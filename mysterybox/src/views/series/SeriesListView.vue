<template>
  <div class="series-list">
    <div class="filter-container">
      <el-form :inline="true" :model="queryParams" class="search-form">
        <el-form-item label="系列名称">
          <el-input
            v-model="queryParams.name"
            placeholder="请输入系列名称"
            clearable
            @keyup.enter.native="handleSearch"
          />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
            <el-option label="上架" :value="1" />
            <el-option label="下架" :value="0" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSearch">查询</el-button>
          <el-button @click="handleReset">重置</el-button>
        </el-form-item>
      </el-form>
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
      <el-table-column label="系列图片" width="120">
        <template slot-scope="scope">
          <el-image 
            :src="scope.row.imageUrl" 
            style="width: 80px; height: 45px; object-fit: cover;"
            :preview-src-list="[scope.row.imageUrl]">
          </el-image>
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
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="系列名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" v-model="form.description"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number 
            v-model="form.price" 
            :precision="2" 
            :step="0.1" 
            :min="0"
            controls-position="right">
          </el-input-number>
        </el-form-item>
        <el-form-item label="系列图片" prop="imageUrl">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :show-file-list="false"
            :on-success="handleUploadSuccess"
            :before-upload="beforeUpload">
            <img v-if="form.imageUrl" :src="form.imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
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
      queryParams: {
        name: '',
        status: '',
        pageNum: 1,
        pageSize: 10
      },
      form: {
        name: '',
        description: '',
        price: 0,
        imageUrl: '',
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
        ],
        imageUrl: [{ required: true, message: '请上传系列图片', trigger: 'change' }]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    handleSearch() {
      this.queryParams.pageNum = 1
      this.fetchData()
    },

    handleReset() {
      this.queryParams = {
        name: '',
        status: '',
        pageNum: 1,
        pageSize: 10
      }
      this.fetchData()
    },

    async fetchData() {
      this.loading = true
      try {
        const res = await getSeriesList(this.queryParams)
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
          imageUrl: '',
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
    },
    
    handleUploadSuccess(res) {
      if (res.code === 200) {
        this.form.imageUrl = res.data
        this.$message.success('上传成功')
      } else {
        this.$message.error(res.message || '上传失败')
      }
    },
    
    beforeUpload(file) {
      const isImage = file.type.startsWith('image/')
      const isLt2M = file.size / 1024 / 1024 < 2

      // 检查图片尺寸
      return new Promise((resolve, reject) => {
        const image = new Image()
        image.src = URL.createObjectURL(file)
        image.onload = () => {
          // 检查宽高比是否接近 16:9
          const ratio = image.width / image.height
          const targetRatio = 16 / 9
          const tolerance = 0.1 // 允许的误差范围
          
          if (Math.abs(ratio - targetRatio) > tolerance) {
            this.$message.error('请上传 16:9 比例的图片!')
            reject(false)
          }
          
          if (!isImage) {
            this.$message.error('只能上传图片文件!')
            reject(false)
          }
          if (!isLt2M) {
            this.$message.error('图片大小不能超过 2MB!')
            reject(false)
          }
          
          resolve(true)
        }
      })
    }
  }
}
</script>

<style scoped>
.series-list {
  padding: 20px;
}

.filter-container {
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-form {
  flex: 1;
  margin-right: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 160px;
  height: 90px;
  line-height: 90px;
  text-align: center;
}
.avatar {
  width: 160px;
  height: 90px;
  display: block;
  object-fit: cover;
}
</style> 