import request from '@/utils/request'

// 获取概览数据
export function getOverview() {
  return request({
    url: '/api/statistics/overview',
    method: 'get'
  })
}

// 获取抽奖偏好数据
export function getDrawPreferences() {
  return request({
    url: '/api/statistics/preferences',
    method: 'get'
  })
}

// 获取热门系列数据
export function getPopularStyles() {
  return request({
    url: '/api/statistics/popular',
    method: 'get'
  })
} 