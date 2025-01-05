import request from '@/utils/request'

export function getHotSeries() {
  return request({
    url: '/api/series/hot',
    method: 'get'
  })
}

export function getAllSeries() {
  return request({
    url: '/api/series',
    method: 'get'
  })
}

export function getSeriesById(id) {
  return request({
    url: `/api/series/${id}`,
    method: 'get'
  })
} 