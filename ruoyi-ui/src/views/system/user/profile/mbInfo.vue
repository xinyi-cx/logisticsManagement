<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <el-form-item label="马帮apiAccountId" prop="apiAccountId">
      <el-input v-model="user.apiAccountId" maxlength="30" />
    </el-form-item>
    <el-form-item label="马帮apiKey" prop="apiKey">
      <el-input v-model="user.apiKey" maxlength="11" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { updateUserProfile } from "@/api/system/user";

export default {
  props: {
    user: {
      type: Object
    }
  },
  data() {
    return {
      // 表单校验
      rules: {
        apiAccountId: [
          { required: true, message: "马帮apiAccountId不能为空", trigger: "blur" }
        ],
        apiKey: [
          { required: true, message: "马帮apiKey不能为空", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateUserProfile(this.user).then(response => {
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
