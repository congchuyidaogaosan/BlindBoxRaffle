import request from './request'

// 获取热门款式
export function getHotStyles(limit = 3) {
  return request({
    url: '/styles/hot',
    method: 'GET',
    data: { limit }
  })
}

// 获取系列列表
export function getSeriesList() {
  return request({
    url: '/series/list',
    method: 'GET'
  })
} 