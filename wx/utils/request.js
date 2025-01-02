const request = (options) => {
  const token = wx.getStorageSync('token')
  
  return new Promise((resolve, reject) => {
    wx.request({
      ...options,
      header: {
        ...options.header,
        Authorization: token ? `Bearer ${token}` : ''
      },
      success: (res) => {
        if (res.statusCode === 401) {
          // token过期，重新登录
          wx.navigateTo({
            url: '/pages/login/index'
          })
          reject(new Error('未登录或登录已过期'))
        } else {
          resolve(res)
        }
      },
      fail: reject
    })
  })
}

export default request 