<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="查询日期">
        <el-date-picker
          v-model="dateRange"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:userdeliverinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userdeliverinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="用户运单号" align="center" prop="waybill" />
      <el-table-column label="用户邮箱" align="center" prop="email" />
      <el-table-column label="用户问题描述" align="center" prop="issuedesc" />
      <el-table-column label="信息提交时间" align="center" prop="inserttime" width="180" />
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.inserttime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="运单国别" align="center" prop="country" />
      <el-table-column label="运单物流商" align="center" prop="logistics" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listUserdeliverinfo, getUserdeliverinfo, delUserdeliverinfo, addUserdeliverinfo, updateUserdeliverinfo } from "@/api/system/userdeliverinfo";

export default {
  name: "Userdeliverinfo",
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
      // user deliver infomation table表格数据
      userdeliverinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        waybill: null,
        email: null,
        issuedesc: null,
        inserttime: null,
        country: null,
        logistics: null
      },
      dateRange:[],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        waybill: [
          { required: true, message: "用户运单号不能为空", trigger: "blur" }
        ],
        email: [
          { required: true, message: "用户邮箱不能为空", trigger: "blur" }
        ],
        inserttime: [
          { required: true, message: "信息提交时间不能为空", trigger: "blur" }
        ],
        country: [
          { required: true, message: "运单国别不能为空", trigger: "blur" }
        ],
        logistics: [
          { required: true, message: "运单物流商不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询user deliver infomation table列表 */
    getList() {
      this.loading = true;
      this.queryParams.begintime = this.dateRange[0];
      this.queryParams.endtime = this.dateRange[1];
      listUserdeliverinfo(this.queryParams).then(response => {
        this.userdeliverinfoList = response.rows;
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
        waybill: null,
        email: null,
        issuedesc: null,
        inserttime: null,
        country: null,
        logistics: null
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
      this.title = "添加user deliver infomation table";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getUserdeliverinfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改user deliver infomation table";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUserdeliverinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserdeliverinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除user deliver infomation table编号为"' + ids + '"的数据项？').then(function() {
        return delUserdeliverinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/userdeliverinfo/export', {
        ...this.queryParams
      }, `userdeliverinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
