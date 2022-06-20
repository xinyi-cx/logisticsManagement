<template>
  <div class="app-container">
    <div class="day-dashboard" v-show="dayShow">
      <el-form ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="查询批次" prop="ref1">
          <el-date-picker clearable size="small"
                          v-model="paramDte"
                          type="date"
                          value-format="yyyyMMdd"
                          placeholder="请选择查询批次"
                          :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="toggleDashboard">按月份查询</el-button>
        </el-form-item>
      </el-form>

      <div class="statusChart" id="statusChart" v-if="activeIndex % 2 == 0" :style="{width: '100%', height: '600px'}"></div>
    </div>

    <div class="day-dashboard" v-show="!dayShow">
      <el-form ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="查询月份" prop="ref1">
          <el-date-picker clearable size="small"
                          v-model="paramMonth"
                          type="month"
                          value-format="yyyyMM"
                          placeholder="请选择查询月份"
                          :picker-options="pickerOptionsMonth">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="monthHandleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="monthResetQuery">重置</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="toggleDashboard">按批次查询</el-button>
        </el-form-item>
      </el-form>

      <div class="monthChart" id="monthChart" v-if="activeIndex % 2 == 1" :style="{width: '100%', height: '600px'}"></div>
    </div>
  </div>



</template>

<script>
import { statistics } from "@/api/shippingOrder/package";
import echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme

export default {
  name: 'monthDashboard',
  data() {
    return {
      dayShow: true,
      activeIndex: 0,
      echartsData:{
        xAxisData: [],
        seriesData: []
      },
      monthEchartsData:{
        xAxisData: [],
        seriesData: []
      },
      paramDte: null,
      showSearch: true,
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 *24);
            picker.$emit('pick', date)
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      // 按月查询
      paramMonth: null,
      pickerOptionsMonth: {
        disabledDate(time) {
          return time.time() > Date.now();
        },
      }
    }
  },
  created() {
    this.getCurrentDate();
    this.getEcartsData();
    this.getMonthEcartsData();
  },
  mounted() {
    this.$nextTick(() => {
      this.drawLine();
      this.drawMonthLine();
    })
  },
  methods: {
    getEcartsData() {
      statistics(this.paramDte).then(response => {
        this.echartsData.xAxisData = response.data.xAxisData;
        this.echartsData.seriesData = response.data.seriesData;
        let barSize;
        if (response.data.xAxisData === undefined){
          barSize = undefined;
        }else {
          barSize= response.data.xAxisData.length;
        }
        this.drawLine(barSize);
      });
    },
    getMonthEcartsData() {
      statistics(this.paramMonth).then(response => {
        this.monthEchartsData.xAxisData = response.data.xAxisData;
        this.monthEchartsData.seriesData = response.data.seriesData;
        let barSize;
        if (response.data.xAxisData === undefined){
          barSize = undefined;
        }else {
          barSize= response.data.xAxisData.length;
        }
        this.drawMonthLine(barSize);
      });
    },
    drawLine(barSize) {
      // 初始化echarts实例
      let mychart = echarts.init(document.getElementById('statusChart'));
      let titleDate = this.paramDte ? this.paramDte : '今日';
      let barWidth = barSize === undefined ? '3%' : barSize * 3 + '%';
      var _this = this;
      let option = {
        title: {
          text: `${titleDate}各订单状态柱形图`,
          textStyle: {
            color: '#333',
            fontWeight: 'bold'
          },
          left: 'center',
          top: 'top'
        },
        legend: {
          data: ['新增订单', '激活订单', '签收订单', '异常订单', '拒签订单']
        },
        tooltip: {},
        xAxis: {
          data: this.echartsData.xAxisData,
          type: 'category',
          // data: ['新增订单', '激活订单', '签收订单', '异常订单', '拒签订单']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          type: "bar",
          barWidth: barWidth,
          showBackground: true,
          itemStyle: {
            borderRadius: 5,
            color: new echarts.graphic.LinearGradient(0,0,0,1,[
                { offset: 0, color: '#83bff6'},
                { offset: 0.7, color: '#188df0'},
                { offset: 1, color: '#188df0'}
              ])
          },
          emphasis: {
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0,0,0,1, [
                { offset: 0, color: '#2378f7'},
                { offset: 0.7, color: '#2378f7'},
                { offset: 1, color: '#83bff6'}
              ])
            }
          },
          data: this.echartsData.seriesData
          // data: [80, 60, 70, 10, 50]

        }]
      };
      //绘制图表
      mychart.setOption(option);
      mychart.on('click', function(params) {
        _this.barClick(params);
      });
    },
    barClick(params){
      this.$router.push({
        name: 'PackageList',
        params: {
          status: params.name,
          datStr: this.paramDte
        }
      }).catch(() => {});
    },
    drawMonthLine(barSize) {
      // 初始化echarts实例
      let monthChart = echarts.init(document.getElementById('monthChart'));
      let titleDate = this.paramMonth ? this.paramMonth : '当月';
      var _this = this;
      let barWidth = barSize === undefined ? '3%' : barSize * 3 + '%';
      let option = {
        title: {
          text: `${titleDate}各订单状态柱形图`,
          textStyle: {
            color: '#333',
            fontWeight: 'bold'
          },
          left: 'center',
          top: 'top'
        },
        legend: {
          data: ['新增订单', '激活订单', '签收订单', '异常订单', '拒签订单']
        },
        tooltip: {},
        xAxis: {
          data: this.monthEchartsData.xAxisData,
          type: 'category',
          // data: ['新增订单', '激活订单', '签收订单', '异常订单', '拒签订单']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          type: "bar",
          barWidth: barWidth,
          showBackground: true,
          itemStyle: {
            borderRadius: 5,
            color: new echarts.graphic.LinearGradient(0,0,0,1,[
              { offset: 0, color: '#83bff6'},
              { offset: 0.7, color: '#188df0'},
              { offset: 1, color: '#188df0'}
            ])
          },
          emphasis: {
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0,0,0,1, [
                { offset: 0, color: '#2378f7'},
                { offset: 0.7, color: '#2378f7'},
                { offset: 1, color: '#83bff6'}
              ])
            }
          },
          data: this.monthEchartsData.seriesData
          // data: [80, 60, 70, 10, 50]

        }]
      };
      //绘制图表
      monthChart.setOption(option);
      monthChart.on('click', function(params) {
        _this.monthBarClick(params);
      });
    },
    monthBarClick(params) {
      this.$router.push({
        name: 'PackageList',
        params: {
          status: params.name,
          datStr: this.paramMonth
        }
      }).catch(() => {});
    },
    getCurrentDate() {
      let today = new Date();
      let year = today.getFullYear() + '';
      let month = today.getMonth() + 1 + '';
      let day = today.getDate() + '';
      if(month < 10) {
        month = '0' + month;
      }
      this.paramMonth = year.concat(month);
      this.paramDte = year.concat(month).concat(day);
      return;
    },
    // 搜索事件
    handleQuery() {
      this.getEcartsData();
    },
    // 搜索事件
    monthHandleQuery() {
      this.getMonthEcartsData();
    },
    monthResetQuery() {
      this.paramMonth = null;
      this.getMonthEcartsData();
    },
    // 重置事件
    resetQuery() {
      this.getCurrentDate();
      this.getEcartsData();
      this.getMonthEcartsData();
    },
    toggleDashboard() {

      this.activeIndex ++;
      if(this.activeIndex % 2 == 0) {
        this.$nextTick(() => {
          this.drawLine();
        })
      } else {
        this.$nextTick(() => {
          this.drawMonthLine();
        })
      }
      this.dayShow = !this.dayShow;
    }
  }
}

</script>
<stylus>


</stylus>
