<template>
  <div class="user-list">
    <el-card>
      <div slot="header">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="用户名">
            <el-input v-model="searchForm.username" placeholder="请输入用户名" clearable />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSearch">查询</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table :data="userList" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="nickname" label="昵称" />
        <el-table-column prop="balance" label="余额" width="120">
          <template slot-scope="scope">
            ¥{{ scope.row.balance }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '正常' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="注册时间" width="180" />
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button type="text" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button 
              type="text" 
              :type="scope.row.status === 1 ? 'danger' : 'primary'"
              @click="handleToggleStatus(scope.row)"
            >
              {{ scope.row.status === 1 ? '禁用' : '启用' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          :current-page="page"
          :page-size="pageSize"
          :total="total"
          layout="total, prev, pager, next"
          @current-change="handlePageChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'UserListView',
  data() {
    return {
      loading: false,
      searchForm: {
        username: ''
      },
      userList: [],
      page: 1,
      pageSize: 10,
      total: 0
    }
  },
  created() {
    this.fetchUserList()
  },
  methods: {
    async fetchUserList() {
      this.loading = true
      try {
        // TODO: 调用接口获取用户列表
      } catch (error) {
        this.$message.error('获取用户列表失败')
      } finally {
        this.loading = false
      }
    },
    handleSearch() {
      this.page = 1
      this.fetchUserList()
    },
    handleEdit(row) {
      // TODO: 实现编辑用户功能
    },
    handleToggleStatus(row) {
      // TODO: 实现启用/禁用用户功能
    },
    handlePageChange(page) {
      this.page = page
      this.fetchUserList()
    }
  }
}
</script>

<style scoped>
.user-list {
  padding: 20px;
}
.pagination {
  margin-top: 20px;
  text-align: right;
}
</style> 