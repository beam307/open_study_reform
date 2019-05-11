import Vue from 'vue'
import Router from 'vue-router'
import login from '../pages/Login'
import join from '../pages/Join'
import Index from '../pages/Index'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', component: Index},
    {path: '/login', component: login},
    {path: '/join', component: join},
    {path: '*', redirect: '/'}
  ]
})
