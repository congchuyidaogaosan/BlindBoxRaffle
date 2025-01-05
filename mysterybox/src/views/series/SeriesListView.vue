<template>
  <div class="series-list">
    <div class="operation-bar">
      <el-button type="primary" @click="handleAdd">新增系列</el-button>
    </div>
    
    <el-table :data="seriesList" border v-loading="loading">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="系列名称" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="price" label="价格">
        <template slot-scope="scope">
          ¥{{ scope.row.price }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 1 ? 'success' : 'info'">
            {{ scope.row.status === 1 ? '上架' : '下架' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button 
            size="mini" 
            type="danger" 
            @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 新增/编辑对话框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="80px">
        <el-form-item label="系列名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="form.description"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number v-model="form.price" :precision="2" :step="0.1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="状态">
          <el-switch v-model="form.status" :active-value="1" :inactive-value="0" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getSeriesList, createSeries, updateSeries, deleteSeries } from '@/api/series'

export default {
  name: 'SeriesListView',
  data() {
    return {
      loading: false,
      seriesList: [],
      dialogVisible: false,
      dialogTitle: '',
      form: {
        name: '',
        description: '',
        price: 0,
        status: 1
      },
      rules: {
        name: [
          { required: true, message: '请输入系列名称', trigger: 'blur' },
          { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
        ],
        description: [
          { required: true, message: '请输入系列描述', trigger: 'blur' }
        ],
        price: [
          { required: true, message: '请输入价格', trigger: 'blur' }
        ]
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
        const res = await getSeriesList()
        this.seriesList = res.data
      } catch (error) {
        this.$message.error('获取系列列表失败')
      } finally {
        this.loading = false
      }
    },
    
    handleAdd() {
      this.dialogTitle = '新增系列'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.form.resetFields()
        this.form = {
          name: '',
          description: '',
          price: 0,
          status: 1
        }
      })
    },
    
    handleEdit(row) {
      this.dialogTitle = '编辑系列'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.form.resetFields()
        this.form = { ...row }
      })
    },
    
    async handleSubmit() {
      this.$refs.form.validate(async valid => {
        if (valid) {
          try {
            if (this.form.id) {
              await updateSeries(this.form.id, this.form)
              this.$message.success('更新成功')
            } else {
              await createSeries(this.form)
              this.$message.success('创建成功')
            }
            this.dialogVisible = false
            this.fetchData()
          } catch (error) {
            this.$message.error('操作失败')
          }
        }
      })
    },
    
    async handleDelete(row) {
      try {
        await this.$confirm('确认删除该系列吗？')
        await deleteSeries(row.id)
        this.$message.success('删除成功')
        this.fetchData()
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('删除失败')
        }
      }
    }
  }
}
</script>

<style scoped>
.series-list {
  padding: 20px;
}

.operation-bar {
  margin-bottom: 20px;
}
</style> 