import { login, register, getUserInfo } from '@/api/user'

export default {
  namespaced: true,
  state: {
    token: localStorage.getItem('token') || '',
    userInfo: JSON.parse(localStorage.getItem('userInfo') || 'null')
  },
  getters: {
    isAuthenticated: state => !!state.token,
    getToken: state => state.token,
    getUserInfo: state => state.userInfo
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    SET_USER_INFO(state, userInfo) {
      state.userInfo = userInfo
      localStorage.setItem('userInfo', JSON.stringify(userInfo))
    },
    CLEAR_USER(state) {
      state.token = ''
      state.userInfo = null
      localStorage.removeItem('token')
      localStorage.removeItem('userInfo')
    }
  },
  actions: {
    async login({ commit }, loginData) {
      const res = await login(loginData)
      const { token, userInfo } = res.data
      commit('SET_TOKEN', token)
      commit('SET_USER_INFO', userInfo)
      return res
    },
    async register({ commit }, registerData) {
      const res = await register(registerData)
      return res
    },
    async getUserInfo({ commit }) {
      const res = await getUserInfo()
      commit('SET_USER_INFO', res.data)
      return res
    },
    logout({ commit }) {
      commit('CLEAR_USER')
    }
  }
} 