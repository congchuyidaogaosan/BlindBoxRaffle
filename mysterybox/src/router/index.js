import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/LoginView.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('@/views/LayoutView.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/DashboardView.vue'),
        meta: { title: '控制台' }
      },
      {
        path: 'series',
        name: 'Series',
        component: () => import('@/views/series/SeriesListView.vue'),
        meta: { title: '系列管理' }
      },
      {
        path: 'styles',
        name: 'Styles',
        component: () => import('@/views/styles/StyleListView.vue'),
        meta: { title: '款式管理' }
      },
      {
        path: 'orders',
        name: 'Orders',
        component: () => import('@/views/orders/OrderListView.vue'),
        meta: { title: '订单列表' }
      },
      {
        path: 'users',
        name: 'Users',
        component: () => import('@/views/users/UserListView.vue'),
        meta: { title: '用户管理' }
      },
      {
        path: 'statistics',
        name: 'Statistics',
        component: () => import('@/views/statistics/index.vue'),
        meta: { title: '数据统计' }
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  document.title = to.meta.title ? `${to.meta.title} - MysteryBox管理系统` : 'MysteryBox管理系统'
  
  // 判断是否需要登录
  if (to.path === '/login') {
    next()
  } else {
    if (store.getters['user/getToken']) {
      next()
    } else {
      next(`/login?redirect=${to.path}`)
    }
  }
})

export default router
