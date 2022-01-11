<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发件人id" prop="senderId">
        <el-input
          v-model="queryParams.senderId"
          placeholder="请输入发件人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货人id" prop="receiverId">
        <el-input
          v-model="queryParams.receiverId"
          placeholder="请输入收货人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类1" prop="ref1">
        <el-input
          v-model="queryParams.ref1"
          placeholder="请输入分类1"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类2" prop="ref2">
        <el-input
          v-model="queryParams.ref2"
          placeholder="请输入分类2"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务id" prop="servicesId">
        <el-input
          v-model="queryParams.servicesId"
          placeholder="请输入服务id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮政编码" prop="postalCode">
        <el-input
          v-model="queryParams.postalCode"
          placeholder="请输入邮政编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createUser">
        <el-input
          v-model="queryParams.createUser"
          placeholder="请输入创建人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新人" prop="updateUser">
        <el-input
          v-model="queryParams.updateUser"
          placeholder="请输入更新人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
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
          v-hasPermi="['system:package:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:package:edit']"
        >批量导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:package:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:package:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
     <!-- 列表 -->
    <el-table v-loading="loading" :data="packageList" @selection-change="handleSelectionChange">
      <!-- 多选 -->
      <el-table-column type="selection" width="55" align="center" />
      <!-- 物流单号 -->
      <el-table-column label="物流单号" align="center" prop="id" />
      <!-- 创建时间 -->
      <el-table-column label="创建时间" align="center" prop="id">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
        </el-table-column>
      <!-- 收货人全名 -->
      <el-table-column label="收货人全名" align="center" prop="receiverName" />
      <!-- 国家 -->
      <el-table-column label="国家" align="center" prop="receiverCountryCode" />
      <!-- 城市 -->
      <el-table-column label="城市" align="center" prop="receiverCity" />
      <!-- 货物金额(pln) -->
      <el-table-column label="货物金额(pln)" align="center" prop="pln" width="180"/>
      <!-- 重量(kg) -->
      <el-table-column label="重量(kg)" align="center" prop="weight" />
      <!-- 邮码 -->
      <el-table-column label="邮码" align="center" prop="receiverPostalCode" />
      <!-- 手机号码 -->
      <el-table-column label="手机号码" align="center" prop="receiverPhone" />
      <!-- 内部引用号 -->
      <el-table-column label="内部引用号" align="center" prop="reference" />
      <!-- 备注 -->
      <el-table-column label="备注" align="center" prop="content" />
      <!-- 下载次数 -->
      <el-table-column label="下载次数" align="center" prop="id" />
      <!-- 分类 -->
      <el-table-column label="分类" align="center" prop="ref1" />
      <!-- 分类2 -->
      <el-table-column label="分类2" align="center" prop="ref2" />
      <!-- 操作 -->
      <el-table-column label="操作" align="center" prop="id">
         <template slot-scope="scope">
         <!-- 修改与删除按钮
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:package:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:package:remove']"
          >删除</el-button>
          -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:package:remove']"
          >查看面单</el-button>
        </template>
      </el-table-column>

      <!-- 原来的字段内容
        <el-table-column label="面单主键" align="center" prop="id" />
        <el-table-column label="付款人类型" align="center" prop="payerType" />
        <el-table-column label="发件人id" align="center" prop="senderId" />
        <el-table-column label="收货人id" align="center" prop="receiverId" />
        <el-table-column label="分类1" align="center" prop="ref1" />
        <el-table-column label="分类2" align="center" prop="ref2" />
        <el-table-column label="服务id" align="center" prop="servicesId" />
        <el-table-column label="手机号码" align="center" prop="phone" />
        <el-table-column label="邮政编码" align="center" prop="postalCode" />
        <el-table-column label="创建人" align="center" prop="createUser" />
        <el-table-column label="更新人" align="center" prop="updateUser" />
        <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
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
              v-hasPermi="['system:package:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:package:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      -->
    </el-table>
    <!-- 翻页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改面单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="106px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="收货人全名" prop="receiverName">
            <el-input v-model="form.receiverName" placeholder="请输入收获人全名" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
          <el-col :span="12">
            <el-form-item label="国家" prop="receiverCountryCode">
              <el-input v-model="form.receiverCountryCode" placeholder="请输入用户昵称" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="城市" prop="receiverCity">
              <el-input v-model="form.receiverCity" placeholder="请输入城市名" maxlength="30" />
            </el-form-item>
          </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="街道" prop="receiverAddress">
            <el-input v-model="form.receiverAddress" placeholder="请输入街道信息" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="邮政编码" prop="receiverPostalCode">
            <el-input v-model="form.receiverPostalCode" placeholder="请输入邮政编码" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号码" prop="receiverPhone">
            <el-input v-model.number="form.receiverPhone" placeholder="请输入手机号码" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="邮件/联系方式" prop="receiverEmail">
            <el-input v-model="form.receiverEmail" placeholder="请输入邮件/联系方式" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="货物金额(PLN)" prop="pln">
            <el-input v-model.number="form.pln" placeholder="max:6000" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <h3 class="headline">其他</h3>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="内部引用号" prop="reference">
            <el-input v-model="form.reference" placeholder="请输入内部引用号" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="重量(KG)" prop="weight">
            <el-input v-model="form.weight" placeholder="max(National:31.5,International:40)" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="分类" prop="ref1">
            <el-input v-model="form.ref1" placeholder="请输入分类" maxlength="30" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="分类2" prop="ref2">
            <el-input v-model="form.ref2" placeholder="请输入分类2" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="备注" prop="content">
            <el-input v-model="form.content" placeholder="请输入邮政备注" maxlength="30" />
          </el-form-item>
        </el-col>
      </el-row>
      <!--
        <el-form-item label="发件人id" prop="senderId">
          <el-input v-model="form.senderId" placeholder="请输入发件人id" />
        </el-form-item>
        <el-form-item label="收货人id" prop="receiverId">
          <el-input v-model="form.receiverId" placeholder="请输入收货人id" />
        </el-form-item>
        <el-form-item label="分类1" prop="ref1">
          <el-input v-model="form.ref1" placeholder="请输入分类1" />
        </el-form-item>
        <el-form-item label="分类2" prop="ref2">
          <el-input v-model="form.ref2" placeholder="请输入分类2" />
        </el-form-item>
        <el-form-item label="服务id" prop="servicesId">
          <el-input v-model="form.servicesId" placeholder="请输入服务id" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="邮政编码" prop="postalCode">
          <el-input v-model="form.postalCode" placeholder="请输入邮政编码" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUser">
          <el-input v-model="form.createUser" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="更新人" prop="updateUser">
          <el-input v-model="form.updateUser" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable size="small"
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable size="small"
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
        -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPackageAll, getPackage, delPackage, updatePackage, addPackageAll } from "@/api/shippingOrder/package";

export default {
  name: "Package",
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
      // 面单表格数据
      packageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        payerType: null,
        senderId: null,
        receiverId: null,
        ref1: null,
        ref2: null,
        servicesId: null,
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
    /** 查询面单列表 */
    getList() {
      this.loading = true;
      listPackageAll(this.queryParams).then(response => {
        this.packageList = response.rows;
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
        payerType: null,
        senderId: null,
        receiverId: null,
        ref1: null,
        ref2: null,
        servicesId: null,
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
      this.title = "添加面单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPackage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改面单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePackage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPackageAll(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除面单编号为"' + ids + '"的数据项？').then(function() {
        return delPackage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/package/export', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style lang="scss" scoped>
.headline {
  font-weight: bold;
  padding-bottom: 10px;
  border-bottom: 1px solid #ddd;
}

</style>
