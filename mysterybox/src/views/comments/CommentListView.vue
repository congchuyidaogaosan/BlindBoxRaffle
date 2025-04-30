<template>
  <div class="comment-list">
    <el-card class="comment-card">
      <template #header>
        <span>评论列表</span>
        <span v-if="isAdmin" style="color: #F56C6C; margin-left: 10px; font-size: 12px;">[管理员]</span>
      </template>

      <!-- 评论列表 -->
      <div class="comment-items" v-loading="loading">
        <div v-if="comments.length === 0" class="empty-text">
          暂无评论
        </div>
        <div v-else class="comment-item" v-for="comment in comments" :key="comment.id">
          <div class="comment-header">
            <div class="user-info">
              <el-avatar :size="40" :src="defaultAvatar">
                {{ getUserInitial(comment.user) }}
              </el-avatar>
              <span class="user-name">{{ getUserName(comment.user) }}</span>
            </div>
            <div class="rating">
              <el-rate
                v-model="comment.rating"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value}分"
              />
            </div>
          </div>
          <div class="comment-content">
            {{ comment.content }}
          </div>
          <div class="comment-footer">
            <div class="box-style-info">
              <el-image 
                class="box-style-image" 
                :src="comment.boxStyle.imageUrl" 
                fit="cover"
                :preview-src-list="[comment.boxStyle.imageUrl]"
              ></el-image>
              <span class="box-style-name">{{ comment.boxStyle.name }}</span>
            </div>
            <div class="comment-actions">
              <span class="comment-time">{{ formatTime(comment.createTime) }}</span>
              <el-button 
                type="text" 
                size="small" 
                class="delete-btn"
                @click="testDelete(comment)"
              >
                <i class="el-icon-delete"></i> 删除
              </el-button>
            </div>
          </div>
        </div>
      </div>

      <!-- 分页 -->
      <div class="pagination" v-if="total > 0">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        />
      </div>
    </el-card>

    <!-- 删除确认对话框 -->
    <el-dialog
      title="删除评论"
      :visible.sync="deleteDialogVisible"
      width="30%"
    >
      <span>确定要删除这条评论吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmDelete" :loading="deleteLoading">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getCommentList, deleteComment, deleteCommentPost } from '@/api/comment'
import { mapGetters } from 'vuex'

export default {
  name: 'CommentListView',
  props: {
    boxStyleId: {
      type: [Number, String],
      default: null // 允许为空，表示查询所有评论
    }
  },
  data() {
    return {
      loading: false,
      comments: [],
      currentPage: 1,
      pageSize: 10,
      total: 0,
      defaultAvatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      deleteDialogVisible: false,
      deleteLoading: false,
      currentComment: null
    }
  },
  computed: {
    ...mapGetters([
      'userInfo',
      'isAdmin'
    ])
  },
  created() {
    this.fetchComments()
    // 输出调试信息
    console.log('是否管理员:', this.isAdmin)
    console.log('当前用户:', this.userInfo)
  },
  methods: {
    // 测试删除按钮
    testDelete(comment) {
      console.log('测试删除按钮:', comment)
      this.currentComment = comment
      
      // 使用原生confirm作为备用方案
      if (confirm(`确定要删除ID为${comment.id}的评论吗？`)) {
        this.confirmDelete()
      } else {
        // 同时尝试显示dialog
        this.deleteDialogVisible = true
      }
    },
    async fetchComments() {
      this.loading = true
      try {
        const params = {
          page: this.currentPage,
          size: this.pageSize
        }
        
        // 如果传入了boxStyleId，则按盲盒款式ID查询
        if (this.boxStyleId) {
          params.boxStyleId = this.boxStyleId
        }
        
        const res = await getCommentList(params)
        if (res.code === 200) {
          // 处理返回的数组
          this.comments = res.data || []
          this.total = this.comments.length
        }
      } catch (error) {
        console.error('获取评论列表失败:', error)
        this.$message.error('获取评论列表失败')
      } finally {
        this.loading = false
      }
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.fetchComments()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      this.fetchComments()
    },
    // 格式化日期时间
    formatTime(time) {
      if (!time) return '';
      
      // 将日期字符串转换成日期对象
      const date = new Date(time.replace(/-/g, '/'));
      
      // 获取年月日时分秒
      const year = date.getFullYear();
      const month = this.padZero(date.getMonth() + 1);
      const day = this.padZero(date.getDate());
      const hours = this.padZero(date.getHours());
      const minutes = this.padZero(date.getMinutes());
      
      // 返回格式化后的日期字符串
      return `${year}-${month}-${day} ${hours}:${minutes}`;
    },
    // 数字补零
    padZero(num) {
      return num < 10 ? '0' + num : num;
    },
    getUserName(user) {
      if (!user) return '匿名用户'
      return user.nickname || user.username || '用户' + user.id
    },
    getUserInitial(user) {
      if (!user) return 'U'
      const name = this.getUserName(user)
      return name.charAt(0)
    },
    // 处理删除评论
    handleDelete(comment) {
      this.currentComment = comment
      this.deleteDialogVisible = true
    },
    // 确认删除评论
    async confirmDelete() {
      console.log('确认删除:', this.currentComment)
      if (!this.currentComment) return
      
      this.deleteLoading = true
      try {
        console.log('删除ID:', this.currentComment.id)
        
        // 尝试使用DELETE方法删除
        const res = await deleteCommentPost(this.currentComment.id)
        console.log('POST方法删除结果:', res)
        
        if (res && res.code === 200) {
          this.$message.success('删除成功')
          // 重新获取评论列表
          this.fetchComments()
        } else {
          this.$message.error((res && res.message) || '删除失败')
        }
      } catch (error) {
        console.error('删除评论失败:', error)
        this.$message.error('删除评论失败: ' + (error.message || error))
      } finally {
        this.deleteLoading = false
        this.deleteDialogVisible = false
        this.currentComment = null
      }
    }
  }
}
</script>

<style scoped>
.comment-list .comment-card {
  margin-bottom: 20px;
}

.comment-list .comment-items {
  min-height: 200px;
}

.comment-list .empty-text {
  text-align: center;
  color: #909399;
  padding: 40px 0;
}

.comment-list .comment-item {
  padding: 20px 0;
  border-bottom: 1px solid #ebeef5;
}

.comment-list .comment-item:last-child {
  border-bottom: none;
}

.comment-list .comment-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 12px;
}

.comment-list .user-info {
  display: flex;
  align-items: center;
  gap: 12px;
}

.comment-list .user-name {
  font-size: 14px;
  color: #303133;
  font-weight: 500;
}

.comment-list .comment-content {
  font-size: 14px;
  color: #606266;
  line-height: 1.6;
  margin: 12px 0;
}

.comment-list .comment-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.comment-list .box-style-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.comment-list .box-style-image {
  width: 40px;
  height: 40px;
  border-radius: 4px;
  object-fit: cover;
}

.comment-list .box-style-name {
  font-size: 12px;
  color: #606266;
}

.comment-list .comment-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.comment-list .comment-time {
  font-size: 12px;
  color: #909399;
}

.comment-list .delete-btn {
  color: #F56C6C;
  padding: 2px 0;
}

.comment-list .delete-btn:hover {
  color: #f78989;
}

.comment-list .pagination {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}
</style>
