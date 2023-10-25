import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from 'vue-router'
import HomeView from '../views/HomeView.vue'

// @ts-ignore
import Login from '../views/login/index.vue'
import Home from '../views/home/index.vue'
import Admin from '../views/admin/index.vue'
import UserManage from '../views/userManage/index.vue'
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: '',
    component: HomeView
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
    path:'/adminLogin',
    name:'adminLogin',
    component:Login
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

export default router
