// pages/user/orders.js
import { getOrderList, cancelOrder } from '../../utils/api'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList: [],
    loading: false,
    currentTab: 0, // 0:全部 1:待付款 2:已完成 3:已取消
    tabs: ['全部', '待付款', '已完成', '已取消']
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.fetchOrders()
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

  // 切换标签
  onTabChange(e) {
    const currentTab = e.currentTarget.dataset.index
    this.setData({ currentTab })
    this.fetchOrders()
  },

  // 获取订单列表
  async fetchOrders() {
    this.setData({ loading: true })
    try {
      // 根据当前标签获取对应状态的订单
      const status = ['ALL', 'PENDING', 'COMPLETED', 'CANCELLED'][this.data.currentTab]
      //获取用户信息 getApp().globalData.userInfo
      const userInfo = getApp().globalData.userInfo

      const res = await getOrderList({ status, userId: userInfo.id })
      
      this.setData({
        orderList: res.data || []
      })
    } catch (error) {
      console.error('获取订单列表失败:', error)
      wx.showToast({
        title: '获取订单失败',
        icon: 'none'
      })
    } finally {
      this.setData({ loading: false })
    }
  },

  // 取消订单
  async handleCancel(e) {
    const { id } = e.currentTarget.dataset
    
    try {
      await wx.showModal({
        title: '提示',
        content: '确定要取消该订单吗？'
      })
      
      await cancelOrder(id)
      
      wx.showToast({
        title: '取消成功',
        icon: 'success'
      })
      
      // 刷新订单列表
      this.fetchOrders()
      
    } catch (error) {
      console.error('取消订单失败:', error)
      wx.showToast({
        title: '取消失败',
        icon: 'none'
      })
    }
  },

  // 获取订单状态文本
  getStatusText(status) {
    const statusMap = {
      'PENDING': '待付款',
      'COMPLETED': '已完成', 
      'CANCELLED': '已取消'
    }
    return statusMap[status] || status
  },

  // 获取订单状态样式
  getStatusClass(status) {
    const classMap = {
      'PENDING': 'warning',
      'COMPLETED': 'success',
      'CANCELLED': 'default'
    }
    return classMap[status] || 'default'
  }
})