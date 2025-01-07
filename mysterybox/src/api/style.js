import request from '@/utils/request'

// 获取款式列表
export function getStyleList(params) {
  return request({
    url: '/api/styles/list',
    method: 'get',
    params
  })
}

// 创建款式
export function createStyle(data) {
  return request({
    url: '/api/styles/create',
    method: 'post',
    data
  })
}

// 更新款式
export function updateStyle(id, data) {
  return request({
    url: `/api/styles/update/${id}`,
    method: 'post',
    data
  })
}

// 删除款式
export function deleteStyle(id) {
  return request({
    url: `/api/styles/delete/${id}`,
    method: 'post'
  })
}

// 获取款式详情
export function getStyleById(id) {
  return request({
    url: `/api/styles/detail/${id}`,
    method: 'get'
  })
}

// 上传款式图片
export function uploadStyleImage(id, file) {
  const formData = new FormData()
  formData.append('file', file)
  return request({
    url: `/api/styles/image/${id}`,
    method: 'post',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
} 