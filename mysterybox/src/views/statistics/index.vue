<template>
  <div class="statistics">
    <el-card>
      <div slot="header">
        <el-form :inline="true">
          <el-form-item label="时间范围">
            <el-date-picker
              v-model="dateRange"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              @change="handleDateChange"
            />
          </el-form-item>
        </el-form>
      </div>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">
              <span>销售统计</span>
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
              <span>用户增长</span>
            </div>
            <div class="chart-container">
              <v-chart 
                :options="userChartOptions"
                :autoresize="true"
              />
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-row :gutter="20" style="margin-top: 20px">
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">
              <span>系列销量排行</span>
            </div>
            <el-table :data="seriesRanking" size="small" v-loading="loading">
              <el-table-column prop="name" label="系列名称" />
              <el-table-column prop="sales" label="销量" width="100" />
              <el-table-column prop="amount" label="销售额" width="120">
                <template slot-scope="scope">
                  ¥{{ scope.row.amount }}
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">
              <span>款式中奖率</span>
            </div>
            <el-table :data="styleRanking" size="small" v-loading="loading">
              <el-table-column prop="name" label="款式名称" />
              <el-table-column prop="drawCount" label="抽中次数" width="100" />
              <el-table-column prop="probability" label="中奖率" width="100">
                <template slot-scope="scope">
                  {{ scope.row.probability }}%
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
import { getOverview, getDrawPreferences, getPopularStyles } from '@/api/statistics'
import VChart from 'vue-echarts'

export default {
  name: 'StatisticsView',
  components: {
    VChart
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
  },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        // 获取概览数据
        const [overviewRes, preferencesRes, popularRes] = await Promise.all([
          getOverview({
            startDate: this.dateRange[0],
            endDate: this.dateRange[1]
          }),
          getDrawPreferences(),
          getPopularStyles()
        ])

        // 更新销售趋势图表
        const salesData = overviewRes.data.salesTrend || []
        this.salesChartOptions.xAxis.data = salesData.map(item => item.date)
        this.salesChartOptions.series[0].data = salesData.map(item => item.amount)

        // 更新用户增长图表
        const userData = overviewRes.data.userTrend || []
        this.userChartOptions.xAxis.data = userData.map(item => item.date)
        this.userChartOptions.series[0].data = userData.map(item => item.count)

        // 更新系列排行
        this.seriesRanking = popularRes.data

        // 更新款式中奖率
        this.styleRanking = preferencesRes.data

      } catch (error) {
        this.$message.error('获取统计数据失败')
      } finally {
        this.loading = false
      }
    },
    handleDateChange() {
      this.fetchData()
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