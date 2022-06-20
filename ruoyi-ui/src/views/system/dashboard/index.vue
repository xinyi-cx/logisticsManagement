<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="18" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <el-tabs v-model="activeTab">
            <el-tab-pane label="按批次查询" name="dateDashboard">
              <dateDashboard :user="user" />
            </el-tab-pane>
            <el-tab-pane label="按月份查询" name="monthDashboard">
              <monthDashboard :user="user" />
            </el-tab-pane>
            <el-tab-pane label="按用户查询" name="userDashboard">
              <userDashboard :user="user" />
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import dateDashboard from "./dateDashboard";
import monthDashboard from "./monthDashboard";
import userDashboard from "./userDashboard";


import { getUserProfile } from "@/api/system/user";

export default {
  name: "Profile",
  components: { dateDashboard, monthDashboard, userDashboard },
  data() {
    return {
      user: {},
      roleGroup: {},
      postGroup: {},
      activeTab: "userinfo"
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    }
  }
};
</script>
