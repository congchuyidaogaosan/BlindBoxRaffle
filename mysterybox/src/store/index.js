import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import draw from './modules/draw'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    user,
    draw
  }
}) 