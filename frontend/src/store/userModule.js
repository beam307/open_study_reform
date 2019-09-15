export default {
  namespaced: true,

  state: {
    authenticated: window.localStorage.getItem('access-token') ? true : false,
    accessToken: window.localStorage.getItem('access-token') ? window.localStorage.getItem('access-token') : null,
    refreshToken: window.localStorage.getItem('refresh-token') ? window.localStorage.getItem('refresh-token') : null,
    id: null,
    name: null
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
    id: function (state) {
      return state.id;
    },
    name: function (state) {
      return state.name;
    },
    isProfile: function (state) {
      return state.id && state.name ? true : false
    }
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
    },
    setId: (state, payload) => {
      state.id = payload;
    },
    setName: (state, payload) => {
      state.name = payload;
    },
    setProfile: (state, payload) => {
      state.id = payload.id;
      state.name = payload.nickname;
    },
    resetProfile: (state, payload) => {
      state.id = null;
      state.name = null;
    },
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
    },
    setId: (context, payload) => {
      context.commit("setId", payload);
    },
    setName: (context, payload) => {
      context.commit("setName", payload);
    },
    setProfile: (context, payload) => {
      context.commit("setProfile", payload);
    },
    resetProfile: (context, payload) => {
      context.commit("resetProfile", payload);
    }
  }

}
