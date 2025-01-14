import { getOrderById } from '../../utils/api'

Page({
  data: {
    orderId: null,
    result: null,
    loading: false
  },
  
  onLoad(options) {
    this.setData({
      orderId: options.orderId
    })
    this.fetchData()
  },
  
  async fetchData() {
    this.setData({ loading: true })
    try {
      const res = await getOrderById(this.data.orderId)
      console.log('抽奖结果:', res)
      
      if (res.code === 200) {
        this.setData({
          result: {
            order: res.data
          }
        })
      } else {
        throw new Error(res.message || '获取数据失败')
      }
    } catch (error) {
      console.error('获取抽取结果失败', error)
      wx.showToast({
        title: error.message || '获取结果失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  },
  
  handleBack() {
    wx.navigateBack()
  },
  
  goToOrders() {
    wx.navigateTo({
      url: '/pages/user/orders'
    })
  }
}) 