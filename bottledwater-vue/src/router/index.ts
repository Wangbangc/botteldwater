import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from 'vue-router'
import HomeView from '../views/HomeView.vue'

// @ts-ignore
import Login from '../views/login/index.vue'
import Home from '../views/home/index.vue'
import Admin from '../views/admin/index.vue'
import UserManage from '../views/userManage/index.vue'
import bottledManage from '../views/bottledManage/index.vue'
import deliveryManage from '../views/deliveryManage/index.vue'
import orderManage from '../views/orderManage/index.vue'  
import { ElMessage, ElMessageBox } from 'element-plus'
import DeliveryOrderManage from '../views/DeliveryOrderManage/index.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: '',
    component: Login
  },  
  {
    path: '/deliveryOrderManage',
    name: 'deliveryOrderManage',
    component: DeliveryOrderManage
  },
  {
    path: '/deliveryManage',
    name: 'deliveryManage',
    component: deliveryManage
  },
  {
    path: '/orderManage',
    name: 'orderManage',
    component: orderManage
  },
  {
    path:'/bottledManage',
    name:'bottledManage',
    component:bottledManage
  },
  {
    path:'/home',
    name:'home',
    component:Home
  },
  {
    path:'/userManage',
    name:'userManage',
    component:UserManage
  },
  {
    path:'/admin',
    name:'admin',
    component:Admin
  },
 
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
// BAD
//写一个路由导航守卫，如果用户未登录，就跳转到登录页面，确保localStorage.getItem('token')存在是登录状态
const isAuthenticated = localStorage.getItem('token')
router.beforeEach((to, from, next) => {
  if (to.name !== '' && !isAuthenticated){
    next({ name: ''})
    ElMessage.error('请先登录')
  } 
  // 如果用户未能验证身份，则 `next` 会被调用两次
  next()
})

export default router
