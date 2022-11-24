<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="document表id" prop="documentFileId">
        <el-input
          v-model="queryParams.documentFileId"
          placeholder="请输入document表id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="package表id" prop="packId">
        <el-input
          v-model="queryParams.packId"
          placeholder="请输入package表id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="batch_id" prop="batchId">
        <el-input
          v-model="queryParams.batchId"
          placeholder="请输入batch_id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="logic_id" prop="logicId">
        <el-input
          v-model="queryParams.logicId"
          placeholder="请输入logic_id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="CLIENT" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入CLIENT"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Country" prop="country">
        <el-input
          v-model="queryParams.country"
          placeholder="请输入Country"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="LoginID" prop="loginid">
        <el-input
          v-model="queryParams.loginid"
          placeholder="请输入LoginID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="New waybill" prop="newWaybill">
        <el-input
          v-model="queryParams.newWaybill"
          placeholder="请输入New waybill"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Order number" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入Order number"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="value PLN (COD)" prop="valuePlnCod">
        <el-input
          v-model="queryParams.valuePlnCod"
          placeholder="请输入value PLN (COD)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Weight kg" prop="weightKg">
        <el-input
          v-model="queryParams.weightKg"
          placeholder="请输入Weight kg"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="recipient name" prop="recipientName">
        <el-input
          v-model="queryParams.recipientName"
          placeholder="请输入recipient name"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Recipient phone" prop="recipientPhone">
        <el-input
          v-model="queryParams.recipientPhone"
          placeholder="请输入Recipient phone"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Recipient E-mail" prop="recipientEmail">
        <el-input
          v-model="queryParams.recipientEmail"
          placeholder="请输入Recipient E-mail"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Description" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入Description"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Need box" prop="needBox">
        <el-input
          v-model="queryParams.needBox"
          placeholder="请输入Need box"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="Create Date" prop="createDate">
        <el-input
          v-model="queryParams.createDate"
          placeholder="请输入Create Date"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注2" prop="remark2">
        <el-input
          v-model="queryParams.remark2"
          placeholder="请输入备注2"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注3" prop="remark3">
        <el-input
          v-model="queryParams.remark3"
          placeholder="请输入备注3"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注4" prop="remark4">
        <el-input
          v-model="queryParams.remark4"
          placeholder="请输入备注4"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注5" prop="remark5">
        <el-input
          v-model="queryParams.remark5"
          placeholder="请输入备注5"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入是否删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:content:add']"
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
          v-hasPermi="['system:content:edit']"
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
          v-hasPermi="['system:content:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:content:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="document表id" align="center" prop="documentFileId" />
      <el-table-column label="package表id" align="center" prop="packId" />
      <el-table-column label="batch_id" align="center" prop="batchId" />
      <el-table-column label="logic_id" align="center" prop="logicId" />
      <el-table-column label="CLIENT" align="center" prop="client" />
      <el-table-column label="Country" align="center" prop="country" />
      <el-table-column label="Type" align="center" prop="importType" />
      <el-table-column label="LoginID" align="center" prop="loginid" />
      <el-table-column label="New waybill" align="center" prop="newWaybill" />
      <el-table-column label="Order number" align="center" prop="orderNumber" />
      <el-table-column label="value PLN (COD)" align="center" prop="valuePlnCod" />
      <el-table-column label="Weight kg" align="center" prop="weightKg" />
      <el-table-column label="recipient name" align="center" prop="recipientName" />
      <el-table-column label="Recipient phone" align="center" prop="recipientPhone" />
      <el-table-column label="Recipient E-mail" align="center" prop="recipientEmail" />
      <el-table-column label="Description" align="center" prop="description" />
      <el-table-column label="Need box" align="center" prop="needBox" />
      <el-table-column label="Create Date" align="center" prop="createDate" />
      <el-table-column label="备注1" align="center" prop="remark" />
      <el-table-column label="备注2" align="center" prop="remark2" />
      <el-table-column label="备注3" align="center" prop="remark3" />
      <el-table-column label="备注4" align="center" prop="remark4" />
      <el-table-column label="备注5" align="center" prop="remark5" />
      <el-table-column label="是否删除" align="center" prop="isDelete" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:content:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:content:remove']"
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

    <!-- 添加或修改导入查询物流对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="document表id" prop="documentFileId">
          <el-input v-model="form.documentFileId" placeholder="请输入document表id" />
        </el-form-item>
        <el-form-item label="package表id" prop="packId">
          <el-input v-model="form.packId" placeholder="请输入package表id" />
        </el-form-item>
        <el-form-item label="batch_id" prop="batchId">
          <el-input v-model="form.batchId" placeholder="请输入batch_id" />
        </el-form-item>
        <el-form-item label="logic_id" prop="logicId">
          <el-input v-model="form.logicId" placeholder="请输入logic_id" />
        </el-form-item>
        <el-form-item label="CLIENT" prop="client">
          <el-input v-model="form.client" placeholder="请输入CLIENT" />
        </el-form-item>
        <el-form-item label="Country" prop="country">
          <el-input v-model="form.country" placeholder="请输入Country" />
        </el-form-item>
        <el-form-item label="LoginID" prop="loginid">
          <el-input v-model="form.loginid" placeholder="请输入LoginID" />
        </el-form-item>
        <el-form-item label="New waybill" prop="newWaybill">
          <el-input v-model="form.newWaybill" placeholder="请输入New waybill" />
        </el-form-item>
        <el-form-item label="Order number" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入Order number" />
        </el-form-item>
        <el-form-item label="value PLN (COD)" prop="valuePlnCod">
          <el-input v-model="form.valuePlnCod" placeholder="请输入value PLN (COD)" />
        </el-form-item>
        <el-form-item label="Weight kg" prop="weightKg">
          <el-input v-model="form.weightKg" placeholder="请输入Weight kg" />
        </el-form-item>
        <el-form-item label="recipient name" prop="recipientName">
          <el-input v-model="form.recipientName" placeholder="请输入recipient name" />
        </el-form-item>
        <el-form-item label="Recipient phone" prop="recipientPhone">
          <el-input v-model="form.recipientPhone" placeholder="请输入Recipient phone" />
        </el-form-item>
        <el-form-item label="Recipient E-mail" prop="recipientEmail">
          <el-input v-model="form.recipientEmail" placeholder="请输入Recipient E-mail" />
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="form.description" placeholder="请输入Description" />
        </el-form-item>
        <el-form-item label="Need box" prop="needBox">
          <el-input v-model="form.needBox" placeholder="请输入Need box" />
        </el-form-item>
        <el-form-item label="Create Date" prop="createDate">
          <el-input v-model="form.createDate" placeholder="请输入Create Date" />
        </el-form-item>
        <el-form-item label="备注1" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注1" />
        </el-form-item>
        <el-form-item label="备注2" prop="remark2">
          <el-input v-model="form.remark2" placeholder="请输入备注2" />
        </el-form-item>
        <el-form-item label="备注3" prop="remark3">
          <el-input v-model="form.remark3" placeholder="请输入备注3" />
        </el-form-item>
        <el-form-item label="备注4" prop="remark4">
          <el-input v-model="form.remark4" placeholder="请输入备注4" />
        </el-form-item>
        <el-form-item label="备注5" prop="remark5">
          <el-input v-model="form.remark5" placeholder="请输入备注5" />
        </el-form-item>
        <el-form-item label="是否删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
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
import { listContent, getContent, delContent, addContent, updateContent } from "@/api/system/content";

export default {
  name: "Content",
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
      // 导入查询物流表格数据
      contentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        documentFileId: null,
        packId: null,
        batchId: null,
        logicId: null,
        client: null,
        country: null,
        importType: null,
        loginid: null,
        newWaybill: null,
        orderNumber: null,
        valuePlnCod: null,
        weightKg: null,
        recipientName: null,
        recipientPhone: null,
        recipientEmail: null,
        description: null,
        needBox: null,
        createDate: null,
        remark2: null,
        remark3: null,
        remark4: null,
        remark5: null,
        isDelete: null,
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
    /** 查询导入查询物流列表 */
    getList() {
      this.loading = true;
      listContent(this.queryParams).then(response => {
        this.contentList = response.rows;
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
        documentFileId: null,
        packId: null,
        batchId: null,
        logicId: null,
        client: null,
        country: null,
        importType: null,
        loginid: null,
        newWaybill: null,
        orderNumber: null,
        valuePlnCod: null,
        weightKg: null,
        recipientName: null,
        recipientPhone: null,
        recipientEmail: null,
        description: null,
        needBox: null,
        createDate: null,
        remark: null,
        remark2: null,
        remark3: null,
        remark4: null,
        remark5: null,
        isDelete: null,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加导入查询物流";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getContent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改导入查询物流";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateContent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContent(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除导入查询物流编号为"' + ids + '"的数据项？').then(function() {
        return delContent(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/content/export', {
        ...this.queryParams
      }, `content_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
