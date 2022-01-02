<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="收件人城市" prop="city">
        <el-input
          v-model="queryParams.city"
          placeholder="请输入收件人城市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人公司" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入收件人公司"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人国家编码" prop="countryCode">
        <el-input
          v-model="queryParams.countryCode"
          placeholder="请输入收件人国家编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人邮件" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入收件人邮件"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入收件人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人手机号码" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入收件人手机号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人邮政编码" prop="postalCode">
        <el-input
          v-model="queryParams.postalCode"
          placeholder="请输入收件人邮政编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人创建人" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入收件人创建人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人更新人" prop="updateUser">
        <el-input
          v-model="queryParams.updateUser"
          placeholder="请输入收件人更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收件人创建时间" prop="createdTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择收件人创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="收件人更新时间" prop="updatedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择收件人更新时间">
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
          v-hasPermi="['system:receiver:add']"
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
          v-hasPermi="['system:receiver:edit']"
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
          v-hasPermi="['system:receiver:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:receiver:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiverList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收件人主键" align="center" prop="id" />
      <el-table-column label="收件人地址" align="center" prop="address" />
      <el-table-column label="收件人城市" align="center" prop="city" />
      <el-table-column label="收件人公司" align="center" prop="company" />
      <el-table-column label="收件人国家编码" align="center" prop="countryCode" />
      <el-table-column label="收件人邮件" align="center" prop="email" />
      <el-table-column label="收件人姓名" align="center" prop="name" />
      <el-table-column label="收件人手机号码" align="center" prop="phone" />
      <el-table-column label="收件人邮政编码" align="center" prop="postalCode" />
      <el-table-column label="收件人创建人" align="center" prop="createUser" />
      <el-table-column label="收件人更新人" align="center" prop="updateUser" />
      <el-table-column label="收件人创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收件人更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:receiver:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:receiver:remove']"
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

    <!-- 添加或修改收货人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收件人地址" prop="address">
          <el-input v-model="form.address" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="收件人城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入收件人城市" />
        </el-form-item>
        <el-form-item label="收件人公司" prop="company">
          <el-input v-model="form.company" placeholder="请输入收件人公司" />
        </el-form-item>
        <el-form-item label="收件人国家编码" prop="countryCode">
          <el-input v-model="form.countryCode" placeholder="请输入收件人国家编码" />
        </el-form-item>
        <el-form-item label="收件人邮件" prop="email">
          <el-input v-model="form.email" placeholder="请输入收件人邮件" />
        </el-form-item>
        <el-form-item label="收件人姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入收件人姓名" />
        </el-form-item>
        <el-form-item label="收件人手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入收件人手机号码" />
        </el-form-item>
        <el-form-item label="收件人邮政编码" prop="postalCode">
          <el-input v-model="form.postalCode" placeholder="请输入收件人邮政编码" />
        </el-form-item>
        <el-form-item label="收件人创建人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入收件人创建人" />
        </el-form-item>
        <el-form-item label="收件人更新人" prop="updateUser">
          <el-input v-model="form.updateUser" placeholder="请输入收件人更新人" />
        </el-form-item>
        <el-form-item label="收件人创建时间" prop="createdTime">
          <el-date-picker clearable size="small"
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择收件人创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收件人更新时间" prop="updatedTime">
          <el-date-picker clearable size="small"
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择收件人更新时间">
          </el-date-picker>
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
import { listReceiver, getReceiver, delReceiver, addReceiver, updateReceiver } from "@/api/system/receiver";

export default {
  name: "Receiver",
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
      // 收货人信息表格数据
      receiverList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        address: null,
        city: null,
        company: null,
        countryCode: null,
        email: null,
        name: null,
        phone: null,
        postalCode: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询收货人信息列表 */
    getList() {
      this.loading = true;
      listReceiver(this.queryParams).then(response => {
        this.receiverList = response.rows;
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
        address: null,
        city: null,
        company: null,
        countryCode: null,
        email: null,
        name: null,
        phone: null,
        postalCode: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null
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
      this.title = "添加收货人信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReceiver(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改收货人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReceiver(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReceiver(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除收货人信息编号为"' + ids + '"的数据项？').then(function() {
        return delReceiver(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/receiver/export', {
        ...this.queryParams
      }, `receiver_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
