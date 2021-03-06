import 'babel-polyfill'
import Vue from 'vue'
import Router from 'vue-router'
import store from '../store/store'
import User from '../pages/user/User'
import Study from '../pages/study/Study'
import StudyList from '../pages/study/StudyList'
import StudyEdit from '../pages/study/edit/StudyEdit'
import StudyView from '../pages/study/StudyView'
import login from '../pages/login/Login'
import UserSignUp from '../pages/user/UserSignUp'
import Index from '../pages/Index'
import UserMyPage from "../pages/user/UserMyPage";
import UserManage from "../pages/user/UserManage";
import StudyManage from "../pages/user/StudyManage";
import Notice from "../pages/notice/Notice";
import NoticeList from "../pages/notice/NoticeList";
import NoticeView from "../pages/notice/NoticeView";


Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/', component: Index
    },
    {
      path: '/login',
      name: 'login',
      component: login,
      beforeEnter: (to, from, next) => {
        if (store.getters['user/authenticated']) {
          next('/');
        } else {
          next();
        }
      }
    },
    {
      path: '/user', component: User,
      children: [
        {
          path: 'signUp', component: UserSignUp
        },
        {
          path: 'myPage', component: UserMyPage
        },
        {
          path: 'userManage', component: UserManage
        },
        {
          path: 'studyManage', component: StudyManage
        }
      ]
    },
    {
      path: '/study', component: Study,
      children: [
        {
          path: 'list', name: 'studyList', component: StudyList,
        },
        {
          path: 'edit/:id', name: 'studyEdit', component: StudyEdit,
          beforeEnter: (to, from, next) => {
            if (store.getters['user/authenticated']) {
              next();
            } else {
              alert("로그인이 필요합니다.");
              next('/login');
            }
          }
        },
        {
          path: ':id', name: 'studyView', component: StudyView,
        }
      ]
    },
    {
      path: '/notice', component: Notice,
      children: [
        {
          path: 'list', name: 'noticeList', component: NoticeList
        },
        {
          path: ':id', name: 'noticeView', component: NoticeView
        }
      ]
    },
    {path: '*', redirect: '/'}
  ]
})
