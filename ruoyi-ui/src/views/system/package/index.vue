<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="物流单号" prop="waybill">
        <el-input
          v-model="queryParams.waybill"
          placeholder="请输入物流单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发件人" prop="showSenderName">
        <el-input
          v-model="queryParams.showSenderName"
          placeholder="请输入发件人信息"
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
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5" v-if="countryCodePlFlag">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:package:add']"
        >导入</el-button>
      </el-col>

      <el-col :span="1.5" v-if="countryCodePlFlag">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:package:export']"
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
          v-hasPermi="['system:package:add']"
        >导入</el-button>
      </el-col>

      <el-col :span="1.5" v-if="countryCodeCzFlag">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportForCz"
          v-hasPermi="['system:package:export']"
        >导出</el-button>
      </el-col>
<!--no-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-upload2"-->
<!--          size="mini"-->
<!--          @click="handleImportForNo"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >导入(不生成dpd面单)</el-button>-->
<!--      </el-col>-->
<!--      no-->
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
      <!-- 物流单号 -->
<!--      <el-table-column label="物流单号" align="center" width="150" prop="waybill" />-->
      <el-table-column fixed label="物流单号" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.waybill" target="_blank">{{scope.row.waybill}}</el-link>
        </template>
      </el-table-column>
      <!-- 创建时间 -->
      <el-table-column label="创建时间" align="center" prop="createdTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime) }}</span>
        </template>
        </el-table-column>
      <!-- 内部引用号 -->
      <el-table-column label="内部单号" align="center" width="150" show-overflow-tooltip prop="reference" />
      <!-- 发件人全名 -->
      <el-table-column label="发件人" width="100" show-overflow-tooltip align="center" prop="showSenderName" />
      <!-- 收货人全名 -->
      <el-table-column label="收件人" width="100" show-overflow-tooltip align="center" prop="receiverName" />
      <!-- 国家 -->
      <el-table-column label="国家" align="center" prop="receiverCountryCode" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_country" :value="scope.row.receiverCountryCode"/>
        </template>
      </el-table-column>
      <!-- 城市 -->
      <el-table-column label="城市" align="center" width="120" show-overflow-tooltip prop="receiverCity" />
      <!-- 邮编 -->
      <el-table-column label="邮编" align="center" prop="receiverPostalCode" />
      <!-- 手机号码 -->
      <el-table-column label="手机号码" align="center" prop="receiverPhone" width="150" />
      <!-- 货物金额(pln) -->
      <el-table-column label="COD(pln)" align="center" prop="pln" width="80"/>
      <!-- 重量(kg) -->
      <el-table-column label="重量(kg)" align="center" prop="weight" />

      <!-- 货品名称-description -->
      <el-table-column label="物品类型" align=              "center" prop="customerData1" width="120" show-overflow-tooltip/>
      <!-- 下载次数 -->
      <el-table-column label="下载次数" align="center" prop="downloadNum" />
      <!-- 备注1 reference1 -->
      <el-table-column label="售后电话" show-overflow-tooltip align="center" prop="ref1" />
      <!-- 备注2 reference2 -->
      <el-table-column label="售后邮箱" show-overflow-tooltip align="center" prop="ref2" />
      <!-- 操作 -->
      <el-table-column label="操作" align="center" fixed="right" width="120" class-name="small-padding">
         <template slot-scope="scope">
           <el-button
             size="mini"
             type="text"
             icon="el-icon-view"
             @click="handleDownloadPDF(scope.row)"
             v-hasPermi="['system:history:edit']"
           >面单</el-button>
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
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <h3 class="headline">收件人信息</h3>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="receiverName">
              <el-input v-model="form.receiverName" placeholder="请输入收件人全名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="国家" prop="receiverCountryCode">
              <el-select v-model="form.receiverCountryCode" placeholder="请选择" clearable filterable>
                <el-option
                  v-for="dict in dict.type.sys_country"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
            <el-col :span="12">
              <el-form-item label="城市" prop="receiverCity">
                <el-input v-model="form.receiverCity" placeholder="请输入城市名" maxlength="30" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="街道" prop="receiverAddress">
                <el-input v-model="form.receiverAddress" placeholder="请输入街道信息" />
              </el-form-item>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="邮编" prop="receiverPostalCode">
              <el-input v-model.number="form.receiverPostalCode" placeholder="请输入5位数的邮政编码" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号" prop="receiverPhone">
              <el-input v-model="form.receiverPhone" placeholder="请输入手机号码" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="receiverEmail">
              <el-input v-model="form.receiverEmail" placeholder="请输入邮箱信息" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="COD(pln)" prop="pln" label-width="90px">
              <el-input v-model.number="form.pln" placeholder="max:6000" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <h3 class="headline">其他</h3>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="Order" prop="reference">
              <el-input v-model="form.reference" placeholder="请输入内部引用号" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重量(KG)" prop="weight">
              <el-input v-model.number="form.weight" placeholder="不足1kg按1kg计算，不能大于10kg" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="售后电话" prop="ref1">
              <el-input v-model="form.ref1" placeholder="请输入售后电话" maxlength="30" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="售后邮箱" prop="ref2">
              <el-input v-model="form.ref2" placeholder="请输入售后邮箱" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="商品描述" prop="content">
              <el-input v-model="form.content" placeholder="请输入商品描述信息" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" :disabled="disabled">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

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
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplateForNo">下载模板</el-link>
          <!--          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplatePDF">下载测试数据</el-link>-->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileFormForNo" :disabled="submitDisabled">确 定</el-button>
        <el-button @click="uploadNo.open = false">取 消</el-button>
      </div>
    </el-dialog>
<!--    importPackageForNoGen-->

  </div>
</template>

<script>
import { listPackageAll, getPackage, delPackage, updatePackage, addPackageAll } from "@/api/shippingOrder/package";
import { userCountry } from "@/api/system/user";
import { getToken } from "@/utils/auth";

export default {
  name: "Package",
  dicts: ['sys_country'],
  data() {
    // 重量校验（不能大于10Kg）
    let checkWeight = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('重量不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          if (value < 1) {
            callback(new Error('重量须大于等于1kg'));
          } else if(value > 10) {
            callback(new Error('重量不能超过10kg'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    // cod校验（不能大于 6000）
    let checkPln = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('COD金额不能为空'));
      }
      setTimeout(() => {
        if (!Number.isInteger(value)) {
          callback(new Error('请输入数字值'));
        } else {
          if (value >6000) {
            callback(new Error('COD金额不能超过6000'));
          } else {
            callback();
          }
        }
      }, 1000);
    };
    return {
      countryCodePlFlag: true,
      countryCodeCzFlag: false,
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
      // 新增面单，提交后置灰吧？
      disabled: false,
      // 导入时点击确定后置灰，避免重复点击
      submitDisabled: false,
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
        url: process.env.VUE_APP_BASE_API + "/system/package/importData"
      },
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
        url: process.env.VUE_APP_BASE_API + "/system/package/importDataCz"
      },
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
      // 查询参数
      queryParams: {
        status: null,
        datStr: null,
        hisParam: null,
        pageNum: 1,
        pageSize: 10,
        payerType: null,
        ref1: null,
        ref2: null,
        servicesId: null,
        phone: null,
        postalCode: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null,
        showSenderName: null,
        waybill: null,
        ids:[]
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        receiverName: [
          { required: true, message: "请输入收件人全名", trigger: "blur" },
          // { min: 2, max: 20, message: '用户名长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        receiverCountryCode:[
          { required: true, message: "请选择所在国家", trigger: "blur" }
        ],
        receiverCity: [
          { required: true, message: "请输入收件人所在城市", trigger: "blur" },
          // 这块需要校验么？
        ],
        receiverAddress: [
          { required: true, message: "请输入收件人详细地址", trigger: "blur" }
        ],
        receiverPostalCode: [
          { required: true, message: "请输入邮政编码", trigger: "blur" }
        ],
        pln: [
          { required: true, message: "请输入COD", trigger: "blur" },
          { validator: checkPln, trigger: "blur"}
        ],
        receiverEmail: [
          { required: true, message: "邮箱信息不能为空", trigger: "blur"},
          { type: "email", message: "请输入正确的邮箱地址", trigger: ["blur", "change"] }
        ],
        receiverPhone: [
          { required: true, message: "手机号不能为空", trigger: "blur"},
         // { pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: "请输入正确的手机号码", trigger: "blur" }
        ],
        reference: [
          { required: true, message: "请输入内部引用号", trigger: "blur" }
        ],
        weight: [
          { required: true, message: "请输入重量信息", trigger: "blur" },
          { validator: checkWeight, trigger: "blur"}
        ],
      }
    };
  },
  created() {
    const hisParam = this.$route.params && this.$route.params.hisParam;
    const status = this.$route.params && this.$route.params.status;
    const datStr = this.$route.params && this.$route.params.datStr;
    console.log(status+"aaa",datStr);
    if (hisParam){
      this.queryParams.hisParam = hisParam;
    }
    if (status){
      this.queryParams.status = status;
    }
    if (datStr){
      this.queryParams.datStr = datStr;
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
      this.ids = [];
      this.queryParams.ids = [];
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length!==1;
      this.multiple = !selection.length;
      // TO-DO 根据选中数据进行导出，将数组传给导出接口即可。
      console.log(this.ids);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.disabled = false;
      this.title = "新增面单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPackage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.disabled = false;
        this.title = "修改面单";
      });
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "面单批量导入";
      this.upload.open = true;
      this.submitDisabled = false;
    },
    handleImportForCz() {
      this.uploadCz.title = "面单批量导入";
      this.uploadCz.open = true;
      this.submitDisabled = false;
    },
    handleImportForNo() {
      this.uploadNo.title = "面单批量导入";
      this.uploadNo.open = true;
      this.submitDisabled = false;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/package/importTemplate', {
      }, `package_template_${new Date().getTime()}.xlsx`)
    },
    importTemplateForCz() {
      this.download('system/package/importTemplateCz', {
      }, `package_template_${new Date().getTime()}.xlsx`)
    },
    importTemplateForNo() {
      this.download('system/package/importTemplateCz', {
      }, `package_template_${new Date().getTime()}.xlsx`)
    },
    importTemplate2(){
      this.download('system/package/downloadFile/11', {
      }, `package_template_${new Date().getTime()}.xlsx`)
    },
    handleDownloadPDF(row) {
      const id = row.id;
      this.download('system/package/getPDFById/' + id, {
      }, `package_${new Date().getTime()}.pdf`)
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
      this.submitDisabled = true;
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePackage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.disabled = true;
              this.getList();
            });
          } else {
            addPackageAll(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.disabled = true;
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
    handleExport(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/package/export', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    },
    /** 捷克导出按钮操作 */
    handleExportForCz(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/package/exportCz', {
        ...this.queryParams
      }, `package_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExportTwo(row) {
      const ids = row.id || this.ids;
      this.queryParams.ids = ids;
      this.download('system/package/exportTwo', {
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
