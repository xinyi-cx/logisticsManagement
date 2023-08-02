<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="批量任务历史Id" prop="batId">
        <el-input
          v-model="queryParams.batId"
          placeholder="请输入批量任务历史Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="同步时间" prop="syncTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.syncTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择同步时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:dpd:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:dpd:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:dpd:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dpd:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dpdList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="批量任务历史Id" align="center" prop="batId" />
      <el-table-column label="信息id" align="center" prop="messageId" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="同步状态 1-同步成功 0-同步失败 2-部分成功" align="center" prop="syncStatus" />
      <el-table-column label="文件是否删除 0-未删除 1-已经删除" align="center" prop="fileDeleteStatus" />
      <el-table-column label="错误信息" align="center" prop="errorMsg" />
      <el-table-column label="同步时间" align="center" prop="syncTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.syncTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:dpd:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dpd:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改同步历史记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="批量任务历史Id" prop="batId">
          <el-input v-model="form.batId" placeholder="请输入批量任务历史Id" />
        </el-form-item>
        <el-form-item label="信息id" prop="messageId">
          <el-input v-model="form.messageId" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="错误信息" prop="errorMsg">
          <el-input v-model="form.errorMsg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="同步时间" prop="syncTime">
          <el-date-picker clearable size="small"
            v-model="form.syncTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择同步时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDpd, getDpd, delDpd, addDpd, updateDpd } from "@/api/system/dpd";

export default {
  name: "Dpd",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 同步历史记录表格数据
      dpdList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        batId: null,
        messageId: null,
        fileName: null,
        syncStatus: null,
        fileDeleteStatus: null,
        errorMsg: null,
        syncTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileName: [
          { required: true, message: "文件名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询同步历史记录列表 */
    getList() {
      this.loading = true;
      listDpd(this.queryParams).then(response => {
        this.dpdList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        batId: null,
        messageId: null,
        fileName: null,
        syncStatus: "0",
        fileDeleteStatus: "0",
        errorMsg: null,
        syncTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加同步历史记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDpd(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改同步历史记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDpd(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDpd(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除同步历史记录编号为"' + ids + '"的数据项？').then(function() {
        return delDpd(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dpd/export', {
        ...this.queryParams
      }, `dpd_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
