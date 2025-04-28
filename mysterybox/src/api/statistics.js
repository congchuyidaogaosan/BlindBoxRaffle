import request from '@/utils/request'

// 获取概览数据
export function getOverview() {
  return request({
    url: '/api/ex/mother',
    method: 'get'
  })
}
