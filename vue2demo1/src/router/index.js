import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path:'/shop',
    name:'shop',
    component: ()=>import('../views/ShopView')
  },
  {
    path:'/customer',
    name:'customer',
    component: ()=>import('../views/CustomerView'),
    meta: {
      requireAuth: true,
    }
  },
  {
    path:'/delivery',
    name:'delivery',
    component: ()=>import('../views/DeliveryView'),
    meta: {
      requireAuth: true,
    }
  },
  {
    path:'/manager',
    name:'manager',
    component: ()=>import('../views/Manager'),
    meta: {
      requireAuth: false,
    }
  },
  {
    path: '/storekeeper',
    name: 'storekeeper',
    component: () => import('../views/StoreKeeperView'),
    meta: {
      requireAuth: true,
    },
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
