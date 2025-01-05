import request from '@/utils/request'

export function getOrders() {
  return request({
    url: '/api/orders',
    method: 'get'
  })
}

export function getOrderDetails(orderId) {
  return request({
    url: `/api/orders/${orderId}/details`,
    method: 'get'
  })
}

export function createOrder(data) {
  return request({
    url: '/api/orders',
    method: 'post',
    data
  })
}

export function updateOrder(id, data) {
  return request({
    url: `/api/orders/${id}`,
    method: 'put',
    data
  })
} 