import Vue from 'vue'
import Router from 'vue-router'
import login from '../components/Login'
import join from '../components/Join'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/login', component: login},
    {path: '/join', component: join}
  ]
})
