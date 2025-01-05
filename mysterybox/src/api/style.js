import request from '@/utils/request'

// 获取款式列表
export function getStyleList() {
  return request({
    url: '/api/styles',
    method: 'get'
  })
}

// 创建款式
export function createStyle(data) {
  return request({
    url: '/api/styles',
    method: 'post',
    data
  })
}

// 更新款式
export function updateStyle(id, data) {
  return request({
    url: `/api/styles/${id}`,
    method: 'put',
    data
  })
}

// 删除款式
export function deleteStyle(id) {
  return request({
    url: `/api/styles/${id}`,
    method: 'delete'
  })
}

// 获取款式详情
export function getStyleById(id) {
  return request({
    url: `/api/styles/${id}`,
    method: 'get'
  })
}

// 上传款式图片
export function uploadStyleImage(id, file) {
  const formData = new FormData()
  formData.append('file', file)
  return request({
    url: `/api/styles/${id}/image`,
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
} 