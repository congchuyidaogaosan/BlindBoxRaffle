import { getStylesBySeriesId } from '../../utils/api'

Page({
  data: {
    seriesId: null,
    styleList: [],
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
      const res = await getStylesBySeriesId(this.data.seriesId)
      if (res.statusCode === 200) {
        this.setData({
          styleList: res.data
        })
      }
    } catch (error) {
      console.error('获取款式列表失败', error)
      wx.showToast({
        title: '获取数据失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  },
  
  handleDraw(e) {
    const { id } = e.currentTarget.dataset
    wx.navigateTo({
      url: `/pages/draw/index?styleId=${id}`
    })
  }
}) 