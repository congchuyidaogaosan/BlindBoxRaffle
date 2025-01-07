// pages/series/index.js
import { getSeriesList } from '../../utils/api'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    keyword: '',
    seriesList: [],
    loading: false,
    refreshing: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.fetchData()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },

  async onRefresh() {
    this.setData({ refreshing: true })
    try {
      await this.fetchData()
    } finally {
      this.setData({ refreshing: false })
    }
  },

  // 搜索处理
  onSearch(e) {
    const keyword = typeof e.detail === 'object' ? e.detail.value : this.data.keyword
    this.setData({ keyword })
    this.fetchData()
  },

  async fetchData() {
    this.setData({ loading: true })
    try {
      const res = await getSeriesList({
        name: this.data.keyword
      })
      this.setData({
        seriesList: res.data || []
      })
    } catch (error) {
      console.error('获取系列列表失败:', error)
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