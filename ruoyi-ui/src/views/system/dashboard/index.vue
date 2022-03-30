<template>
  <div id="mychart" :style="{width: '100%', height: '300px'}">为啥不显示？</div>

</template>

<script>
import echarts from 'echarts'
//require('echarts/theme/macarons') // echarts theme

export default {
  name: 'dashboard',
  data() {
    return {

    }
  },
  mounted() {
    this.$nextTick(() => {
      this.drawLine();
    })
  },
  methods: {
    drawLine() {
      // 初始化echarts实例
      let mychart = echarts.init(document.getElementById('mychart'));
      var option = {
        title: {
          text: '在Vue中使用echarts'
        },
        legend: {
          data: ['销量']
        },
        tooltip: {},
        xAxis: {
          data: ["2022-03-08", "2022-03-09", "2022-03-10", "2022-03-11", "2022-03-12", "2022-03-13"]
        },
        yAxis: {},
        series: [{
          name: "销量",
          type: "bar",
          data: [5, 20, 10, 30, 15, 50]
        }]
      }
      //绘制图表
      mychart.setOption(option);
    }
  }
}

</script>
