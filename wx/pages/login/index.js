import { login } from '../../utils/api'

Page({
  data: {
    loading: false
  },

  async handleLogin(e) {
    if (this.data.loading) return
    console.log(e);
    // 获取用户信息失败
    if (!e.detail.userInfo) {
      wx.showToast({
        title: '请授权登录',
        icon: 'none'
      })
      return
    }

    this.setData({ loading: true })

    try {
      // 获取code
      const { code } = await wx.login()
      console.log(code,e.detail.userInfo);
      // 调用后端登录接口
      const res = await login({
        code,
        userInfo: e.detail.userInfo
      })

      // 保存token和用户信息
      wx.setStorageSync('token', res.data.token)
      
      getApp().globalData.userInfo = res.data.userInfo
      getApp().globalData.isLogin = true
      console.log(getApp().globalData.userInfo);
      // 返回首页
      wx.switchTab({
        url: '/pages/draw/index'
      })

    } catch (err) {
      console.error('登录失败:', err)
      wx.showToast({
        title: '登录失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  }
}) 