import router from './router'
import store from './store'
import { Message } from 'element-ui'

// 白名单路由
const whiteList = ['/login', '/register']

router.beforeEach(async (to, from, next) => {
  const hasToken = store.getters['user/getToken']

  if (hasToken) {
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
      const hasUserInfo = store.getters['user/getUserInfo']
      if (hasUserInfo) {
        next()
      } else {
        try {
          // 获取用户信息
          await store.dispatch('user/getUserInfo')
          next()
        } catch (error) {
          // 清除token并跳转登录页
          store.dispatch('user/logout')
          Message.error(error.message || '获取用户信息失败')
          next(`/login?redirect=${to.path}`)
        }
      }
    }
  } else {
    if (whiteList.includes(to.path)) {
      next()
    } else {
      next(`/login?redirect=${to.path}`)
    }
  }
})

router.afterEach(() => {
  // 结束进度条等
}) 