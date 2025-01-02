import { getUserInfo, recharge } from '../../utils/api'

Page({
  data: {
    userInfo: {},
    loading: false
  },
  
  onShow() {
    this.fetchUserInfo()
  },
  
  async fetchUserInfo() {
    this.setData({ loading: true })
    try {
      const res = await getUserInfo()
      if (res.statusCode === 200) {
        this.setData({
          userInfo: res.data
        })
      }
    } catch (error) {
      console.error('获取用户信息失败', error)
    } finally {
      this.setData({ loading: false })
    }
  },
  
  navigateTo(e) {
    const { url } = e.currentTarget.dataset
    wx.navigateTo({ url })
  },
  
  showRecharge() {
    wx.showModal({
      title: '充值金额',
      editable: true,
      placeholderText: '请输入充值金额',
      success: async (res) => {
        if (res.confirm) {
          const amount = parseFloat(res.content)
          if (isNaN(amount) || amount <= 0) {
            wx.showToast({
              title: '请输入有效金额',
              icon: 'none'
            })
            return
          }
          
          try {
            await recharge(amount)
            wx.showToast({
              title: '充值成功',
              icon: 'success'
            })
            this.fetchUserInfo()
          } catch (error) {
            wx.showToast({
              title: '充值失败',
              icon: 'none'
            })
          }
        }
      }
    })
  }
}) 