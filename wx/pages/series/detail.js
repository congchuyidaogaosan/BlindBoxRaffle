import { getSeriesById, getStylesBySeriesId } from '../../utils/api'

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
      
      console.log('获取到系列信息:', seriesRes)
      console.log('获取到款式列表:', stylesRes)
      
      if (stylesRes.data && stylesRes.data.length > 0) {
        console.log('第一个款式的数据:', stylesRes.data[0])
      }
      
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