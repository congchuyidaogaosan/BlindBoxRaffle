<template>
  <div class="user-list">
    <el-table :data="users" style="width: 100%">
      <el-table-column label="用户信息" width="250">
        <template #default="scope">
          <div class="user-info">
            <el-avatar 
              :size="40" 
              :src="scope.row.avatarUrl"
            />
            <div class="user-detail">
              <div class="nickname">{{ scope.row.nickname }}</div>
              <div class="openid text-gray">{{ scope.row.openId }}</div>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column prop="balance" label="余额" width="120">
        <template #default="scope">
          ¥{{ scope.row.balance }}
        </template>
      </el-table-column>

      <el-table-column prop="role" label="角色" width="120">
        <template #default="scope">
          <el-tag :type="getRoleType(scope.row.role)">
            {{ getRoleText(scope.row.role) }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="createTime" label="注册时间" width="180">
        <template #default="scope">
          {{ formatDate(scope.row.createTime) }}
        </template>
      </el-table-column>

      <el-table-column label="操作" width="150">
        <template #default="scope">
          <el-button 
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getUsers, deleteUser } from '@/api/user'
import { formatDate } from '@/utils/date'

export default {
  name: 'UserList',
  data() {
    return {
      users: []
    }
  },
  created() {
    this.fetchUsers()
  },
  methods: {
    async fetchUsers() {
      try {
        const res = await getUsers()
        if (res.code === 200) {
          this.users = res.data
        }
      } catch (error) {
        console.error('获取用户列表失败:', error)
        this.$message.error('获取用户列表失败')
      }
    },
    getRoleType(role) {
      const roleMap = {
        'ADMIN': 'danger',
        'USER': 'success'
      }
      return roleMap[role] || 'info'
    },
    getRoleText(role) {
      const roleMap = {
        'ADMIN': '管理员',
        'USER': '普通用户'
      }
      return roleMap[role] || role
    },
    formatDate(date) {
      return formatDate(new Date(date))
    },
    handleDelete(user) {
      this.$confirm('确认删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        try {
          const res = await deleteUser(user.id)
          if (res.code === 200) {
            this.$message.success('删除成功')
            this.fetchUsers() // 重新加载列表
          } else {
            this.$message.error(res.msg || '删除失败')
          }
        } catch (error) {
          console.error('删除用户失败:', error)
          this.$message.error('删除用户失败')
        }
      }).catch(() => {
        // 取消删除
      })
    },
    getStatusText(status) {
      const statusMap = {
        '1': '正常',
        '0': '禁用',
        '-1': '已删除'
      }
      return statusMap[status] || '未知'
    }
  }
}
</script>

<style scoped>
.user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.user-detail {
  display: flex;
  flex-direction: column;
}

.nickname {
  font-size: 14px;
  font-weight: 500;
}

.openid {
  font-size: 12px;
  color: #909399;
}

.text-gray {
  color: #909399;
}
</style> 