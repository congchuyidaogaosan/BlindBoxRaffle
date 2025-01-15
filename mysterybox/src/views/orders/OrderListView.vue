<template>
  <div class="orders">
    <el-card>
      <div slot="header">
        <span>订单记录</span>
      </div>
      <el-table
        :data="orders"
        style="width: 100%"
        v-loading="loading"
      >
        <el-table-column
          label="订单号"
          prop="id"
          width="180"
        />

        <el-table-column
          label="抽中款式"
          width="200">
          <template slot-scope="scope">
            <div class="style-info">
              <el-image 
                :src="scope.row.styleImageUrl" 
                :preview-src-list="[scope.row.styleImageUrl]"
                style="width: 50px; height: 50px; border-radius: 4px;"
                fit="cover"
              />
              <div class="style-detail">
                <div class="style-name">{{ scope.row.styleName }}</div>
                <div class="series-name text-gray">{{ scope.row.seriesName }}</div>
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column
          label="金额"
          width="120">
          <template slot-scope="scope">
            <span class="amount">¥{{ scope.row.totalAmount }}</span>
          </template>
        </el-table-column>

        <el-table-column
          label="状态"
          width="100">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          width="180"
          prop="createTime"
        />

        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import { getOrders, deleteOrder } from '@/api/order'

export default {
  name: 'OrdersView',
  data() {
    return {
      loading: false,
      orders: []
    }
  },
  methods: {
    getStatusType(status) {
      const statusMap = {
        PENDING: 'warning',
        COMPLETED: 'success',
        CANCELLED: 'info',
        FAILED: 'danger'
      }
      return statusMap[status] || 'info'
    },
    getStatusText(status) {
      const statusMap = {
        PENDING: '待支付',
        COMPLETED: '已完成',
        CANCELLED: '已取消',
        FAILED: '失败'
      }
      return statusMap[status] || status
    },
    async fetchOrders() {
      this.loading = true
      try {
        const res = await getOrders()
        if (res.code === 200) {
          this.orders = res.data
        }
      } catch (error) {
        this.$message.error('获取订单列表失败')
      } finally {
        this.loading = false
      }
    },
    async handleDelete(order) {
      try {
        await this.$confirm('确认删除该订单?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        })
        
        const res = await deleteOrder(order.id)
        if (res.code === 200) {
          this.$message.success('删除成功')
          this.fetchOrders()
        }
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('删除失败')
        }
      }
    }
  },
  created() {
    this.fetchOrders()
  }
}
</script>

<style scoped>
.style-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.style-detail {
  display: flex;
  flex-direction: column;
}

.style-name {
  font-size: 14px;
  font-weight: 500;
}

.series-name {
  font-size: 12px;
}

.text-gray {
  color: #909399;
}

.amount {
  font-weight: 500;
  color: #f56c6c;
}
</style> 