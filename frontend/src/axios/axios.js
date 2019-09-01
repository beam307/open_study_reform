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
    if (store.getters['user/accessToken']) {
      config.headers = Object.assign(config.headers, {'X-AUTH-TOKEN': store.getters['user/accessToken']});
    }
    return config;
  }, err => {
    return Promise.reject(err);
  });

  axios.interceptors.response.use((res) => {
    return res;
  }, err => {
    if (err.response.data.errorCode == 11) {
      const headers = {
        'Content-Type': 'application/json',
        'X-REFRESH-TOKEN': store.getters['user/refreshToken']
      };
      axios.post("/api/auth/refreshToken", null, {headers: headers})
        .then(result => {
          store.dispatch('user/setAccessToken', result.data);
          window.localStorage.setItem("access-token", result.data);
          window.location.reload();
        }).catch(err => {
        store.dispatch('user/setAuthenticated', false);
        store.dispatch('user/setAccessToken', null);
        store.dispatch('user/setRefreshToken', null);
        window.localStorage.removeItem("access-token");
        window.localStorage.removeItem("refresh-token");
      })
    }
    return Promise.reject(err);
  });

  Vue.prototype.$authorizedApi = authrizedApi(axios, router, store);
  Vue.prototype.$http = axios;
}

