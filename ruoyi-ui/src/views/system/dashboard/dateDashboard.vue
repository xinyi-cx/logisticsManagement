<template>
  <div class="app-container">
    <div class="day-dashboard">
      <el-form ref="queryForm" :inline="true" label-width="68px">
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
        </el-form-item>
      </el-form>

      <div class="statusChart" id="dateChart" :style="{width: '100%', height: '600px'}"></div>
    </div>
  </div>
</template>

<script>
import { statistics } from "@/api/shippingOrder/package";
import echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme

export default {
  name: 'dateDashboard',
  data() {
    return {
      echartsData:{
        xAxisData: [],
        seriesData: []
      },
      paramDte: null,
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
      }
    }
  },
  created() {
    this.getCurrentDate();
    this.getEcartsData();
  },
  mounted() {
    this.$nextTick(() => {
      // this.drawLine();
      this.getEcartsData();
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
    drawLine(barSize) {
      // 初始化echarts实例
      let mychart = echarts.init(document.getElementById('dateChart'));
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
    // 重置事件
    resetQuery() {
      this.getCurrentDate();
      this.getEcartsData();
    }
  }
}

</script>
<stylus>


</stylus>
