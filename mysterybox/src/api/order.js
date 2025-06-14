import request from '@/utils/request'

export function getOrders() {
  return request({
    url: '/api/orders/list',
    method: 'get'
  })
}

export function getOrderDetails(orderId) {
  return request({
    url: `/api/orders/${orderId}/details`,
    method: 'get'
  })
}


export function updateOrder(id, data) {
  return request({
    url: `/api/orders/${id}`,
    method: 'put',
    data
  })
}

export function deleteOrder(orderId) {
  return request({
    url: `/api/orders/delete/${orderId}`,
    method: 'post'
  })
} 