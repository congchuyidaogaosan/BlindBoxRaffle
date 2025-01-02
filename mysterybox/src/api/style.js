import request from '@/utils/request'

export function getStyleList(params) {
  return request({
    url: '/api/styles',
    method: 'get',
    params
  })
}

export function getStyleById(id) {
  return request({
    url: `/api/styles/${id}`,
    method: 'get'
  })
}

export function createStyle(data) {
  return request({
    url: '/api/styles',
    method: 'post',
    data
  })
}

export function updateStyle(id, data) {
  return request({
    url: `/api/styles/${id}`,
    method: 'put',
    data
  })
}

export function deleteStyle(id) {
  return request({
    url: `/api/styles/${id}`,
    method: 'delete'
  })
} 