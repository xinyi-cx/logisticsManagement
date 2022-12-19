<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="业务" prop="importType">
        <el-select v-model="queryParams.importType" placeholder="请选择" clearable filterable>
          <el-option
            v-for="dict in dict.type.sys_waybill_import"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="激活日期">
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
      <el-form-item label="导单日期">
        <el-date-picker
          v-model="dateRange2"
          size="small"
          style="width: 240px"
          value-format="yyyy/MM/dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="客户" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入客户名"
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
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择" clearable filterable>
          <el-option
            v-for="dict in dict.type.sys_waybill"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单号"
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
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImportForNo"
          v-hasPermi="['system:content:add']"
        >导入</el-button>
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

    <el-table v-loading="loading" :data="contentList" stripe border
              max-height="300px"
              @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column fixed label="NewWaybill" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.newWaybill" target="_blank">{{scope.row.newWaybill}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="Client" align="center" prop="client" width="150px" show-overflow-tooltip />
      <el-table-column label="Country" align="center" prop="country" />
      <el-table-column label="Type" align="center" prop="importType" />
      <el-table-column label="LoginID" align="center" prop="loginid" width="150px" show-overflow-tooltip/>
      <el-table-column label="OrderNumber" align="center" prop="orderNumber" width="180px" />
      <el-table-column label="COD" align="center" prop="valuePlnCod" />
      <el-table-column label="Weight" align="center" prop="weightKg" />
      <el-table-column label="Recipient" align="center" prop="recipientName" width="150px" show-overflow-tooltip/>
      <el-table-column label="RecipientPhone" align="center" prop="recipientPhone" width="150px" show-overflow-tooltip/>
      <el-table-column label="RecipientEmail" align="center" prop="recipientEmail" width="150px" show-overflow-tooltip/>
      <el-table-column label="Description" align="center" prop="description" width="100px" show-overflow-tooltip/>
      <el-table-column label="NeedBox" align="center" prop="needBox" />
      <el-table-column label="ExportDate" align="center" prop="createDate" width="150px" show-overflow-tooltip/>
      <el-table-column label="ActiveDate" align="center" prop="activedDate" width="120px" show-overflow-tooltip/>
      <el-table-column label="LastStatusDate" align="center" prop="lastStatusDate" width="120px" show-overflow-tooltip/>
      <el-table-column label="Status" align="center" prop="status" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_waybill" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column fixed label="NewParcel" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.newNumber" target="_blank">{{scope.row.newNumber}}</el-link>
        </template>
      </el-table-column>
      <el-table-column fixed label="ReturnParcel" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.returnNumber" target="_blank">{{scope.row.returnNumber}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
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

    <!--    导入匹配 importPackageForNoGen-->
    <el-dialog :title="uploadNo.title" :visible.sync="uploadNo.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="uploadNo.headers"
        :action="uploadNo.url"
        :disabled="uploadNo.isUploading"
        :on-progress="handleFileUploadProgressForNo"
        :on-success="handleFileSuccessForNo"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div><span style="color: red;">*各业务文件命名规则示例：</span><br/>
            <div style="text-align: left;">本地货：DPD Local Wolin coat pol 20221212 34box export xls</div>
            <div style="text-align: left;">转 寄：DPD Resend Wolin pol 20221212 34box export xls</div>
            <div style="text-align: left;">直 发：DPD Origianl Wolin pol 20221212 34b export xls</div>
          </div>
          <div>
            <span style="color: red;">仅允许导入xls、xlsx格式文件。</span>
            <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplateForNo">下载模板</el-link>
          </div>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileFormForNo" :disabled="submitDisabled">确 定</el-button>
        <el-button @click="uploadNo.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>

<script>
import { listContent, getContent, delContent, addContent, updateContent } from "@/api/system/content";
import { getToken } from "@/utils/auth";

export default {
  name: "Content",
  dicts: ['sys_waybill','sys_waybill_import'],
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
      // 日期范围
      dateRange: [],
      dateRange2:[],
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
        activedDate: null,
        lastStatusDate: null,
        status: null,
        newNumber: null,
        returnNumber: null,
        ids:[],
      },
      // 导入时点击确定后置灰，避免重复点击
      submitDisabled: false,
      uploadNo: {
        // 是否显示弹出层（导入）
        open: false,
        // 弹出层标题（导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/package/importPackageForNoGen"
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
      this.queryParams.params = typeof (this.queryParams.params) === 'object' && this.queryParams.params !== null && !Array.isArray(this.queryParams.params) ? this.queryParams.params : {};
      // this.dateRange2 = Array.isArray(this.dateRange2) ? this.dateRange2 : [];
      this.queryParams.params['CreateDateBeginTime'] = this.dateRange2[0];
      this.queryParams.params['CreateDateEndTime'] = this.dateRange2[1];
      this.loading = true;
      // debugger;
      listContent(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
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
        activedDate: null,
        lastStatusDate: null,
        status: "0",
        newNumber: null,
        returnNumber: null,
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
    handleImportForNo() {
      this.uploadNo.title = "批量导入";
      this.uploadNo.open = true;
      this.submitDisabled = false;
    },
    // 文件上传中处理
    handleFileUploadProgressForNo(event, file, fileList) {
      this.uploadNo.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccessForNo(response, file, fileList) {
      this.uploadNo.open = false;
      this.uploadNo.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileFormForNo() {
      this.$refs.upload.submit();
      this.submitDisabled = true;
    },
    importTemplateForNo() {
      this.download('system/content/importTemplateNoGen', {
      }, `DPD Local Wolin coat pol 20221201 43box export xls template.xlsx`)
    },
    /** 导出按钮操作 */
    handleExport(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/content/export', {
        ...this.queryParams
      }, `content_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
