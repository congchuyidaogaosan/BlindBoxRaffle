// app.js
App({
  onLaunch() {
    // 检查登录状态
    this.checkLogin()
  },

  checkLogin() {
    // 获取本地存储的token
    const token = wx.getStorageSync('token')
    if (!token) {
      // 如果没有token，跳转到登录页
      wx.redirectTo({
        url: '/pages/login/index'
      })
      return false
    }
    return true
  },

  globalData: {
    userInfo: null,
    isLogin: false
  }
})
