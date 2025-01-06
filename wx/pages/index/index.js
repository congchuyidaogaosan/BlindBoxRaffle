// index.js
import { getHotStyles, getSeriesList } from '../../utils/api'

Page({
  data: {
    hotStyles: [], // 热门款式
    seriesList: [], // 系列列表
    loading: false
  },

  onLoad() {
    this.fetchData()
  },

  async fetchData() {
    this.setData({ loading: true })
    try {
      // 并行请求热门款式和系列列表
      const [hotRes, seriesRes] = await Promise.all([
        getHotStyles(),
        getSeriesList()
      ])

      this.setData({
        hotStyles: hotRes.data.data,
        seriesList: seriesRes.data.data
      })
    } catch (error) {
      wx.showToast({
        title: '获取数据失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  },

  goToDetail(e) {
    const { id } = e.currentTarget.dataset
    wx.navigateTo({
      url: `/pages/series/detail?id=${id}`
    })
  }
})
