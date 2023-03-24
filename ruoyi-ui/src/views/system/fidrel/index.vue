<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入客户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="用户ID" prop="userId">-->
<!--        <el-input-->
<!--          v-model="queryParams.userId"-->
<!--          placeholder="请输入用户ID"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="用户账号" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入用户昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="国家" prop="country">
        <el-input
          v-model="queryParams.country"
          placeholder="请输入国家"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="fid" prop="fid">-->
<!--        <el-input-->
<!--          v-model="queryParams.fid"-->
<!--          placeholder="请输入fid"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="备注" prop="fidCommon">
        <el-input
          v-model="queryParams.fidCommon"
          placeholder="请输入备注"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="备注2" prop="remark2">-->
<!--        <el-input-->
<!--          v-model="queryParams.remark2"-->
<!--          placeholder="请输入备注2"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="用户状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_job_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:fidrel:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:fidrel:edit']"-->
<!--        >修改</el-button>-->
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:fidrel:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="relList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="客户名称" align="center" prop="customerName" />
<!--      <el-table-column label="用户ID" align="center" prop="userId" />-->
      <el-table-column label="用户账号" align="center" prop="userName" />
      <el-table-column label="用户昵称" align="center" prop="nickName" />
      <el-table-column label="国家" align="center" prop="country" />
      <el-table-column label="fid" align="center" prop="fid" />
      <el-table-column label="备注" align="center" prop="fidCommon" />
<!--      <el-table-column label="备注1" align="center" prop="remark" />-->
<!--      <el-table-column label="备注2" align="center" prop="remark2" />-->
<!--      <el-table-column label="状态" align="center" prop="status" />-->

      <el-table-column label="状态" align="center">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.status"
            active-value="0"
            inactive-value="1"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:fidrel:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:fidrel:remove']"
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

    <!-- 添加或修改用户fid对应关系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId" hidden>
          <el-input v-model="form.userId" />
<!--          <el-select-->
<!--            v-model="form.userId"-->
<!--            placeholder="请选择用户ID"-->
<!--            clearable-->
<!--            size="small"-->
<!--            style="width: 240px"-->
<!--            @change="selectUser"-->
<!--          >-->
<!--            <el-option-->
<!--              v-for="dict in userList"-->
<!--              :key="dict.userId"-->
<!--              :label="dict.userName"-->
<!--              :value="dict"-->
<!--            />-->
<!--          </el-select>-->

        </el-form-item>
        <el-form-item label="用户账号" prop="userName">
<!--          <el-input v-model="form.userName" placeholder="请输入用户账号" readonly />-->
          <el-select
            v-model="form.userName"
            placeholder="请选择用户"
            clearable
            size="small"
            style="width: 240px"
            @change="selectUser"
          >
            <el-option
              v-for="dict in userList"
              :key="dict.userId"
              :label="dict.userName"
              :value="dict"
            />
          </el-select>
        </el-form-item>
<!--        <el-form-item label="用户昵称" prop="nickName">-->
<!--          <el-input v-model="form.nickName" placeholder="请输入用户昵称" />-->
<!--        </el-form-item>-->
        <el-form-item label="用户国家" prop="country">
          <el-input v-model="form.country" placeholder="请输入国家" readonly />
        </el-form-item>
<!--        <el-form-item label="fid" prop="fid">-->
<!--          <el-input v-model="form.fid" placeholder="请输入fid" />-->
<!--        </el-form-item>-->
        <el-form-item label="备注" prop="fidCommon">
          <el-input v-model="form.fidCommon" placeholder="请备注该客户开始日期" />
        </el-form-item>
<!--        <el-form-item label="备注1" prop="remark">-->
<!--          <el-input v-model="form.remark" placeholder="请输入备注1" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注2" prop="remark2">-->
<!--          <el-input v-model="form.remark2" placeholder="请输入备注2" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="状态" prop="status">-->
<!--          <el-input v-model="form.status" placeholder="请输入状态" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFidrel, getFidrel, delFidrel, addFidrel, updateFidrel , activeFidrel} from "@/api/system/fidrel";
import { getAllUser } from "@/api/system/user";

import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Fidrel",
  dicts: ['sys_job_status', 'sys_country'],
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
      // 用户fid对应关系表格数据
      relList: [],
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        userName: null,
        customerName: null,
        nickName: null,
        country: null,
        fid: null,
        fidCommon: null,
        remark2: null,
        status: null,
      },
      userQueryParams: {
        userId: null,
        userName: null,
        customerName: null,
        nickName: null,
        country: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "请选择用户", trigger: "blur" }
        ],
        customerName: [
          { required: true, message: "客户名称不能为空", trigger: "blur" }
        ],
        fidCommon: [
          { required: true, message: "请备注该客户开始日期", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getAllUser();
  },
  methods: {
    selectUser(val){
      this.form.userId = val.userId;
      this.form.userName = val.userName;
      this.form.country = val.country;
    },
    getAllUser() {
      getAllUser(this.userQueryParams).then(response => {
          this.userList = response.data;
        }
      );
    },
    /** 查询用户fid对应关系列表 */
    getList() {
      this.loading = true;
      listFidrel(this.queryParams).then(response => {
        this.relList = response.rows;
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
        userId: null,
        userName: null,
        customerName: null,
        nickName: null,
        country: null,
        fid: null,
        fidCommon: null,
        remark: null,
        remark2: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
    handleStatusChange(row) {
      debugger
      let text = row.status === "0" ? "启用" : "停用";
      // if (row.status === "1"){
      //   this.$modal.msg( "不可以停用");
      //   row.status = row.status === "0" ? "1" : "0";
      // }else {
        this.$modal.confirm('确认要"' + text + '""' + row.userName + '"客户的fid吗？').then(function() {
          const updateForm = {
            id : row.id,
            status : row.status
          };
          return updateFidrel(updateForm);
          // changeUserStatus(row.userId, row.status)
        }).then(() => {
          this.$modal.msgSuccess(text + "成功");
          this.getList();
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
      // }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户fid对应关系";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFidrel(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户fid对应关系";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFidrel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFidrel(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户fid对应关系编号为"' + ids + '"的数据项？').then(function() {
        return delFidrel(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/fidrel/export', {
        ...this.queryParams
      }, `fidrel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
