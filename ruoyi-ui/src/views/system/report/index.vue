<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="运单号码" prop="waybill">
        <el-input
          v-model="queryParams.waybill"
          placeholder="请输入运单号码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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

      <el-form-item label="查询内容" prop="selectType">
        <el-select v-model="selectType" placeholder="请选择" filterable>
          <el-option label="未回COD报告" value="未回COD报告"/>
          <el-option label="已回COD报告" value="已回COD报告"/>
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
          type="success"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImportForNo"
          v-hasPermi="['system:report:add']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:report:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:report:remove']"
          v-if="selectType === '已回COD报告'"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" width="60px"/>
      <el-table-column label="单号" align="center" width="180px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.newWaybill" target="_blank">{{scope.row.newWaybill}}</el-link>
        </template>
      </el-table-column>
      <el-table-column label="国家" align="center" prop="country" />
      <el-table-column label="客户" align="center" prop="client" width="150px" show-overflow-tooltip />
      <el-table-column label="COD金额" align="center" prop="valuePlnCod" />
      <el-table-column label="激活日期" align="center" prop="activedDate" width="120px" show-overflow-tooltip/>
      <el-table-column label="签收日期" align="center" prop="lastStatusDate" width="120px" show-overflow-tooltip/>
      <el-table-column label="COD报告时间" align="center" prop="collectiveTransferDate" width="120px" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:report:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:report:remove']"
            v-if="selectType === '已回COD报告'"
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

    <!-- 添加或修改COD报告对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
<!--        <el-form-item label="document表id" prop="documentFileId">-->
<!--          <el-input v-model="form.documentFileId" placeholder="请输入document表id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="package表id" prop="packId">-->
<!--          <el-input v-model="form.packId" placeholder="请输入package表id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="batch_id" prop="batchId">-->
<!--          <el-input v-model="form.batchId" placeholder="请输入batch_id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="logic_id" prop="logicId">-->
<!--          <el-input v-model="form.logicId" placeholder="请输入logic_id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="CLIENT" prop="client">-->
<!--          <el-input v-model="form.client" placeholder="请输入CLIENT" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="Country" prop="country">-->
<!--          <el-input v-model="form.country" placeholder="请输入Country" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="LoginID" prop="loginid">-->
<!--          <el-input v-model="form.loginid" placeholder="请输入LoginID" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="nr_klienta" prop="nrKlienta">-->
<!--          <el-input v-model="form.nrKlienta" placeholder="请输入nr_klienta" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="下载收件人" prop="downloadRecipient">-->
<!--          <el-input v-model="form.downloadRecipient" placeholder="请输入下载收件人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="运单号码" prop="waybill">-->
<!--          <el-input v-model="form.waybill" placeholder="请输入运单号码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="运单" prop="waybillPostingDate">-->
<!--          <el-input v-model="form.waybillPostingDate" placeholder="请输入运单" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="货到付款金额 " prop="cashOnDelivery">-->
<!--          <el-input v-model="form.cashOnDelivery" placeholder="请输入货到付款金额 " />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="货件有效交付日期" prop="goodsEffectiveDeliveryDate">-->
<!--          <el-input v-model="form.goodsEffectiveDeliveryDate" placeholder="请输入货件有效交付日期" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="货件收件人" prop="addressee">-->
<!--          <el-input v-model="form.addressee" placeholder="请输入货件收件人" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="邮政编码" prop="postalCode">-->
<!--          <el-input v-model="form.postalCode" placeholder="请输入邮政编码" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="地区" prop="area">-->
<!--          <el-input v-model="form.area" placeholder="请输入地区" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="街" prop="street">-->
<!--          <el-input v-model="form.street" placeholder="请输入街" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="Unisoft-Client中的内容字段" prop="unisoftClientContentField">-->
<!--          <el-input v-model="form.unisoftClientContentField" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="批量传输" prop="batchTransmission">-->
<!--          <el-input v-model="form.batchTransmission" placeholder="请输入批量传输" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="集体移送日期" prop="collectiveTransferDate">-->
<!--          <el-input v-model="form.collectiveTransferDate" placeholder="请输入集体移送日期" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="结算编号 " prop="settlementNumber">-->
<!--          <el-input v-model="form.settlementNumber" placeholder="请输入结算编号 " />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="激活时间" prop="sysActivedDate">-->
<!--          <el-input v-model="form.sysActivedDate" placeholder="请输入激活时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="最近物流时间" prop="sysLastStatusDate">-->
<!--          <el-input v-model="form.sysLastStatusDate" placeholder="请输入最近物流时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="签收时间" prop="sysSignDate">-->
<!--          <el-input v-model="form.sysSignDate" placeholder="请输入签收时间" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注1" prop="remark">-->
<!--          <el-input v-model="form.remark" placeholder="请输入备注1" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注2" prop="remark2">-->
<!--          <el-input v-model="form.remark2" placeholder="请输入备注2" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注3" prop="remark3">-->
<!--          <el-input v-model="form.remark3" placeholder="请输入备注3" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注4" prop="remark4">-->
<!--          <el-input v-model="form.remark4" placeholder="请输入备注4" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="备注5" prop="remark5">-->
<!--          <el-input v-model="form.remark5" placeholder="请输入备注5" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否删除" prop="isDelete">-->
<!--          <el-input v-model="form.isDelete" placeholder="请输入是否删除" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

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

<script>
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/system/report";
import { listContentNoReport, listContentReport } from "@/api/system/content";
import { getToken } from "@/utils/auth";

export default {
  name: "Report",
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
      // COD报告表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRange: [],
      selectType: "已回COD报告",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sysActivedDate: null,
        sysLastStatusDate: null,
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
        url: process.env.VUE_APP_BASE_API + "/system/report/importData"
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
    /** 查询COD报告列表 */
    getList() {
      this.loading = true;
      if (this.selectType === '已回COD报告'){
        listContentReport(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.reportList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      } else {
        listContentNoReport(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.reportList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      }
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
        nrKlienta: null,
        downloadRecipient: null,
        waybill: null,
        waybillPostingDate: null,
        cashOnDelivery: null,
        goodsEffectiveDeliveryDate: null,
        addressee: null,
        postalCode: null,
        area: null,
        street: null,
        unisoftClientContentField: null,
        batchTransmission: null,
        collectiveTransferDate: null,
        settlementNumber: null,
        sysActivedDate: null,
        sysLastStatusDate: null,
        sysSignDate: null,
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
      this.title = "添加COD报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReport(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改COD报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除COD报告编号为"' + ids + '"的数据项？').then(function() {
        return delReport(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleImportForNo() {
      this.uploadNo.title = "报告批量导入";
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
      this.download('system/report/importTemplate', {
      }, `DPD Local Wolin coat pol 20221201 43box export xls template.xlsx`)
    },
    /** 导出按钮操作 */
    handleExport() {
      if (this.selectType === '已回COD报告'){
        this.download('system/content/exportReport', {
          ...this.queryParams
        }, `report_${new Date().getTime()}.xlsx`)
      }else {
        this.download('system/content/exportNoReport', {
          ...this.queryParams
        }, `report_${new Date().getTime()}.xlsx`)
      }
    }
  }
};
</script>
