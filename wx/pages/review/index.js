import { getStyleById, submitReview } from '../../utils/api'

Page({
  data: {
    styleId: null,
    styleInfo: null,
    rating: 5,
    content: '',
    loading: false
  },
  
  onLoad(options) {
    this.setData({
      styleId: options.styleId
    })
    this.fetchData()
  },
  
  async fetchData() {
    this.setData({ loading: true })
    try {
      const res = await getStyleById(this.data.styleId)
      if (res.statusCode === 200) {
        this.setData({
          styleInfo: res.data
        })
      }
    } catch (error) {
      console.error('获取款式信息失败', error)
    } finally {
      this.setData({ loading: false })
    }
  },
  
  setRating(e) {
    this.setData({
      rating: e.currentTarget.dataset.rating
    })
  },
  
  setContent(e) {
    this.setData({
      content: e.detail.value
    })
  },
  
  async handleSubmit() {
    if (!this.data.content.trim()) {
      wx.showToast({
        title: '请输入评价内容',
        icon: 'none'
      })
      return
    }
    
    try {
      await submitReview({
        styleId: this.data.styleId,
        rating: this.data.rating,
        content: this.data.content
      })
      
      wx.showToast({
        title: '评价成功',
        icon: 'success'
      })
      
      setTimeout(() => {
        wx.navigateBack()
      }, 1500)
    } catch (error) {
      wx.showToast({
        title: '评价失败',
        icon: 'none'
      })
    }
  }
}) 