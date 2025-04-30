<template>
  <div class="statistics">
    <el-card class="overview-chart">
      <div slot="header">
        <span>数据概览</span>
      </div>
      <div id="overviewChart" style="width: 100%; height: 400px;"></div>
    </el-card>
  </div>
</template>

<script>
import { getOverview } from '@/api/statistics'
import * as echarts from 'echarts'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, PieChart } from 'echarts/charts'
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
} from 'echarts/components'

use([
  CanvasRenderer,
  LineChart,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
])

export default {
  name: 'StatisticsView',
  components: {
    'v-chart': {
      props: ['option'],
      mounted() {
        this.chart = echarts.init(this.$el)
        this.chart.setOption(this.option)
      },
      watch: {
        option: {
          handler(newVal) {
            this.chart.setOption(newVal)
          },
          deep: true
        }
      },
      beforeDestroy() {
        this.chart.dispose()
      }
    }
  },
  data() {
    return {
      loading: false,
      dateRange: [],
      seriesRanking: [],
      styleRanking: [],
      salesChartOptions: {
        title: {
          text: '销售趋势'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '销售额',
          type: 'line',
          smooth: true,
          data: []
        }]
      },
      userChartOptions: {
        title: {
          text: '用户增长'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '新增用户',
          type: 'line',
          smooth: true,
          data: []
        }]
      }
    }
  },
  created() {
    this.fetchData()
    this.fetchOverview()
  },
  mounted() {
    window.addEventListener('resize', this.resizeChart)
  },
  beforeUnmount() {
    window.removeEventListener('resize', this.resizeChart)
    const chart = echarts.getInstanceByDom(document.getElementById('overviewChart'))
    if (chart) {
      chart.dispose()
    }
  },
  methods: {
    resizeChart() {
      const chart = echarts.getInstanceByDom(document.getElementById('overviewChart'))
      if (chart) {
        chart.resize()
      }
    },
    async fetchData() {
      this.loading = true
      try {
        // 获取概览数据
        const overviewRes = await getOverview()
        console.log(overviewRes)
        // 更新销售趋势图表
        const salesData = overviewRes.data.salesTrend || []
        this.salesChartOptions.xAxis.data = salesData.map(item => item.date)
        this.salesChartOptions.series[0].data = salesData.map(item => item.amount)

        // 更新用户增长图表
        const userData = overviewRes.data.userTrend || []
        this.userChartOptions.xAxis.data = userData.map(item => item.date)
        this.userChartOptions.series[0].data = userData.map(item => item.count)

        // 更新系列排行
        this.seriesRanking = overviewRes.data.seriesRanking

        // 更新款式中奖率
        this.styleRanking = overviewRes.data.styleRanking

      } catch (error) {
        this.$message.error('获取统计数据失败')
      } finally {
        this.loading = false
      }
    },
    handleDateChange() {
      this.fetchData()
    },
    updateOverviewChart(data) {
      const option = {
        title: {
          text: '订单与销售额统计',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function(params) {
            let result = params[0].name + '<br/>';
            params.forEach(param => {
              const value = param.seriesName === '订单数' ? param.value + ' 单' : '¥' + param.value;
              result += param.marker + param.seriesName + ': ' + value + '<br/>';
            });
            return result;
          }
        },
        legend: {
          data: ['订单数', '销售额'],
          top: 30
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true,
          top: 80
        },
        xAxis: {
          type: 'category',
          data: ['组别1', '组别2'],
          axisLabel: {
            interval: 0
          }
        },
        yAxis: [
          {
            type: 'value',
            name: '订单数',
            position: 'left',
            axisLabel: {
              formatter: '{value} 单'
            }
          },
          {
            type: 'value',
            name: '销售额',
            position: 'right',
            axisLabel: {
              formatter: '¥{value}'
            }
          }
        ],
        series: [
          {
            name: '订单数',
            type: 'bar',
            data: data.map(item => item.sheets),
            itemStyle: {
              color: '#409EFF'
            }
          },
          {
            name: '销售额',
            type: 'bar',
            yAxisIndex: 1,
            data: data.map(item => item.totalmoney),
            itemStyle: {
              color: '#67C23A'
            }
          }
        ]
      };
      
      const chart = echarts.init(document.getElementById('overviewChart'));
      chart.setOption(option);
    },
    
    async fetchOverview() {
      try {
        const overviewRes = await getOverview()
        if (overviewRes.code === 200) {
          this.updateOverviewChart(overviewRes.data)
        }
      } catch (error) {
        console.error('获取概览数据失败:', error)
      }
    }
  }
}
</script>

<style scoped>
.statistics {
  padding: 20px;
}

.chart-container {
  height: 300px;
  width: 100%;
}

.echarts {
  width: 100%;
  height: 100%;
}

.el-table {
  margin-top: 10px;
}
</style> 