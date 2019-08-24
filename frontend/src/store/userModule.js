export default {
  namespaced: true,

  state: {
    authenticated: false,
    accessToken: window.localStorage.getItem('access-token') ? window.localStorage.getItem('access-token') : null,
    refreshToken: null,
  },

  getters: {
    authenticated: function (state) {
      return state.authenticated;
    },
    accessToken: function (state) {
      return state.accessToken;
    },
    refreshToken: function (state) {
      return state.refreshToken;
    },
  },

  mutations: {
    setAuthenticated: (state, payload) => {
      state.authenticated = payload;
    },
    setAccessToken: (state, payload) => {
      state.accessToken = payload;
    },
    setRefreshToken: (state, payload) => {
      state.refreshToken = payload;
    }
  },

  actions: {
    setAuthenticated: (context, payload) => {
      context.commit("setAuthenticated", payload);
    },
    setAccessToken: (context, payload) => {
      context.commit("setAccessToken", payload);
    },
    setRefreshToken: (context, payload) => {
      context.commit("setRefreshToken", payload);
    }
  }

}
