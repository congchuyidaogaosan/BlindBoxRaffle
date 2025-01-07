import { getSeriesById, getStylesBySeriesId } from '../../utils/api'

Page({
  data: {
    seriesId: null,
    seriesInfo: {},
    styleList: [],
    lowestProb: 0,
    loading: false
  },
  
  onLoad(options) {
    this.setData({
      seriesId: options.id
    })
    this.fetchData()
  },
  
  async fetchData() {
    this.setData({ loading: true })
    try {
      const [seriesRes, stylesRes] = await Promise.all([
        getSeriesById(this.data.seriesId),
        getStylesBySeriesId(this.data.seriesId)
      ])

      // 计算最低概率
      const styleList = stylesRes.data || []
      const lowestProb = Math.min(...styleList.map(item => item.probability))

      this.setData({
        seriesInfo: seriesRes.data,
        styleList,
        lowestProb
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
  
  // 抽取神秘款式
  onDrawMystery() {
    wx.navigateTo({
      url: `/pages/draw/index?seriesId=${this.data.seriesId}&type=mystery`
    })
  },
  
  // 抽取普通款式
  onDraw(e) {
    const { id } = e.currentTarget.dataset
    wx.navigateTo({
      url: `/pages/draw/index?seriesId=${this.data.seriesId}&styleId=${id}`
    })
  }
}) 