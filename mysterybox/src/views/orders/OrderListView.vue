<template>
  <div class="orders">
    <el-card>
      <div slot="header">
        <span>订单记录</span>
      </div>
      <el-table
        :data="orderList"
        style="width: 100%"
        v-loading="loading"
      >
        <el-table-column
          prop="id"
          label="订单号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="创建时间"
          width="180">
          <template slot-scope="scope">
            {{ formatDate(scope.row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column
          prop="totalAmount"
          label="金额"
          width="120">
          <template slot-scope="scope">
            ¥ {{ scope.row.totalAmount }}
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          width="120">
          <template slot-scope="scope">
            <el-tag :type="getStatusType(scope.row.status)">
              {{ getStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleDetail(scope.row)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 订单详情弹窗 -->
    <el-dialog title="订单详情" :visible.sync="dialogVisible">
      <div v-if="currentOrder">
        <div class="order-info">
          <p>订单号：{{ currentOrder.id }}</p>
          <p>创建时间：{{ formatDate(currentOrder.createTime) }}</p>
          <p>订单金额：¥ {{ currentOrder.totalAmount }}</p>
          <p>订单状态：{{ getStatusText(currentOrder.status) }}</p>
        </div>
        <div class="order-items">
          <h4>抽中物品</h4>
          <div class="item-list">
            <div class="item" v-for="item in currentOrder.items" :key="item.id">
              <img :src="item.imageUrl" :alt="item.name">
              <div class="item-info">
                <p>{{ item.name }}</p>
                <p class="price">¥ {{ item.price }}</p>
              </div>
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
      orderList: [],
      dialogVisible: false,
      currentOrder: null
    }
  },
  methods: {
    formatDate,
    getStatusType(status) {
      const types = {
        'PENDING': 'warning',
        'COMPLETED': 'success',
        'CANCELLED': 'info'
      }
      return types[status] || 'info'
    },
    getStatusText(status) {
      const texts = {
        'PENDING': '待支付',
        'COMPLETED': '已完成',
        'CANCELLED': '已取消'
      }
      return texts[status] || status
    },
    async fetchOrders() {
      this.loading = true
      try {
        const res = await getOrders()
        this.orderList = res.data
      } catch (error) {
        this.$message.error('获取订单列表失败')
      } finally {
        this.loading = false
      }
    },
    async handleDetail(order) {
      try {
        const res = await getOrderDetails(order.id)
        this.currentOrder = res.data
        this.dialogVisible = true
      } catch (error) {
        this.$message.error('获取订单详情失败')
      }
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
</style> 