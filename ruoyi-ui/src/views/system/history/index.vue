<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建时间">
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

      <el-form-item label="类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="批量任务状态"
          clearable
          size="small"
          style="width: 240px"
        >
<!--          <el-option-->
<!--            v-for="dict in dict.type.sys_bat_status"-->
<!--            :key="dict.value"-->
<!--            :label="dict.label"-->
<!--            :value="dict.value"-->
<!--          />-->
          <el-option label="马帮主动通知" value="马帮主动通知"/>
          <el-option label="面单导入" value="面单导入"/>

        </el-select>
      </el-form-item>
      <!--
      <el-form-item label="成功面单数" prop="successNum">
        <el-input
          v-model="queryParams.successNum"
          placeholder="请输入成功面单数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="失败面单数" prop="failNum">
        <el-input
          v-model="queryParams.failNum"
          placeholder="请输入失败面单数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下载次数" prop="downloadNum">
        <el-input
          v-model="queryParams.downloadNum"
          placeholder="请输入下载次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原始excel路径" prop="excelUrl">
        <el-input
          v-model="queryParams.excelUrl"
          placeholder="请输入原始excel路径"
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
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择更新时间">
        </el-date-picker>
      </el-form-item>
      -->
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
<!--          @click="handleImport"-->
<!--          v-hasPermi="['system:package:add']"-->
<!--        >导入</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['system:history:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['system:history:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->

<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['system:history:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="id" />-->
      <el-table-column label="创建时间" align="center" prop="createdTime">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="成功面单数" align="center" prop="successNum">
        <template slot-scope="scope">
          <!-- 待添加点击处理事件 跳转至成功面单列表 -->
          <router-link :to="'/system/package/index/000' + scope.row.id" class="link-type">
           <span>{{ scope.row.successNum }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="失败面单数" align="center" prop="failNum">
        <template slot-scope="scope">
          <!-- 待添加点击处理事件 跳转至失败面单列表-->
          <router-link :to="'/system/package/index/111' + scope.row.id" class="link-type">
           <span>{{ scope.row.failNum }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="下载次数" align="center" prop="downloadNum" />
<!--      <el-table-column label="原始excel" align="center" prop="excelUrl" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            @click="handleDownload(scope.row)"-->
<!--            v-hasPermi="['system:history:edit']"-->
<!--          >{{scope.row.excelUrl}}</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <!--
      <el-table-column label="excel内容" align="center" prop="excelContent" />
      <el-table-column label="创建人" align="center" prop="createUser" />
      <el-table-column label="更新人" align="center" prop="updateUser" />
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      -->
      <el-table-column label="导入文件名称" align="center" prop="fileName" />
      <el-table-column label="查看物流信息" align="center">
        <template slot-scope="scope">
          <router-link :to="'/system/info/index/' + scope.row.id" class="link-type">
            <span>查看物流信息</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-show="scope.row.type === '面单导入'"
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDownload(scope.row)"
          >查看原始excel</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDownloadError(scope.row)"
          >查看导入面单错误信息</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="downloadGeneratedExcel(scope.row)"
          >查看批量excel</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDownloadPDF(scope.row)"
          >查看批量面单</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-view"-->
<!--            @click="handleDownloadReLogic(scope.row)"-->
<!--            v-hasPermi="['system:history:edit']"-->
<!--          >导出物流信息（转寄）</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleDownloadReContent(scope.row)"
          >导出物流</el-button>
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

    <!-- 添加或修改批量任务历史对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="成功面单数" prop="successNum">
          <el-input v-model="form.successNum" placeholder="请输入成功面单数" />
        </el-form-item>
        <el-form-item label="失败面单数" prop="failNum">
          <el-input v-model="form.failNum" placeholder="请输入失败面单数" />
        </el-form-item>
        <el-form-item label="下载次数" prop="downloadNum">
          <el-input v-model="form.downloadNum" placeholder="请输入下载次数" />
        </el-form-item>
        <el-form-item label="原始excel路径" prop="excelUrl">
          <el-input v-model="form.excelUrl" placeholder="请输入原始excel路径" />
        </el-form-item>
        <el-form-item label="excel内容">
          <editor v-model="form.excelContent" :min-height="192"/>
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
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
          <!--
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate2">下载测试数据</el-link>
          -->
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
import { listHistory, getHistory, delHistory, addHistory, updateHistory } from "@/api/system/history";
import { getUser } from "@/api/system/user";
import { getToken } from "@/utils/auth";

export default {
  name: "History",
  dicts: ['sys_normal_disable'],
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
      // 批量任务历史表格数据
      historyList: [],
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
        url: process.env.VUE_APP_BASE_API + "/system/package/importData"
      },
      // 日期范围
      dateRange: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: undefined,
        status: undefined
      },
      packParams:{
        hisParam : null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      userInfo: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询批量任务历史列表 */
    getList() {
      this.loading = true;
      listHistory(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
        this.historyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取对应用户信息*/
    getUserInfo(userId) {
      getUser(userId).then(response => {
        if (response.code == 200) {
          this.userInfo = response.data;
        } else {
          // 给出相应提示
        }
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
        type: null,
        status: "0",
        successNum: null,
        failNum: null,
        downloadNum: null,
        excelUrl: null,
        excelContent: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加批量任务历史";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHistory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改批量任务历史";
      });
    },
    handleDownload(row) {
      this.reset();
      const id = row.id;
      this.download('system/package/downloadFile/' + id, {}, `batch_task_${new Date().getTime()}.xlsx`)
    },
    handleDownloadReLogic(row) {
      this.reset();
      const id = row.id;
      this.download('system/info/exportForBatch/', {
        hisParam : id
      }, `logistics_info_${new Date().getTime()}.xlsx`)
    },
    handleDownloadReContent(row) {
      this.reset();
      const id = row.id;
      this.download('system/content/export/', {
        batchId : id
      }, `logistics_content_${new Date().getTime()}.xlsx`)
    },
    handleDownloadPDF(row) {
      this.reset();
      const id = row.id;
      const userId = parseInt(row.updateUser);
      this.getUserInfo(userId);
      let fileName = `Original ${this.userInfo.userName} ${this.userInfo.country} ${row.createdTime} ${row.successNum} export labels`;
      this.download('system/package/getPDFByBatchId/' + id, {}, `${fileName}.pdf`)
    },
    handleDownloadError(row) {
      this.reset();
      const id = row.id;
      const userId = parseInt(row.updateUser);
      this.getUserInfo(userId);
      let fileName = `Original ${this.userInfo.userName} ${this.userInfo.country} ${row.createdTime} ${row.successNum} export error`;
      this.download('system/package/getTxtById/' + id, {}, `${fileName}.txt`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "批量导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/package/importTemplate', {}, `package_template_${new Date().getTime()}.xlsx`)
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
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", {dangerouslyUseHTMLString: true});
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHistory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHistory(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除批量任务历史编号为"' + ids + '"的数据项？').then(function () {
        return delHistory(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/history/export', {
        ...this.queryParams
      }, `history_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handlePacExport(id) {
      this.packParams.hisParam = '000' + id;
    },

    downloadGeneratedExcel(row) {
      this.packParams.hisParam = row.id;
      const userId = parseInt(row.updateUser);
      this.getUserInfo(userId);
      let fileName = `Original ${this.userInfo.userName} ${this.userInfo.country} ${row.createdTime} ${row.successNum} export xls`;
      this.download('system/package/export', {
        ...this.packParams
      }, `${fileName}.xlsx`);
    }
  }
};
</script>
