import request from '@/utils/request'

// 获取系列列表
export function getSeriesList(params) {
  return request({
    url: '/api/series/list',
    method: 'get',
    params
  })
}

// 创建系列
export function createSeries(data) {
  return request({
    url: '/api/series/create',
    method: 'post',
    data
  })
}

// 更新系列
export function updateSeries(id, data) {
  return request({
    url: `/api/series/update/${id}`,
    method: 'post',
    data
  })
}

// 删除系列
export function deleteSeries(id) {
  return request({
    url: `/api/series/delete/${id}`,
    method: 'post'
  })
}

// 获取系列详情
export function getSeriesById(id) {
  return request({
    url: `/api/series/detail/${id}`,
    method: 'get'
  })
} 