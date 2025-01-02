<template>
  <div class="statistics">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header">
            <span>销售统计</span>
          </div>
          <div class="statistics-content">
            <div class="item">
              <div class="label">总销售额</div>
              <div class="value">￥{{ salesStats.totalSales }}</div>
            </div>
            <div class="item">
              <div class="label">订单数</div>
              <div class="value">{{ salesStats.totalOrders }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="box-card">
          <div slot="header">
            <span>用户活动</span>
          </div>
          <div class="statistics-content">
            <div class="item">
              <div class="label">新增用户</div>
              <div class="value">{{ userActivity.newUsers }}</div>
            </div>
            <div class="item">
              <div class="label">活跃用户</div>
              <div class="value">{{ userActivity.activeUsers }}</div>
            </div>
            <div class="item">
              <div class="label">抽取次数</div>
              <div class="value">{{ userActivity.totalDraws }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    
    <el-row :gutter="20" class="chart-row">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header">
            <span>热门款式</span>
          </div>
          <div class="chart-container">
            <ve-histogram :data="popularStylesChart"></ve-histogram>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header">
            <span>抽取偏好</span>
          </div>
          <div class="chart-container">
            <ve-pie :data="drawPreferencesChart"></ve-pie>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getStatistics } from '@/api/statistics'

export default {
  data() {
    return {
      salesStats: {},
      userActivity: {},
      popularStylesChart: {
        columns: ['款式', '销量'],
        rows: []
      },
      drawPreferencesChart: {
        columns: ['系列', '抽取次数'],
        rows: []
      }
    }
  },
  
  methods: {
    async fetchData() {
      try {
        const [
          salesRes,
          userActivityRes,
          popularStylesRes,
          drawPreferencesRes
        ] = await Promise.all([
          getStatistics('/sales'),
          getStatistics('/user-activity'),
          getStatistics('/popular-styles'),
          getStatistics('/draw-preferences')
        ])
        
        this.salesStats = salesRes.data
        this.userActivity = userActivityRes.data
        this.popularStylesChart.rows = popularStylesRes.data
        this.drawPreferencesChart.rows = drawPreferencesRes.data
      } catch (error) {
        this.$message.error('获取统计数据失败')
      }
    }
  },
  
  created() {
    this.fetchData()
  }
}
</script>

<style lang="scss" scoped>
.statistics {
  padding: 20px;
  
  .box-card {
    margin-bottom: 20px;
  }
  
  .statistics-content {
    .item {
      margin-bottom: 15px;
      
      .label {
        color: #909399;
        font-size: 14px;
      }
      
      .value {
        font-size: 24px;
        color: #303133;
        margin-top: 5px;
      }
    }
  }
  
  .chart-container {
    height: 300px;
  }
}
</style> 