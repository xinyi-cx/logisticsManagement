<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发货时间" prop="deliveryTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.deliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择发货时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最新物流时间" prop="lastTime" label-width="100px">
        <el-date-picker clearable size="small"
          v-model="queryParams.lastTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择最新物流时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="pack_id" prop="packId">-->
<!--        <el-input-->
<!--          v-model="queryParams.packId"-->
<!--          placeholder="请输入pack_id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="转寄pack_id" prop="secPackId">-->
<!--        <el-input-->
<!--          v-model="queryParams.secPackId"-->
<!--          placeholder="请输入转寄pack_id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="物流单号" prop="waybill">
        <el-input
          v-model="queryParams.waybill"
          placeholder="请输入物流单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物流状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="物流状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in dict.type.sys_waybill"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="parcel_id" prop="parcelId">-->
<!--        <el-input-->
<!--          v-model="queryParams.parcelId"-->
<!--          placeholder="请输入parcel_id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createdTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.createdTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="更新时间" prop="updatedTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.updatedTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择更新时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="refreshList">获取最新物流信息</el-button>
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
<!--          v-hasPermi="['system:info:add']"-->
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
<!--          v-hasPermi="['system:info:edit']"-->
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
<!--          v-hasPermi="['system:info:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange" style="width: 100%;">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="id" />-->
      <!-- 物流单号、 -->
      <el-table-column fixed label="物流单号" align="center" width="200px">
        <template slot-scope="scope">
          <el-link :href="'https://t.17track.net/zh-cn#nums=' + scope.row.waybill" target="_blank">{{scope.row.waybill}}</el-link>
        </template>
      </el-table-column>


      <el-table-column label="物流公司" align="center" prop="company" />
      <el-table-column label="发货时间" align="center" prop="deliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center" prop="order" />
      <el-table-column label="最新物流时间" align="center" prop="lastTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最新物流信息" align="center" prop="lastMsg" width="200" show-overflow-tooltip/>
      <el-table-column label="回复信息" align="center" prop="repMsg" />
      <el-table-column label="失败原因" align="center" prop="failureMsg" />
      <el-table-column label="备注" align="center" prop="content" />
      <el-table-column label="内部引用号" align="center" prop="reference" width="200" />
<!--      <el-table-column label="pack_id" align="center" prop="packId" />-->
<!--      <el-table-column label="转寄pack_id" align="center" prop="secPackId" />-->
      <el-table-column label="包裹状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_waybill" :value="scope.row.status"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="package_id" align="center" prop="packageId" />-->
<!--      <el-table-column label="parcel_id" align="center" prop="parcelId" />-->
<!--      <el-table-column label="创建人" align="center" prop="createUser" />-->
<!--      <el-table-column label="更新人" align="center" prop="updateUser" />-->
<!--      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="是否删除" align="center" prop="isDelete" />-->
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['system:info:edit']"-->
<!--          >修改</el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleRefreshOne(scope.row)"
          >获取最新物流信息</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:info:remove']"
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

    <!-- 添加或修改物流信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物流公司" prop="company">
          <el-input v-model="form.company" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="发货时间" prop="deliveryTime">
          <el-date-picker clearable size="small"
            v-model="form.deliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单号" prop="order">
          <el-input v-model="form.order" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="最新物流时间" prop="lastTime">
          <el-date-picker clearable size="small"
            v-model="form.lastTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择最新物流时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最新物流信息" prop="lastMsg">
          <el-input v-model="form.lastMsg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="回复信息" prop="repMsg">
          <el-input v-model="form.repMsg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="失败原因" prop="failureMsg">
          <el-input v-model="form.failureMsg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="内部引用号" prop="reference">
          <el-input v-model="form.reference" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="pack_id" prop="packId">
          <el-input v-model="form.packId" placeholder="请输入pack_id" />
        </el-form-item>
        <el-form-item label="转寄pack_id" prop="secPackId">
          <el-input v-model="form.secPackId" placeholder="请输入转寄pack_id" />
        </el-form-item>
        <el-form-item label="物流单号" prop="waybill">
          <el-input v-model="form.waybill" placeholder="请输入物流单号" />
        </el-form-item>
        <el-form-item label="package_id" prop="packageId">
          <el-input v-model="form.packageId" placeholder="请输入package_id" />
        </el-form-item>
        <el-form-item label="parcel_id" prop="parcelId">
          <el-input v-model="form.parcelId" placeholder="请输入parcel_id" />
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
import { refreshInfo, listInfo, refreshOneInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/system/info";

export default {
  name: "Info",
  dicts: ['sys_waybill'],
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
      // 物流信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        company: null,
        deliveryTime: null,
        order: null,
        lastTime: null,
        lastMsg: null,
        repMsg: null,
        failureMsg: null,
        content: null,
        reference: null,
        packId: null,
        secPackId: null,
        waybill: null,
        status: null,
        packageId: null,
        parcelId: null,
        createUser: null,
        updateUser: null,
        createdTime: null,
        updatedTime: null,
        hisParam: null
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
    const status = this.$route.params && this.$route.params.status;
    debugger;
    this.queryParams.hisParam = hisParam;
    this.queryParams.status = status;
    this.getList();
  },
  methods: {
    /** 查询物流信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    refreshList() {
      this.loading = true;
      refreshInfo(this.queryParams).then(response => {
        this.$modal.msgSuccess(response);
        this.getList();
      });
    },
    handleRefreshOne(row) {
      const id = row.id || this.ids;
      refreshOneInfo(id).then(response => {
        this.$modal.msgSuccess(response);
        this.getList();
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
        company: null,
        deliveryTime: null,
        order: null,
        lastTime: null,
        lastMsg: null,
        repMsg: null,
        failureMsg: null,
        content: null,
        reference: null,
        packId: null,
        secPackId: null,
        waybill: null,
        status: "0",
        packageId: null,
        parcelId: null,
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物流信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物流信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除物流信息编号为"' + ids + '"的数据项？').then(function() {
        return delInfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
