import request from './request'

// 获取热门款式
export function getHotSeries(limit = 3) {
  return request({
    url: '/series/hot',
    method: 'GET',
    data: { limit }
  })
}

// 获取系列列表
export function getSeriesList(params) {
  return request({
    url: '/series/list',
    method: 'GET',
    data: params
  })
}

// 获取系列详情
export function getSeriesById(id) {
  return request({
    url: `/series/detail/${id}`,
    method: 'GET'
  })
}

// 获取系列下的款式列表
export function getStylesBySeriesId(seriesId) {
  return request({
    url: `/styles/series/${seriesId}`,
    method: 'GET'
  })
}

// 登录
export function login(data) {
    return request({
      url: '/WXLogin/setCode?code='+data.code,
      method: 'POST',
    })
  }
  
