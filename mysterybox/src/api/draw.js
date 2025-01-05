import request from '@/utils/request'

export function draw(seriesId, count = 1) {
  return request({
    url: `/api/draw/${seriesId}`,
    method: 'post',
    data: { count }
  })
}

export function getDrawHistory(userId) {
  return request({
    url: `/api/draw/history/${userId}`,
    method: 'get'
  })
} 