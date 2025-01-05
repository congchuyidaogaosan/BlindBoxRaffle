import request from '@/utils/request'

export function getStylesBySeriesId(seriesId) {
  return request({
    url: `/api/styles/series/${seriesId}`,
    method: 'get'
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