<template>
  <div class="series-list">
    <div class="operation-bar">
      <el-button type="primary" @click="handleAdd">新增系列</el-button>
    </div>
    
    <el-table :data="seriesList" border>
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="系列名称" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          {{ scope.row.status === 1 ? '上架' : '下架' }}
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
      <el-form :model="form" label-width="80px">
        <el-form-item label="系列名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="form.description"></el-input>
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
  data() {
    return {
      seriesList: [],
      dialogVisible: false,
      dialogTitle: '',
      form: {
        name: '',
        description: '',
        status: 1
      },
      loading: false
    }
  },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        const response = await getSeriesList()
        this.seriesList = response.data
      } catch (error) {
        this.$message.error('获取系列列表失败')
      } finally {
        this.loading = false
      }
    },
    
    async handleSubmit() {
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
  },
  created() {
    this.fetchData()
  }
}
</script> 