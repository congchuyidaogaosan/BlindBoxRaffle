import { getStyleById, pay } from '../../utils/api'

Page({
  data: {
    styleId: null,
    styleInfo: null,
    loading: false
  },
  
  onLoad(options) {
    this.setData({
      styleId: options.styleId
    })
    this.fetchData()
  },
  
  async fetchData() {
    this.setData({ loading: true })
    try {
      const res = await getStyleById(this.data.styleId)
      if (res.statusCode === 200) {
        this.setData({
          styleInfo: res.data
        })
      }
    } catch (error) {
      console.error('获取款式信息失败', error)
    } finally {
      this.setData({ loading: false })
    }
  },
  
  async handlePay() {
    try {
      // 创建订单
      const orderRes = await wx.request({
        url: `${baseURL}/orders`,
        method: 'POST',
        data: {
          userId: wx.getStorageSync('userId'),
          styleId: this.data.styleId,
          quantity: 1,
          amount: this.data.styleInfo.price
        }
      })
      
      if (orderRes.statusCode === 200) {
        // 支付
        await pay(orderRes.data.id, this.data.styleInfo.price)
        wx.showToast({
          title: '支付成功',
          icon: 'success'
        })
        // 跳转到抽取结果页
        wx.navigateTo({
          url: `/pages/draw/result?orderId=${orderRes.data.id}`
        })
      }
    } catch (error) {
      wx.showToast({
        title: error.message || '支付失败',
        icon: 'none'
      })
    }
  }
}) 