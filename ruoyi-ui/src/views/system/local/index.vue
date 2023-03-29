<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="code1" prop="code1">
        <el-input
          v-model="queryParams.code1"
          placeholder="请输入code1"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="code2" prop="code2">
        <el-input
          v-model="queryParams.code2"
          placeholder="请输入code2"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:local:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:local:export']"-->
<!--        >导出</el-button>-->
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

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
      <el-table-column label="code1" align="center" prop="code1" width="120"/>
      <el-table-column label="code2" align="center" prop="code2" width="120"/>
      <!-- 创建时间 -->
      <el-table-column label="创建时间" align="center" prop="createdTime" width="160" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- 内部引用号 -->
      <el-table-column label="内部单号" align="center" show-overflow-tooltip prop="reference" width="100"/>
      <!-- 收货人全名 -->
      <el-table-column label="收件人" width="100" show-overflow-tooltip align="center" prop="receiverName" />
      <!-- 国家 -->
      <el-table-column label="国家" align="center" prop="receiverCountryCode" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_country" :value="scope.row.receiverCountryCode"/>
        </template>
      </el-table-column>
      <!-- 城市 -->
      <el-table-column label="城市" align="center" prop="receiverCity" show-overflow-tooltip width="100"/>
      <!-- 邮编 -->
      <el-table-column label="邮编" align="center"  show-overflow-tooltip prop="receiverPostalCode" width="100"/>
      <!-- 手机号码 -->
      <el-table-column label="手机号码" align="center" show-overflow-tooltip prop="receiverPhone" width="100"/>
      <!-- 货物金额(pln) -->
      <el-table-column label="COD(pln)" align="center" prop="pln" width="100"/>
      <!-- 重量(kg) -->
      <el-table-column label="重量(kg)" align="center" prop="weight" width="100"/>
      <!-- 物品类型-description -->
      <el-table-column label="物品类型" align="center" show-overflow-tooltip prop="content" width="100"/>
      <!-- 下载次数 -->
<!--      <el-table-column label="下载次数" align="center" prop="downloadNum" />-->
      <!-- 备注1 -->
      <el-table-column label="售后电话" align="center" show-overflow-tooltip prop="ref1" width="100"/>
      <!-- 备注2 -->
      <el-table-column label="售后邮箱" align="center" show-overflow-tooltip prop="ref2" width="100"/>
      <!-- 操作 -->
      <el-table-column label="操作" align="center" prop="id">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--          >修改关联关系</el-button>-->
                    <el-button
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                      @click="handleDelete(scope.row)"
                      v-hasPermi="['system:package:remove']"
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
import { listLocal, getLocal, delLocal, addLocal, updateLocal } from "@/api/system/local";
import { listPackageAll, getPackage, delPackage, updatePackage, addPackageAll, packageAll } from "@/api/shippingOrder/package";

import { userCountry } from "@/api/system/user";
import { getToken } from "@/utils/auth";

export default {
  name: "Local",
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
      // 本地关联关系表格数据
      packageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        url: process.env.VUE_APP_BASE_API + "/system/local/importData"
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
        url: process.env.VUE_APP_BASE_API + "/system/local/importDataCz"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        localId: 1,
        oldPackageId: null,
        code1: null,
        code2: null,
        code3: null,
        countryCode: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null,
        isDelete: null
      },
      // 表单参数
      form: {},
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
    this.getCountry();
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
    /** 查询本地关联关系列表 */
    getList() {
      this.loading = true;
      this.ids = [];
      this.queryParams.ids = [];
      const thatOut = this;
      listPackageAll(this.queryParams).then(response => {
        thatOut.packageList = response.rows;
        debugger;
        thatOut.total = response.total;
        thatOut.loading = false;
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
        oldPackageId: null,
        code1: null,
        code2: null,
        code3: null,
        countryCode: null,
        remark: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null,
        isDelete: null
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLocal(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLocal(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除本地关联关系编号为"' + ids + '"的数据项？').then(function() {
        return delLocal(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "本地面单批量导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/local/importTemplate', {
      }, `local_package_template_${new Date().getTime()}.xlsx`)
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
      this.download('system/local/importTemplateCz', {
      }, `local_package_template_${new Date().getTime()}.xlsx`)
    },
    handleImportForCz() {
      this.uploadCz.title = "本地面单批量导入";
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
    /** 导出按钮操作 */
    handleExportRe(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/local/exportLocal', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExportReCz(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/local/exportLocalCz', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
