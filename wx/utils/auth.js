const TOKEN_KEY = 'token'
const USER_INFO = 'userInfo'

export function getToken() {
  return wx.getStorageSync(TOKEN_KEY)
}

export function setToken(token) {
  return wx.setStorageSync(TOKEN_KEY, token)
}

export function removeToken() {
  return wx.removeStorageSync(TOKEN_KEY)
}

export function getUserInfo() {
  return wx.getStorageSync(USER_INFO)
}

export function setUserInfo(userInfo) {
  return wx.setStorageSync(USER_INFO, userInfo)
}

export function removeUserInfo() {
  return wx.removeStorageSync(USER_INFO)
} 