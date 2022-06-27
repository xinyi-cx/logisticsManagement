<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="24" :xs="24">
        <el-card>
          <div slot="header" class="clearfix">
            <span>数据看板</span>
          </div>
          <el-tabs v-model="activeTab" @click="handleClick">
            <el-tab-pane label="按批次查询" name="dateDashboard">
              <dateDashboard />
            </el-tab-pane>
            <el-tab-pane label="按月份查询" name="monthDashboard" :lazy="true">
              <monthDashboard />
            </el-tab-pane>
<!--            <el-tab-pane label="按用户查询" name="userDashboard">-->
<!--              <userDashboard />-->
<!--            </el-tab-pane>-->
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
  name: "Dashboard",
  components: { dateDashboard, monthDashboard, userDashboard },
  data() {
    return {
      // user: {},
      // roleGroup: {},
      // postGroup: {},
      activeTab: "dateDashboard"
    };
  },
  created() {
    // this.getUser();
  },
  methods: {
    // handleClick(tab, event) {
    //   if(tab.name === 'monthDashboard' ) {
    //     this.$nextTick( () => {
    //       this.$refs.month.chart.resize();
    //     })
    //   }
    // }
    // getUser() {
    //   getUserProfile().then(response => {
    //     this.user = response.data;
    //     this.roleGroup = response.roleGroup;
    //     this.postGroup = response.postGroup;
    //   });
    // }
  }
};
</script>
