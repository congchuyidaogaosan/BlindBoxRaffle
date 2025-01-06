<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>今日订单</span>
          </div>
          <div class="card-content">
            <div class="card-value">{{ statistics.todayOrders || 0 }}</div>
            <div class="card-label">较昨日 {{ statistics.orderChange > 0 ? '+' : ''}}{{ statistics.orderChange || 0 }}%</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>今日销售额</span>
          </div>
          <div class="card-content">
            <div class="card-value">¥{{ statistics.todaySales || 0 }}</div>
            <div class="card-label">较昨日 {{ statistics.salesChange > 0 ? '+' : ''}}{{ statistics.salesChange || 0 }}%</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>新增用户</span>
          </div>
          <div class="card-content">
            <div class="card-value">{{ statistics.newUsers || 0 }}</div>
            <div class="card-label">较昨日 {{ statistics.userChange > 0 ? '+' : ''}}{{ statistics.userChange || 0 }}%</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>抽奖次数</span>
          </div>
          <div class="card-content">
            <div class="card-value">{{ statistics.draws || 0 }}</div>
            <div class="card-label">较昨日 {{ statistics.drawChange > 0 ? '+' : ''}}{{ statistics.drawChange || 0 }}%</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header">
            <span>销售趋势</span>
          </div>
          <div class="chart-container">
            <v-chart 
              :options="salesChartOptions"
              :autoresize="true"
            />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header">
            <span>热门系列</span>
          </div>
          <div class="chart-container">
            <v-chart 
              :options="seriesChartOptions"
              :autoresize="true"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getOverview, getDrawPreferences, getPopularStyles } from '@/api/statistics'
import ECharts from 'vue-echarts'
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/pie'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/title'
import 'echarts/lib/component/legend'

export default {
  name: 'DashboardView',
  components: {
    'v-chart': ECharts
  },
  data() {
    return {
      loading: false,
      statistics: {
        todayOrders: 0,
        orderChange: 0,
        todaySales: 0,
        salesChange: 0,
        newUsers: 0,
        userChange: 0,
        draws: 0,
        drawChange: 0
      },
      salesChartOptions: {
        title: {
          text: '近7天销售趋势'
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
          data: [],
          type: 'line',
          smooth: true
        }]
      },
      seriesChartOptions: {
        title: {
          text: '系列销量占比'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        series: [{
          type: 'pie',
          radius: '65%',
          center: ['50%', '50%'],
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      }
    }
  },
  created() {
    this.fetchStatistics()
  },
  methods: {
    async fetchStatistics() {
      this.loading = true
      try {
        // 获取概览数据
        const overviewRes = await getOverview()
        this.statistics = overviewRes.data

        // 获取销售趋势数据
        const salesData = overviewRes.data.salesTrend || []
        this.salesChartOptions.xAxis.data = salesData.map(item => item.date)
        this.salesChartOptions.series[0].data = salesData.map(item => item.amount)

        // 获取系列销量数据
        const popularRes = await getPopularStyles()
        this.seriesChartOptions.series[0].data = popularRes.data.map(item => ({
          name: item.name,
          value: item.sales
        }))

      } catch (error) {
        this.$message.error('获取统计数据失败')
      } finally {
        this.loading = false
      }
    }
  }
}
</script>

<style scoped>
.dashboard {
  padding: 20px;
}

.card-content {
  text-align: center;
  padding: 20px 0;
}

.card-value {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 10px;
}

.card-label {
  font-size: 14px;
  color: #909399;
}

.chart-container {
  height: 300px;
  width: 100%;
}

.echarts {
  width: 100%;
  height: 100%;
}
</style> 