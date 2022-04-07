<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
      paramDte: null,
      //查询参数
      queryParams: {
        //批次字段是什么？
        batchNo: null,
      },
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
      }
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
      let titleDate = this.paramDte ? this.paramDte : '今日';
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
          // data: this.echartsData.seriesData
          data: [80, 60, 70, 10, 50]

        }]
      };
      //绘制图表
      mychart.setOption(option);
      mychart.on('click', function(params) {
        console.log(params.name);

      });
    },
    // 搜索事件
    handleQuery() {
      this.getEcartsData();
    },
    // 重置事件
    resetQuery() {
      this.paramDte = null;
      this.getEcartsData();
    }
  }
}

</script>
<stylus>


</stylus>
