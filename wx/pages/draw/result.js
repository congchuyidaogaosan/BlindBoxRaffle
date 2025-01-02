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
      if (res.statusCode === 200) {
        this.setData({
          result: res.data
        })
      }
    } catch (error) {
      console.error('获取抽取结果失败', error)
    } finally {
      this.setData({ loading: false })
    }
  },
  
  handleBack() {
    wx.navigateBack()
  }
}) 