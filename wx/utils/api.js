const baseUrl = 'http://localhost:8080/api'

// 获取热门款式
export function getHotStyles(limit = 3) {
  return wx.request({
    url: `${baseUrl}/styles/hot`,
    method: 'GET',
    data: { limit }
  })
}

// 获取系列列表
export function getSeriesList() {
  return wx.request({
    url: `${baseUrl}/series`,
    method: 'GET'
  })
} 