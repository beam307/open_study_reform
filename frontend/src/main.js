import 'babel-polyfill'
import 'filepond-polyfill'
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.css'
import { createAxios } from './axios/axios';
import store from './store/store'

Vue.use(Vuetify);
createAxios(router, store);
Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
}).$mount('#app');
