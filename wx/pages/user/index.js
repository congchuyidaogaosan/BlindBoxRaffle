import { recharge, getWalletBalance, getUserStats } from '../../utils/api'

Page({
  data: {
    userInfo: {},
    loading: false,
    balance: 0,
    stats: {
      drawCount: 0,
      boxCount: 0
    }
  },

  onShow() {
    //获取本地用户信息
    const userInfo = getApp().globalData.userInfo
    this.setData({ userInfo })
    this.fetchWalletBalance()
    this.fetchUserStats()
  },
  
  
  // 获取钱包余额
  async fetchWalletBalance() {
    try {
      const app = getApp()
      const userId = app.globalData.userInfo.id // 从全局获取用户ID
      
      const res = await getWalletBalance(userId)
      if(res.code === 200) {
        this.setData({
          balance: res.data || 0
        })
      }
    } catch (error) {
      console.error('获取余额失败:', error)
      wx.showToast({
        title: '获取余额失败',
        icon: 'none'
      })
    }
  },
  
  // 处理充值
  async handleRecharge() {
    try {
      const { confirm, content } = await wx.showModal({
        title: '充值金额',
        editable: true,
        placeholderText: '请输入充值金额'
      })
      
      if (!confirm || !content) return
      
      const amount = parseFloat(content)
      if (isNaN(amount) || amount <= 0) {
        wx.showToast({
          title: '请输入有效金额',
          icon: 'none'
        })
        return
      }

      const app = getApp()
      const userId = app.globalData.userInfo.id // 从全局获取用户ID
      
      const res = await recharge(userId, amount)
      
      if (res.code === 200) {
        wx.showToast({
          title: '充值成功',
          icon: 'success'
        })
        // 刷新余额
        this.fetchWalletBalance()
      } else {
        throw new Error(res.msg || '充值失败')
      }
      
    } catch (error) {
      console.error('充值失败:', error)
      wx.showToast({
        title: error.message || '充值失败',
        icon: 'none'
      })
    }
  },
  
  // 头像和昵称相关方法
  userInput(e) {
    console.log(e);
    // 更改为 e.target.value 来获取输入框的值
    let nickname = e.target.value;
    this.setData({
      userInfo: {
       ...this.data.userInfo,
        nickname: nickname
      }
    });
    // 更改本地用户信息
    getApp().globalData.userInfo = this.data.userInfo;
    console.log(getApp().globalData.userInfo);
  },

  onChooseAvatar(e) {
    this.setData({
      // 更新头像 userInfo.avatarUrl
      userInfo: {
        ...this.data.userInfo,
        avatarUrl: e.detail.avatarUrl
      }
    });
    // 更改本地用户信息
    getApp().globalData.userInfo = this.data.userInfo
    console.log(getApp().globalData.userInfo);

  },
  
  // 页面跳转
  navigateTo(e) {
    const { url } = e.currentTarget.dataset
    wx.navigateTo({ url })
  },

  // 获取用户统计信息
  async fetchUserStats() {
    const app = getApp()
    const userId = app.globalData.userInfo.id
    const res = await getUserStats(userId)
    this.setData({
      stats: res.data || {}
    })

  },

  handleLogout() {
    wx.showModal({
      title: '提示',
      content: '确定要退出登录吗？',
      success: (res) => {
        if (res.confirm) {
          // 清除本地存储的用户信息
          wx.removeStorageSync('token')
          wx.removeStorageSync('userInfo')
          
          // 重置页面数据
          this.setData({
            userInfo: null,
            balance: '0.00',
            stats: {
              drawCount: 0,
              boxCount: 0
            }
          })
          
          // 跳转到登录页
          wx.navigateTo({
            url: '/pages/login/index'
          })
        }
      }
    })
  },
}) 