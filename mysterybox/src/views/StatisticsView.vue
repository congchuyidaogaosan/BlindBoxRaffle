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
              <!-- TODO: 添加销售统计图表 -->
            </div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="hover">
            <div slot="header">
              <span>用户增长</span>
            </div>
            <div class="chart-container">
              <!-- TODO: 添加用户增长图表 -->
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
            <el-table :data="seriesRanking" size="small">
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
            <el-table :data="styleRanking" size="small">
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
export default {
  name: 'StatisticsView',
  data() {
    return {
      loading: false,
      dateRange: [],
      seriesRanking: [],
      styleRanking: []
    }
  },
  created() {
    this.fetchStatistics()
  },
  methods: {
    async fetchStatistics() {
      this.loading = true
      try {
        // TODO: 调用接口获取统计数据
      } catch (error) {
        this.$message.error('获取统计数据失败')
      } finally {
        this.loading = false
      }
    },
    handleDateChange() {
      this.fetchStatistics()
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
}
</style> 