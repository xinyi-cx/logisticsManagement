<template>
  <el-form ref="form" :model="form" :rules="rules" label-width="80px">
    <el-form-item label="发件人国家">
      <el-select v-model="form.countryCode" placeholder="请选择" style="width: 100%" clearable filterable>
        <el-option
          v-for="item in dict.type.sys_country"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="发件人城市" prop="city">
      <el-input v-model="form.city" placeholder="请输入发件人城市"/>
    </el-form-item>
    <el-form-item label="发件人街道/地址" prop="address">
      <el-input v-model="form.address" type="textarea" placeholder="请输入内容"/>
    </el-form-item>
    <el-form-item label="发件人公司" prop="company">
      <el-input v-model="form.company" placeholder="请输入发件人公司"/>
    </el-form-item>

    <!--      <el-form-item label="发件人国家" prop="countryCode">-->
    <!--        <el-input v-model="form.countryCode" placeholder="请输入发件人国家" />-->
    <!--      </el-form-item>-->
    <el-form-item label="发件人邮件" prop="email">
      <el-input v-model="form.email" placeholder="请输入发件人邮件"/>
    </el-form-item>
    <el-form-item label="发件人fid" prop="fid">
      <el-input v-model="form.fid" placeholder="请输入发件人fid"/>
    </el-form-item>
    <el-form-item label="发件人姓名" prop="name">
      <el-input v-model="form.name" placeholder="请输入发件人姓名"/>
    </el-form-item>
    <el-form-item label="发件人手机号码" prop="phone">
      <el-input v-model="form.phone" placeholder="请输入发件人手机号码"/>
    </el-form-item>
    <el-form-item label="发件人邮政编码" prop="postalCode">
      <el-input v-model="form.postalCode" placeholder="请输入发件人邮政编码"/>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { getSender, updateSender } from "@/api/system/sender";

export default {
  props: {
    user: {
      type: Object
    }
  },
  dicts: ['sys_country'],
  data() {
    return {
      // 表单参数
      form: {},
      rules: {
      }
    };
  },
  created() {
    this.getSenderByUser();
  },
  methods: {
    getSenderByUser(){
      getSender(0).then(response => {
        this.form = response.data;
      });
    },
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateSender(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
          });
        }
      });
    },
    close() {
      this.$tab.closePage();
    }
  }
};
</script>
