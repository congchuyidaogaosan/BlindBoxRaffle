<template>
  <div class="dashboard">
    <el-row :gutter="20" style="display: flex; flex-wrap: wrap; justify-content: space-between;">
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>今日订单</span>
          </div>
          <div class="card-content">
            <div class="card-value">{{ statistics.gettoorders || 0 }}</div>
            <!-- <div class="card-label">较昨日 {{ statistics.orderChange > 0 ? '+' : ''}}{{ statistics.orderChange || 0 }}%</div> -->
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>今日销售额</span>
          </div>
          <div class="card-content">
            <div class="card-value">¥{{ statistics.gettodaysum || 0 }}</div>
            <!-- <div class="card-label">较昨日 {{ statistics.salesChange > 0 ? '+' : ''}}{{ statistics.salesChange || 0 }}%</div> -->
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">
            <span>新增用户</span>
          </div>
          <div class="card-content">
            <div class="card-value">{{ statistics.getusers || 0 }}</div>
            <!-- <div class="card-label">较昨日 {{ statistics.userChange > 0 ? '+' : ''}}{{ statistics.userChange || 0 }}%</div> -->
          </div>
        </el-card>
      </el-col>
      
    </el-row>

    
  </div>
</template>

<script>
import { getOverview,  } from '@/api/statistics'

export default {
  name: 'DashboardView',

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