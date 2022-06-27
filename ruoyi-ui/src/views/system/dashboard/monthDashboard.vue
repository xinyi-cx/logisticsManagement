<template>
  <div class="app-container">
    <div class="day-dashboard">
      <el-form ref="queryForm" :inline="true" label-width="68px">
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
        </el-form-item>
      </el-form>

      <div class="monthChart" id="monthChart" :style="{width: '100%', height: '600px'}"></div>
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
      monthEchartsData:{
        xAxisData: [],
        seriesData: []
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
    this.getMonthEcartsData();
  },
  mounted() {
    this.$nextTick(() => {
      // this.drawMonthLine();
      this.getMonthEcartsData();
    })
  },
  methods: {
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
    }
  }
}

</script>
<stylus>


</stylus>
