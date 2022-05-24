<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="内部订单号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入内部订单号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="物流产品运单号" prop="expressChannelCode">
        <el-input
          v-model="queryParams.expressChannelCode"
          placeholder="请输入物流产品运单号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="收件人信息" prop="addressReceiveStr">
        <el-input
          v-model="queryParams.addressReceiveStr"
          placeholder="请输入收件人信息"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['system:dto:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:dto:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:dto:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:dto:add']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:dto:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dtoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="内部订单号" align="center" prop="code" />
      <el-table-column label="ERP自定义单号" align="center" prop="customizeCode" />
      <el-table-column label="平台订单编号：例如ebay,wish 等订单编号" align="center" prop="platformTradeCode" />
      <el-table-column label="订单状态" align="center" prop="status" />
      <el-table-column label="是否有异常" align="center" prop="hasException" />
      <el-table-column label="异常信息，物流供应商处理信息" align="center" prop="processMessage" />
      <el-table-column label="package_id" align="center" prop="packageId" />
      <el-table-column label="预计订单运费" align="center" prop="priceForcast" />
      <el-table-column label="实际订单运费" align="center" prop="priceReal" />
      <el-table-column label="包裹地址国家代码" align="center" prop="shippingCountryCode" />
      <el-table-column label="订单生成时间" align="center" prop="timeCreated" />
      <el-table-column label="客户预报订单重量" align="center" prop="weightForcast" />
      <el-table-column label="供应商实际称重订单重量" align="center" prop="weightReal" />
      <el-table-column label="包裹长度" align="center" prop="length" />
      <el-table-column label="包裹宽度" align="center" prop="width" />
      <el-table-column label="包裹高度" align="center" prop="height" />
      <el-table-column label="订单申报物品中文名" align="center" prop="productNameCn" />
      <el-table-column label="订单申报物品英文名" align="center" prop="productNameEn" />
      <el-table-column label="订单申报价值(US_d)" align="center" prop="productValue" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="货物数量" align="center" prop="itemListQuantity" />
      <el-table-column label="订单项数量" align="center" prop="itemListCount" />
      <el-table-column label="申报币种" align="center" prop="currencyCode" />
      <el-table-column label="交易平台付款时间" align="center" prop="platformPayTime" />
      <el-table-column label="AUSDetails" align="center" prop="AUSDetails" />
      <el-table-column label="cod 订单标识  1:是;2:否" align="center" prop="codFlag" />
      <el-table-column label="cod 订单金额" align="center" prop="codValue" />
      <el-table-column label="商家税号" align="center" prop="vatNo" />
      <el-table-column label="商家税号类型 1.欧盟国_iOSS 编号；2.非_iOSS或其他国家税号 编号；3.空表示other" align="center" prop="vatType" />
      <el-table-column label="订单是否带电 1.是 2.否" align="center" prop="hasBattery" />
      <el-table-column label="订单是否含磁 1.是 2.否" align="center" prop="hasMagnetic" />
      <el-table-column label="订单是否含粉末 1.是 2.否" align="center" prop="hasPowder" />
      <el-table-column label="customer" align="center" prop="customer" />
      <el-table-column label="物流产品运单号" align="center" prop="expressChannelCode" />
      <el-table-column label="物流商内部单" align="center" prop="supplierInnerCode" />
      <el-table-column label="expressChannel" align="center" prop="expressChannel" />
      <el-table-column label="myExpressChannel" align="center" prop="myExpressChannel" />
      <el-table-column label="labelHTMLUrl" align="center" prop="labelHTMLUrl" />
      <el-table-column label="labelPDFUrl" align="center" prop="labelPDFUrl" />
      <el-table-column label="extendFieldsStr" align="center" prop="extendFieldsStr" />
      <el-table-column label="商品SKU" align="center" prop="itemListStr" />
      <el-table-column label="发件人" align="center" prop="addressPickupStr" />
      <el-table-column label="收件人" align="center" prop="addressReceiveStr" />
      <el-table-column label="退件人" align="center" prop="addressBackStr" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:dto:edit']"
          >查看详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:dto:remove']"
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

    <!-- 添加或修改马帮订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内部订单号" prop="code">
          <el-input v-model="form.code" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="ERP自定义单号" prop="customizeCode">
          <el-input v-model="form.customizeCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="平台订单编号：例如ebay,wish 等订单编号" prop="platformTradeCode">
          <el-input v-model="form.platformTradeCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否有异常" prop="hasException">
          <el-input v-model="form.hasException" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="异常信息，物流供应商处理信息" prop="processMessage">
          <el-input v-model="form.processMessage" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="package _id" prop="packageId">
          <el-input v-model="form.packageId" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="预计订单运费" prop="priceForcast">
          <el-input v-model="form.priceForcast" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="实际订单运费" prop="priceReal">
          <el-input v-model="form.priceReal" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="包裹地址国家代码" prop="shippingCountryCode">
          <el-input v-model="form.shippingCountryCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单生成时间" prop="timeCreated">
          <el-input v-model="form.timeCreated" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="客户预报订单重量" prop="weightForcast">
          <el-input v-model="form.weightForcast" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="供应商实际称重订单重量" prop="weightReal">
          <el-input v-model="form.weightReal" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="包裹长度" prop="length">
          <el-input v-model="form.length" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="包裹宽度" prop="width">
          <el-input v-model="form.width" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="包裹高度" prop="height">
          <el-input v-model="form.height" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单申报物品中文名" prop="productNameCn">
          <el-input v-model="form.productNameCn" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单申报物品英文名" prop="productNameEn">
          <el-input v-model="form.productNameEn" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单申报价值(US_d)" prop="productValue">
          <el-input v-model="form.productValue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="货物数量" prop="itemListQuantity">
          <el-input v-model="form.itemListQuantity" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单项数量" prop="itemListCount">
          <el-input v-model="form.itemListCount" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="申报币种" prop="currencyCode">
          <el-input v-model="form.currencyCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="交易平台付款时间" prop="platformPayTime">
          <el-input v-model="form.platformPayTime" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="AUSDetails" prop="AUSDetails">
          <el-input v-model="form.AUSDetails" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="cod 订单标识  1:是;2:否" prop="codFlag">
          <el-input v-model="form.codFlag" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="cod 订单金额" prop="codValue">
          <el-input v-model="form.codValue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商家税号" prop="vatNo">
          <el-input v-model="form.vatNo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单是否带电 1.是 2.否" prop="hasBattery">
          <el-input v-model="form.hasBattery" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单是否含磁 1.是 2.否" prop="hasMagnetic">
          <el-input v-model="form.hasMagnetic" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="订单是否含粉末 1.是 2.否" prop="hasPowder">
          <el-input v-model="form.hasPowder" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="customer" prop="customer">
          <el-input v-model="form.customer" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="物流产品运单号" prop="expressChannelCode">
          <el-input v-model="form.expressChannelCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="物流商内部单" prop="supplierInnerCode">
          <el-input v-model="form.supplierInnerCode" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="expressChannel" prop="expressChannel">
          <el-input v-model="form.expressChannel" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="myExpressChannel" prop="myExpressChannel">
          <el-input v-model="form.myExpressChannel" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="labelHTMLUrl" prop="labelHTMLUrl">
          <el-input v-model="form.labelHTMLUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="labelPDFUrl" prop="labelPDFUrl">
          <el-input v-model="form.labelPDFUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="extendFieldsStr" prop="extendFieldsStr">
          <el-input v-model="form.extendFieldsStr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商品SKU" prop="itemListStr">
          <el-input v-model="form.itemListStr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="发件人" prop="addressPickupStr">
          <el-input v-model="form.addressPickupStr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="收件人" prop="addressReceiveStr">
          <el-input v-model="form.addressReceiveStr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="退件人" prop="addressBackStr">
          <el-input v-model="form.addressBackStr" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--    导入对话框-->
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
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listDto, getDto, delDto } from "@/api/system/dto";
import { getToken } from "@/utils/auth";

export default {
  name: "Dto",
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
      // 马帮订单信息表格数据
      dtoList: [],
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
        url: process.env.VUE_APP_BASE_API + "/system/dto/importData"
      },
      // 退件人时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        customizeCode: null,
        platformTradeCode: null,
        status: null,
        hasException: null,
        processMessage: null,
        packageId: null,
        priceForcast: null,
        priceReal: null,
        shippingCountryCode: null,
        timeCreated: null,
        weightForcast: null,
        weightReal: null,
        length: null,
        width: null,
        height: null,
        productNameCn: null,
        productNameEn: null,
        productValue: null,
        itemListQuantity: null,
        itemListCount: null,
        currencyCode: null,
        platformPayTime: null,
        AUSDetails: null,
        codFlag: null,
        codValue: null,
        vatNo: null,
        vatType: null,
        hasBattery: null,
        hasMagnetic: null,
        hasPowder: null,
        customer: null,
        expressChannelCode: null,
        supplierInnerCode: null,
        expressChannel: null,
        myExpressChannel: null,
        labelHTMLUrl: null,
        labelPDFUrl: null,
        extendFieldsStr: null,
        itemListStr: null,
        addressPickupStr: null,
        addressReceiveStr: null,
        addressBackStr: null,
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
    /** 查询马帮订单信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endTime"] = this.daterangeCreateTime[1];
      }
      listDto(this.queryParams).then(response => {
        this.dtoList = response.rows;
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
        code: null,
        customizeCode: null,
        platformTradeCode: null,
        status: "0",
        hasException: null,
        processMessage: null,
        packageId: null,
        priceForcast: null,
        priceReal: null,
        shippingCountryCode: null,
        timeCreated: null,
        weightForcast: null,
        weightReal: null,
        length: null,
        width: null,
        height: null,
        productNameCn: null,
        productNameEn: null,
        productValue: null,
        remark: null,
        itemListQuantity: null,
        itemListCount: null,
        currencyCode: null,
        platformPayTime: null,
        AUSDetails: null,
        codFlag: null,
        codValue: null,
        vatNo: null,
        vatType: null,
        hasBattery: null,
        hasMagnetic: null,
        hasPowder: null,
        customer: null,
        expressChannelCode: null,
        supplierInnerCode: null,
        expressChannel: null,
        myExpressChannel: null,
        labelHTMLUrl: null,
        labelPDFUrl: null,
        extendFieldsStr: null,
        itemListStr: null,
        addressPickupStr: null,
        addressReceiveStr: null,
        addressBackStr: null,
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
      this.ids = selection.map(item => item.code)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "马帮订单信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const code = row.code || this.ids
      getDto(code).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "马帮订单信息";
      });
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/dto/importTemplate', {
      }, `mb_import_template_${new Date().getTime()}.xlsx`)
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
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.code != null) {
    //         updateDto(this.form).then(response => {
    //           this.$modal.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addDto(this.form).then(response => {
    //           this.$modal.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      const codes = row.code || this.ids;
      this.$modal.confirm('是否确认删除马帮订单信息编号为"' + codes + '"的数据项？').then(function() {
        return delDto(codes);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "批量导入";
      this.upload.open = true;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/dto/export', {
        ...this.queryParams
      }, `dto_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
