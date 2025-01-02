// index.js
import { getBanners, getSeriesList } from '../../utils/api'

Page({
  data: {
    banners: [],
    seriesList: [],
    loading: false
  },
  
  async getBanners() {
    try {
      const res = await getBanners()
      if (res.statusCode === 200) {
        this.setData({
          banners: res.data
        })
      }
    } catch (error) {
      console.error('获取轮播图失败', error)
    }
  },
  
  async getSeriesList() {
    this.setData({ loading: true })
    try {
      const res = await getSeriesList()
      if (res.statusCode === 200) {
        this.setData({
          seriesList: res.data
        })
      }
    } catch (error) {
      console.error('获取系列列表失败', error)
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
