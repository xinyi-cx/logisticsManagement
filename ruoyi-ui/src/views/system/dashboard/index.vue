<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="查询批次" prop="ref1">
        <el-input
          v-model="queryParams.batchNo"
          placeholder="请输入查询批次"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    </el-form>

    <div class="statusChart" id="statusChart" :style="{width: '100%', height: '600px'}"></div>
  </div>
</template>

<script>
import { statistics } from "@/api/shippingOrder/package";
import echarts from 'echarts';
require('echarts/theme/macarons') // echarts theme

export default {
  name: 'dashboard',
  data() {
    return {
      echartsData:{},
      paramDte:"",
      //查询参数
      queryParams: {
        batchNo: null,
      },
      showSearch: true
    }
  },
  created() {
    this.getEcartsData();
  },
  mounted() {
    this.$nextTick(() => {
      this.drawLine();
    })
  },
  methods: {
    getEcartsData() {
      statistics(this.paramDte).then(response => {
        this.echartsData = response.data;
      });
    },
    drawLine() {
      // 初始化echarts实例
      let mychart = echarts.init(document.getElementById('statusChart'));
      let option = {
        title: {
          text: '今日各订单状态柱形图',
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
          // data: this.echartsData.xAxisData
          type: 'category',
          data: ['新增订单', '激活订单', '签收订单', '异常订单', '拒签订单']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          type: "bar",
          barWidth: '20%',
          showBackground: true,
          itemStyle: {
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
          // data: this.echartsData.seriesData
          data: [80, 60, 70, 10, 50]

        }]
      };
      //绘制图表
      mychart.setOption(option);
    }
  }
}

</script>
<stylus>


</stylus>
