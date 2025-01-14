import { getSeriesById, getStylesBySeriesId, drawMystery } from '../../utils/api'

Page({
  data: {
    seriesId: null,
    seriesInfo: {},
    styleList: [],
    loading: false
  },
  
  onLoad(options) {
    console.log('页面加载，参数:', options)
    this.setData({
      seriesId: options.id
    })
    this.fetchData()
  },
  
  async fetchData() {
    console.log('开始获取数据，seriesId:', this.data.seriesId)
    this.setData({ loading: true })
    try {
      const [seriesRes, stylesRes] = await Promise.all([
        getSeriesById(this.data.seriesId),
        getStylesBySeriesId(this.data.seriesId)
      ])
      
      this.setData({
        seriesInfo: seriesRes.data,
        styleList: stylesRes.data || []
      })
    } catch (error) {
      console.error('获取数据失败:', error)
      wx.showToast({
        title: '获取数据失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  },
  
  // 神秘抽奖
  async onDrawMystery() {
    try {
      // 检查余额是否足够
      const app = getApp()
      const userBalance = app.globalData.userInfo.balance || 0
      console.log(app.globalData.userInfo);
      if (userBalance < this.data.seriesInfo.price) {
        wx.showToast({
          title: '余额不足，请先充值',
          icon: 'none'
        })
        return
      }

      // 显示确认框
      const { confirm } = await wx.showModal({
        title: '确认抽奖',
        content: `确定要花费 ¥${this.data.seriesInfo.price} 进行抽奖吗？`,
        confirmText: '确定抽奖'
      })
      
      if (!confirm) return

      // 显示抽奖动画
      wx.showLoading({
        title: '抽奖中...',
        mask: true
      })

      // 调用神秘抽奖接口
      const res = await drawMystery({
        seriesId: this.data.seriesId,
        userId: app.globalData.userInfo.id
      })

      wx.hideLoading()

      // 更新用户余额
      app.globalData.userInfo.balance -= this.data.seriesInfo.price

      // 跳转到抽奖结果页
      wx.navigateTo({
        url: `/pages/draw/result?orderId=${res.data.order.id}`
      })

    } catch (error) {
      wx.hideLoading()
      wx.showToast({
        title: error.message || '抽奖失败',
        icon: 'none'
      })
    }
  }
}) 