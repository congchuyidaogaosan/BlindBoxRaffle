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
export function login(data) {
  return request({
    url: '/WXLogin/setCode?code='+data.code,
    method: 'POST',
  })
}

// 获取用户订单列表

// 获取订单列表
export function getOrderList(params) {
  return request({
    url: '/orders/getOrdersByUser',
    method: 'GET',
    data: params
  })
}

// 取消订单
export function cancelOrder(orderId) {
  return request({
    url: `/orders/update/${orderId}`,
    method: 'POST',
    data: {
      status: 'CANCELLED'
    }
  })
}

// 获取用户钱包余额
export function getWalletBalance(userId) {
  return request({
    url: '/user/wallet/balance',
    method: 'GET',
    data: { userId }
  })
}

// 充值
// Param 参数

export function recharge(userId, amount) {
  return request({
    url: '/user/wallet/recharge',
    method: 'POST',
    data: {
      userId: userId,
      amount: amount
    },
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 明确请求头的内容类型
    },

  })
}

// 更新用户信息
export function updateUserInfo(userInfo) {
  return request({
    url: '/api/user/update',
    method: 'POST',
    data: userInfo
  })
}

// 获取订单详情
export function getOrderById(orderId) {
  return request({
    url: `/orders/detail/${orderId}`,
    method: 'GET'
  })
}

// 添加抽奖相关的API方法

// 抽奖
export function drawMystery(data) {
  return request({
    url: '/draw/mystery', 
    method: 'POST',
    data,
    header: {
      'content-type': 'application/x-www-form-urlencoded' // 明确请求头的内容类型
    },
  })
}

// 获取用户统计数据
export function getUserStats(userId) {
  return request({
    url: '/user/stats',
    method: 'GET',
    data: { userId }
  })
}


  
