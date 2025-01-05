import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import App from './App.vue'
import router from './router'
import store from './store'
import './permission'
import VCharts from 'vue-echarts'

// 手动引入 ECharts 模块来减小打包体积
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/pie'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/title'
import 'echarts/lib/component/legend'

Vue.use(ElementUI, { locale })
Vue.config.productionTip = false

// 注册组件
Vue.component('v-chart', VCharts)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
