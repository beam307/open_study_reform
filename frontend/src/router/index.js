import Vue from 'vue'
import Router from 'vue-router'
import User from '../pages/user/User'
import Study from '../pages/study/Study'
import StudyList from '../pages/study/StudyList'
import StudyRegister from '../pages/study/StudyRegister'
import login from '../pages/login/Login'
import UserSignUp from '../pages/user/UserSignUp'
import Index from '../pages/Index'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {path: '/', component: Index},
    {path: '/login', component: login},
    {
      path: '/user', component: User,
      children: [{
        path: 'signUp', component: UserSignUp
      }]
    },
    {
      path: '/study', component: Study,
      children: [{
        path: 'list', component: StudyList,
      }, {
        path: 'register', component: StudyRegister,
      }]
    },
    {path: '*', redirect: '/'}
  ]
})
