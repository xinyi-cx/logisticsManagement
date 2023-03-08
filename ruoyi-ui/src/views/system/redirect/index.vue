<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="78px">
      <el-form-item label="reference1" prop="ref1">
        <el-input
          v-model="queryParams.ref1"
          placeholder="请输入ref1的内容"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="reference2" prop="ref2">
        <el-input
          v-model="queryParams.ref2"
          placeholder="请输入ref2的内容"
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
      <el-form-item label="邮编" prop="postalCode">
        <el-input
          v-model="queryParams.postalCode"
          placeholder="请输入邮编"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-upload2"-->
<!--          size="mini"-->
<!--          @click="handleImport"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >导入</el-button>-->
<!--      </el-col>-->

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-upload2"-->
<!--          size="mini"-->
<!--          @click="handleImportForRel"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >导入关联关系</el-button>-->
<!--      </el-col>-->

      <el-col :span="1.5" v-if="countryCodePlFlag">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
        >导入</el-button>
      </el-col>

      <el-col :span="1.5" v-if="countryCodePlFlag">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportRe"
        >导出</el-button>
      </el-col>

      <!--      导入导出 捷克-->
      <el-col :span="1.5" v-if="countryCodeCzFlag">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImportForCz"
        >导入</el-button>
      </el-col>

      <el-col :span="1.5" v-if="countryCodeCzFlag">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportReCz"
        >导出</el-button>
      </el-col>

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:package:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:package:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
     <!-- 列表 -->
    <el-table v-loading="loading" :data="packageList" @selection-change="handleSelectionChange">
      <!-- 多选 -->
      <el-table-column type="selection" width="55" align="center" />
      <!-- 原面单ID -->
<!--      <el-table-column label="原面单ID" align="center" prop="originalId" />-->
      <!-- 物流单号 -->
<!--      <el-table-column label="物流单号" align="center" prop="waybill" />-->
      <el-table-column fixed label="物流单号" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.waybill" target="_blank">{{scope.row.waybill}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="old shipment number" align="center" prop="oldWaybill" />
      <el-table-column label="old return number" align="center" prop="backOrder" />
      <!-- 创建时间 -->
      <el-table-column label="创建时间" align="center" prop="createdTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
        </el-table-column>
      <!-- 内部引用号 -->
      <el-table-column label="内部单号" align="center" show-overflow-tooltip prop="reference" />
      <!-- 收货人全名 -->
      <el-table-column label="收件人" width="100" show-overflow-tooltip align="center" prop="receiverName" />
      <!-- 国家 -->
      <el-table-column label="国家" align="center" prop="receiverCountryCode" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_country" :value="scope.row.receiverCountryCode"/>
        </template>
      </el-table-column>
      <!-- 城市 -->
      <el-table-column label="城市" align="center" prop="receiverCity" show-overflow-tooltip />
      <!-- 邮编 -->
      <el-table-column label="邮编" align="center"  show-overflow-tooltip prop="receiverPostalCode" />
      <!-- 手机号码 -->
      <el-table-column label="手机号码" align="center" show-overflow-tooltip prop="receiverPhone" />
      <!-- 货物金额(pln) -->
      <el-table-column label="COD(pln)" align="center" prop="pln" width="180"/>
      <!-- 重量(kg) -->
      <el-table-column label="重量(kg)" align="center" prop="weight" />
      <!-- 物品类型-description -->
      <el-table-column label="物品类型" align="center" show-overflow-tooltip prop="content" />
      <!-- 下载次数 -->
      <el-table-column label="下载次数" align="center" prop="downloadNum" />
      <!-- 备注1 -->
      <el-table-column label="售后电话" align="center" show-overflow-tooltip prop="ref1" />
      <!-- 备注2 -->
      <el-table-column label="售后邮箱" align="center" show-overflow-tooltip prop="ref2" />
      <!-- 操作 -->
      <el-table-column label="操作" align="center" prop="id">
         <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改关联关系</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['system:package:remove']"-->
<!--          >删除</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <!-- 翻页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 修改转寄关联关系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="formRel" :model="formRel" :rules="rules" label-width="80px">
        <el-form-item label="旧物流单号" prop="oldWaybill">
          <el-input v-model="formRel.oldWaybill" placeholder="请输入旧物流单号" />
        </el-form-item>
        <el-form-item label="回退单号" prop="backOrder">
          <el-input v-model="formRel.backOrder" placeholder="请输入回退单号" />
        </el-form-item>
        <el-form-item label="新的单号" prop="newOrder">
          <el-input v-model="formRel.newOrder" placeholder="请输入新的单号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRelForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

<!--    导入对话框-->
<!--    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>-->
<!--      <el-upload-->
<!--        ref="upload"-->
<!--        :limit="1"-->
<!--        accept=".xlsx, .xls"-->
<!--        :headers="upload.headers"-->
<!--        :action="upload.url"-->
<!--        :disabled="upload.isUploading"-->
<!--        :on-progress="handleFileUploadProgress"-->
<!--        :on-success="handleFileSuccess"-->
<!--        :auto-upload="false"-->
<!--        drag-->
<!--      >-->
<!--        <i class="el-icon-upload"></i>-->
<!--        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>-->
<!--        <div class="el-upload__tip text-center" slot="tip">-->
<!--          <span>仅允许导入xls、xlsx格式文件。</span>-->
<!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>-->
<!--        </div>-->
<!--      </el-upload>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitFileForm">确 定</el-button>-->
<!--        <el-button @click="upload.open = false">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
    <!--    导入关联关系对话框-->
<!--    <el-dialog :title="uploadRel.title" :visible.sync="uploadRel.open" width="400px" append-to-body>-->
<!--      <el-upload-->
<!--        ref="upload"-->
<!--        :limit="1"-->
<!--        accept=".xlsx, .xls"-->
<!--        :headers="uploadRel.headers"-->
<!--        :action="uploadRel.url"-->
<!--        :disabled="uploadRel.isUploading"-->
<!--        :on-progress="handleFileUploadProgressForRel"-->
<!--        :on-success="handleFileSuccessForRel"-->
<!--        :auto-upload="false"-->
<!--        drag-->
<!--      >-->
<!--        <i class="el-icon-upload"></i>-->
<!--        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>-->
<!--        <div class="el-upload__tip text-center" slot="tip">-->
<!--          <span>仅允许导入xls、xlsx格式文件。</span>-->
<!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplateForRel">下载模板</el-link>-->
<!--        </div>-->
<!--      </el-upload>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitFileFormForRel">确 定</el-button>-->
<!--        <el-button @click="uploadRel.open = false">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->
    <!--    导入波兰对话框-->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
          <!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplatePDF">下载测试数据</el-link>-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm" :disabled="submitDisabled">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

    <!--    导入捷克对话框-->
    <el-dialog :title="uploadCz.title" :visible.sync="uploadCz.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="uploadCz.headers"
        :action="uploadCz.url"
        :disabled="uploadCz.isUploading"
        :on-progress="handleFileUploadProgressForCz"
        :on-success="handleFileSuccessForCz"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplateForCz">下载模板</el-link>
          <!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplatePDF">下载测试数据</el-link>-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileFormForCz" :disabled="submitDisabled">确 定</el-button>
        <el-button @click="uploadCz.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPackageAll, getPackage, delPackage, updatePackage, addPackageAll, packageAll } from "@/api/shippingOrder/package";
import { getInfoByNewPackageId, updateRel } from "@/api/system/redirectrel";

import { userCountry } from "@/api/system/user";
import { getToken } from "@/utils/auth";

export default {
  name: "Package",
  dicts: ['sys_country'],
  data() {
    return {
      countryCodePlFlag: true,
      countryCodeCzFlag: false,
      allPackage: [],
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
      // 导入参数
      upload: {
        // 是否显示弹出层（导入）
        open: false,
        // 弹出层标题（导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/redirect/importData"
      },
      submitDisabled: false,
      // 导入参数
      uploadCz: {
        // 是否显示弹出层（导入）
        open: false,
        // 弹出层标题（导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/redirectrel/importDataCz"
      },
      // 导入关联关系参数
      uploadRel: {
        // 是否显示弹出层（导入）
        open: false,
        // 弹出层标题（导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/redirectrel/importData"
      },
      // 查询参数
      queryParams: {
        hisParam: null,
        pageNum: 1,
        pageSize: 10,
        originalId: 1,
        payerType: null,
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
      formRel: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    const hisParam = this.$route.params && this.$route.params.hisParam;
    if (hisParam){
      this.queryParams.hisParam = hisParam;
    }
    // this.getAll();
    this.getList();
  },
  methods: {
    getCountry() {
      userCountry().then(response => {
        let UCountry = response.msg;
        if ("PL" == UCountry){
          this.countryCodePlFlag = true;
        } else if ("CN" == UCountry){
          this.countryCodePlFlag = true;
        } else {
          this.countryCodePlFlag = false;
          this.countryCodeCzFlag = true;
        }
      });
    },
    getAll(){
      packageAll(null).then(response => {
        this.allPackage = response;
      });
    },
    /** 查询面单列表 */
    getList() {
      this.loading = true;
      this.ids = [];
      this.queryParams.ids = [];
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
        receiverName: null,
        receiverCountryCode: null,
        receiverCity: null,
        receiverAddress: null,
        receiverPostalCode: null,
        receiverPhone: null,
        receiverEmail: null,
        pln: null,
        ref1: null,
        ref2: null,
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
      this.title = "新增转寄面单";
    },
    /** 修改转寄面单关联关系 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfoByNewPackageId(id).then(response => {
        this.formRel = response.data;
        this.open = true;
        this.title = "修改转寄面单关联关系";
      });
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "批量导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/redirect/importTemplate', {
      }, `redirect_package_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    importTemplateForCz() {
      this.download('system/redirect/importTemplateCz', {
      }, `redirect_package_template_${new Date().getTime()}.xlsx`)
    },
    handleImportForCz() {
      this.uploadCz.title = "批量导入";
      this.uploadCz.open = true;
      this.submitDisabled = false;
    },
    // 文件上传中处理
    handleFileUploadProgressForCz(event, file, fileList) {
      this.uploadCz.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccessForCz(response, file, fileList) {
      this.uploadCz.open = false;
      this.uploadCz.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileFormForCz() {
      this.$refs.upload.submit();
      this.submitDisabled = true;
    },
    /** 捷克导出按钮操作 */
    handleExportForCz(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/redirect/exportCz', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    },

    /** 导入关联关系按钮操作 */
    handleImportForRel() {
      this.uploadRel.title = "批量导入关联关系";
      this.uploadRel.open = true;
    },
    /** 下载关联关系模板操作 */
    importTemplateForRel() {
      this.download('system/redirectrel/importTemplate', {
      }, `package_template_${new Date().getTime()}.xlsx`)
    },
    // 文件关联关系上传中处理
    handleFileUploadProgressForRel(event, file, fileList) {
      this.uploadRel.isUploading = true;
    },
    // 文件关联关系上传成功处理
    handleFileSuccessForRel(response, file, fileList) {
      this.uploadRel.open = false;
      this.uploadRel.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交关联关系上传文件
    submitFileFormForRel() {
      this.$refs.upload.submit();
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
    submitRelForm() {
      this.$refs["formRel"].validate(valid => {
        if (valid) {
          if (this.formRel.id != null) {
            updateRel(this.formRel).then(response => {
              this.$modal.msgSuccess("修改成功");
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
    // handleExport() {
    //   const ids = row.id || this.ids;
    //   this.queryParams.ids = ids;
    //   this.download('system/redirect/export', {
    //     ...this.queryParams
    //   }, `package_${new Date().getTime()}.xlsx`)
    // },
    /** 导出按钮操作 */
    handleExportRe(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/package/exportRe', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExportReCz(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/package/exportReCz', {
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
