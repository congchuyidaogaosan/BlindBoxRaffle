import request from '@/utils/request'

export function getSeriesList() {
  return request({
    url: '/api/series',
    method: 'get'
  })
}

export function createSeries(data) {
  return request({
    url: '/api/series',
    method: 'post',
    data
  })
}

export function updateSeries(id, data) {
  return request({
    url: `/api/series/${id}`,
    method: 'put',
    data
  })
}

export function deleteSeries(id) {
  return request({
    url: `/api/series/${id}`,
    method: 'delete'
  })
} 