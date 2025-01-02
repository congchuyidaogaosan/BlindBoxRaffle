const baseURL = 'http://your-api-domain/api'

export const getBanners = () => {
  return wx.request({
    url: `${baseURL}/banners`,
    method: 'GET'
  })
}

export const getSeriesList = () => {
  return wx.request({
    url: `${baseURL}/series`,
    method: 'GET'
  })
}

export const getStylesBySeriesId = (seriesId) => {
  return wx.request({
    url: `${baseURL}/styles/series/${seriesId}`,
    method: 'GET'
  })
}

export const getStyleById = (id) => {
  return wx.request({
    url: `${baseURL}/styles/${id}`,
    method: 'GET'
  })
}

export const getUserBalance = () => {
  return wx.request({
    url: `${baseURL}/payment/balance/${wx.getStorageSync('userId')}`,
    method: 'GET'
  })
}

export const recharge = (amount) => {
  return wx.request({
    url: `${baseURL}/payment/recharge`,
    method: 'POST',
    data: {
      userId: wx.getStorageSync('userId'),
      amount
    }
  })
}

export const pay = (orderId, amount) => {
  return wx.request({
    url: `${baseURL}/payment/pay`,
    method: 'POST',
    data: {
      userId: wx.getStorageSync('userId'),
      orderId,
      amount
    }
  })
}

export const submitReview = (data) => {
  return wx.request({
    url: `${baseURL}/reviews`,
    method: 'POST',
    data: {
      userId: wx.getStorageSync('userId'),
      ...data
    }
  })
}

export const getStyleReviews = (styleId) => {
  return wx.request({
    url: `${baseURL}/reviews/style/${styleId}`,
    method: 'GET'
  })
}

export const getUserReviews = () => {
  return wx.request({
    url: `${baseURL}/reviews/user/${wx.getStorageSync('userId')}`,
    method: 'GET'
  })
} 