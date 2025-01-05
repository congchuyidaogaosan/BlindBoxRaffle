<template>
  <div class="style-list">
    <div class="operation-bar">
      <el-button type="primary" @click="handleAdd">新增款式</el-button>
    </div>
    
    <el-table :data="styleList" border v-loading="loading">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="name" label="款式名称" />
      <el-table-column prop="series.name" label="所属系列" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="stock" label="库存" />
      <el-table-column prop="probability" label="抽取概率" />
      <el-table-column label="预览图">
        <template slot-scope="scope">
          <el-image 
            :src="scope.row.imageUrl" 
            style="width: 50px; height: 50px"
            :preview-src-list="[scope.row.imageUrl]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          {{ scope.row.status === 1 ? '上架' : '下架' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增/编辑对话框 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="所属系列" prop="seriesId">
          <el-select v-model="form.seriesId" placeholder="请选择系列">
            <el-option
              v-for="item in seriesList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="款式名称" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input-number v-model="form.price" :precision="2" :step="0.1" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input-number v-model="form.stock" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="抽取概率" prop="probability">
          <el-input-number 
            v-model="form.probability" 
            :precision="2" 
            :step="0.01" 
            :min="0"
            :max="100">
          </el-input-number>
        </el-form-item>
        <el-form-item label="预览图" prop="imageUrl">
          <el-upload
            class="avatar-uploader"
            :action="uploadUrl"
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
import { getSeriesList } from '@/api/series'
import { getStyleList, createStyle, updateStyle, deleteStyle } from '@/api/style'

export default {
  data() {
    return {
      styleList: [],
      seriesList: [],
      dialogVisible: false,
      dialogTitle: '',
      loading: false,
      uploadUrl: process.env.VUE_APP_BASE_API + '/upload',
      form: {
        seriesId: '',
        name: '',
        price: 0,
        stock: 0,
        probability: 0,
        imageUrl: '',
        status: 1
      },
      rules: {
        seriesId: [{ required: true, message: '请选择系列', trigger: 'change' }],
        name: [{ required: true, message: '请输入款式名称', trigger: 'blur' }],
        price: [{ required: true, message: '请输入价格', trigger: 'blur' }],
        imageUrl: [{ required: true, message: '请上传预览图', trigger: 'change' }]
      }
    }
  },
  methods: {
    async fetchData() {
      this.loading = true
      try {
        const [styleRes, seriesRes] = await Promise.all([
          getStyleList(),
          getSeriesList()
        ])
        this.styleList = styleRes.data
        this.seriesList = seriesRes.data
      } catch (error) {
        this.$message.error('获取数据失败')
      } finally {
        this.loading = false
      }
    },
    
    handleAdd() {
      this.dialogTitle = '新增款式'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.form.resetFields()
        this.form = {
          seriesId: '',
          name: '',
          price: 0,
          stock: 0,
          probability: 0,
          imageUrl: '',
          status: 1
        }
      })
    },
    
    handleEdit(row) {
      this.dialogTitle = '编辑款式'
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs.form.resetFields()
        this.form = {
          ...row,
          seriesId: row.series.id
        }
      })
    },
    
    async handleSubmit() {
      this.$refs.form.validate(async valid => {
        if (valid) {
          try {
            if (this.form.id) {
              await updateStyle(this.form.id, this.form)
              this.$message.success('更新成功')
            } else {
              await createStyle(this.form)
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
        await this.$confirm('确认删除该款式吗？')
        await deleteStyle(row.id)
        this.$message.success('删除成功')
        this.fetchData()
      } catch (error) {
        if (error !== 'cancel') {
          this.$message.error('删除失败')
        }
      }
    },
    
    handleUploadSuccess(res) {
      this.form.imageUrl = res.url
    },
    
    beforeUpload(file) {
      const isImage = file.type.startsWith('image/')
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isImage) {
        this.$message.error('只能上传图片文件!')
      }
      if (!isLt2M) {
        this.$message.error('图片大小不能超过 2MB!')
      }
      return isImage && isLt2M
    }
  },
  created() {
    this.fetchData()
  }
}
</script>

<style>
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
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style> 