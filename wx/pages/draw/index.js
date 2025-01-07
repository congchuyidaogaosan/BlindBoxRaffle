// index.js
import { getHotSeries, getSeriesList } from '../../utils/api'

Page({
  data: {
    hotSeries: [], // 热门系列
    seriesList: [], // 系列列表
    loading: false,
    refreshing: false
  },

  onLoad() {
    this.fetchData()
  },

  async onRefresh() {
    this.setData({ refreshing: true })
    try {
      await this.fetchData()
    } finally {
      this.setData({ refreshing: false })
    }
  },

  async fetchData() {
    this.setData({ loading: true })
    try {
      // 并行请求热门系列和系列列表
      const [hotRes, seriesRes] = await Promise.all([
        getHotSeries(),
        getSeriesList()
      ])

      // 确保数据格式正确
      const hotSeries = hotRes.data || [];
      const seriesList = seriesRes.data || [];

      // 为图片添加默认图片
      hotSeries.forEach(item => {
        if (!item.imageUrl) {
          item.imageUrl = '/images/default.png'
        }
      })

      this.setData({
        hotSeries: hotSeries,
        seriesList: seriesList
      })

      // 打印数据用于调试
      console.log('热门系列:', hotSeries)
      console.log('系列列表:', seriesList)

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

  goToDetail(e) {
    const { id } = e.currentTarget.dataset
    wx.navigateTo({
      url: `/pages/series/detail?id=${id}`
    })
  }
})
