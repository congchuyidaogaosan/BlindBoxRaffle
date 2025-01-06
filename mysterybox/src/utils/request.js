import axios from 'axios'
import store from '@/store'
import router from '@/router'
import { Message } from 'element-ui'

// 创建axios实例
const service = axios.create({
  // baseURL: '',
  timeout: 5000
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    if (store.getters['user/getToken']) {
      config.headers['Authorization'] = 'Bearer ' + store.getters['user/getToken']
    }
    return config
  },
  error => {
    console.log(error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    
    // 如果响应成功
    if (res.code === 200) {
      return res
    }

    // 处理错误
    Message({
      message: res.message || 'Error',
      type: 'error',
      duration: 5 * 1000
    })

    // 处理401未授权
    if (res.code === 401) {
      store.dispatch('user/logout')
      router.push(`/login?redirect=${router.currentRoute.fullPath}`)
    }

    return Promise.reject(res)
  },
  error => {
    Message({
      message: error.response?.data?.message || '请求失败',
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service 