import { draw } from '@/api/draw'

export default {
  namespaced: true,
  state: {
    drawHistory: []
  },
  mutations: {
    ADD_HISTORY(state, record) {
      state.drawHistory.unshift(record)
    }
  },
  actions: {
    async draw({ commit }, { seriesId, count }) {
      const res = await draw(seriesId, count)
      commit('ADD_HISTORY', res.data)
      return res.data
    }
  }
} 