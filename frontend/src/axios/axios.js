import Vue from 'vue';
import Axios from 'axios';

let axios = Axios.create({
  baseURL: process.env.JAVA_API_URL,
  timeout: 3000,
});

const authrizedApi = (axios, router, store) => ({
  get(url) {
    return new Promise((resolve, reject) => {
      if (store.getters['user/accessToken']) {
        resolve(axios.get(url));
      } else {
        redirect(router);
      }
    });
  },
  post(url, data) {
    return new Promise((resolve, reject) => {
      if (store.getters['user/accessToken']) {
        resolve(axios.post(url, data));
      } else {
        redirect(router);
      }
    });
  }
});

const redirect = (router) => {
  setTimeout(() => {
    router.push({path: '/login'})
  }, 500);
};

export function createAxios(router, store) {

  axios.interceptors.request.use((config) => {
    config.headers = {'X-AUTH-TOKEN': store.getters['user/accessToken']};
    return config;
  }, err => {
    return Promise.reject(err);
  });

  Vue.prototype.$authorizedApi = authrizedApi(axios, router, store);
  Vue.prototype.$http = axios;
}

