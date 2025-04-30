// pages/user/orders.js
import { getOrderList, cancelOrder, submitReview } from '../../utils/api'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderList: [],
    loading: false,
    currentTab: 0,
    tabs: ['全部', '已完成', '已取消'],
    showReviewModal: false,
    rating: 0,
    commentContent: '',
    currentOrderId: null,
    currentStyleId: null
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
      const status = 'ALL'
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

  // 处理评价
  handleReview(e) {
    const { id, styleId } = e.currentTarget.dataset
    this.setData({
      showReviewModal: true,
      currentOrderId: id,
      currentStyleId: styleId,
      rating: 0,
      commentContent: ''
    })
  },

  // 关闭评价弹窗
  closeReviewModal() {
    this.setData({
      showReviewModal: false,
      currentOrderId: null,
      currentStyleId: null,
      rating: 0,
      commentContent: ''
    })
  },

  // 处理评分
  handleRating(e) {
    const rating = e.currentTarget.dataset.rating
    this.setData({ rating })
  },

  // 处理评价内容输入
  handleCommentInput(e) {
    this.setData({
      commentContent: e.detail.value
    })
  },

  // 提交评价
  async submitReview() {
    const { currentOrderId, currentStyleId, rating, commentContent } = this.data
    const userInfo = getApp().globalData.userInfo
    
    if (rating === 0) {
      wx.showToast({
        title: '请选择评分',
        icon: 'none'
      })
      return
    }

    if (!commentContent.trim()) {
      wx.showToast({
        title: '请输入评价内容',
        icon: 'none'
      })
      return
    }

    try {
      // 这里需要调用评价接口
      await submitReview({
        userId: userInfo.id,
        boxStyleId: currentStyleId,
        content: commentContent,
        rating: rating
      })
      
      wx.showToast({
        title: '评价成功',
        icon: 'success'
      })
      
      this.closeReviewModal()
      // 刷新订单列表
      this.fetchOrders()
      
    } catch (error) {
      console.error('评价失败:', error)
      wx.showToast({
        title: '评价失败',
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