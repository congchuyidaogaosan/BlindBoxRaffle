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
          label="用户信息"
          width="200">
          <template slot-scope="scope">
            <div class="user-info">
              <el-avatar 
                :size="40" 
                :src="scope.row.userAvatarUrl"
              />
              <div class="user-detail">
                <div class="nickname">{{ scope.row.userNickname || '未知用户' }}</div>
                <div class="user-id text-gray">ID: {{ scope.row.userId }}</div>
              </div>
            </div>
          </template>
        </el-table-column>

        <el-table-column
          label="抽中款式"
          width="200">
          <template slot-scope="scope">
            <div class="style-info">
              <el-image 
                :src="scope.row.boxStyleImageUrl" 
                :preview-src-list="[scope.row.boxStyleImageUrl]"
                style="width: 50px; height: 50px; border-radius: 4px;"
                fit="cover"
              />
              <div class="style-detail">
                <div class="style-name">{{ scope.row.boxStyleName }}</div>
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
          width="120">
          <template #default="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>

        <el-table-column
          label="创建时间"
          width="180">
          <template #default="scope">
            {{ formatDate(scope.row.createTime) }}
          </template>
        </el-table-column>

        <el-table-column
          label="操作"
          width="120">
          <template #default="scope">
            <el-button
              size="small"
              type="primary"
              @click="handleDetail(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 订单详情弹窗 -->
    <el-dialog title="订单详情" :visible.sync="dialogVisible">
      <div v-if="currentOrder">
        <!-- 用户信息 -->
        <div class="detail-section">
          <h4>用户信息</h4>
          <div class="user-info">
            <el-avatar 
              :size="50" 
              :src="currentOrder.userAvatarUrl"
            />
            <div class="user-detail">
              <div class="nickname">{{ currentOrder.userNickname || '未知用户' }}</div>
              <div class="user-id text-gray">ID: {{ currentOrder.userId }}</div>
            </div>
          </div>
        </div>

        <!-- 订单信息 -->
        <div class="order-info">
          <h4>订单信息</h4>
          <p>订单号：{{ currentOrder.id }}</p>
          <p>创建时间：{{ formatDate(currentOrder.createTime) }}</p>
          <p>订单金额：¥ {{ currentOrder.totalAmount }}</p>
          <p>订单状态：
            <el-tag :type="getStatusType(currentOrder.status)">
              {{ getStatusText(currentOrder.status) }}
            </el-tag>
          </p>
        </div>

        <!-- 款式信息 -->
        <div class="style-section">
          <h4>抽中款式</h4>
          <div class="style-info detail-style">
            <el-image 
              :src="currentOrder.boxStyleImageUrl" 
              :preview-src-list="[currentOrder.boxStyleImageUrl]"
              style="width: 80px; height: 80px; border-radius: 4px;"
            />
            <div class="style-detail">
              <div class="style-name">{{ currentOrder.boxStyleName }}</div>
              <div class="series-name">系列：{{ currentOrder.seriesName }}</div>
              <div class="series-desc text-gray">{{ currentOrder.seriesDescription }}</div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getOrders, getOrderDetails } from '@/api/order'
import { formatDate } from '@/utils/date'

export default {
  name: 'OrdersView',
  data() {
    return {
      loading: false,
      orders: [],
      dialogVisible: false,
      currentOrder: null
    }
  },
  methods: {
    formatDate,
    getStatusType(status) {
      const statusMap = {
        'PENDING': 'warning',
        'COMPLETED': 'success',
        'CANCELLED': 'info',
        'FAILED': 'danger'
      }
      return statusMap[status] || 'info'
    },
    getStatusText(status) {
      const statusMap = {
        'PENDING': '待支付',
        'COMPLETED': '已完成',
        'CANCELLED': '已取消',
        'FAILED': '失败'
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
    async handleDetail(order) {
      this.currentOrder = order
      this.dialogVisible = true
    }
  },
  created() {
    this.fetchOrders()
  }
}
</script>

<style scoped>
.order-info {
  margin-bottom: 20px;
}

.order-info p {
  margin: 10px 0;
}

.item-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}

.item {
  text-align: center;
}

.item img {
  width: 100px;
  height: 100px;
  object-fit: cover;
}

.item-info {
  margin-top: 10px;
}

.price {
  color: #F56C6C;
}

.user-info,
.style-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.user-detail,
.style-detail {
  display: flex;
  flex-direction: column;
}

.nickname,
.style-name {
  font-size: 14px;
  font-weight: 500;
}

.user-id,
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

.detail-section {
  margin-bottom: 24px;
}

h4 {
  margin-bottom: 16px;
  font-weight: 500;
  color: #303133;
}

.detail-style {
  padding: 16px;
  background-color: #f5f7fa;
  border-radius: 8px;
}

.series-desc {
  margin-top: 8px;
  font-size: 12px;
  line-height: 1.4;
}
</style> 