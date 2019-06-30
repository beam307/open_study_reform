export default {
  namespaced: true,

  state: {
    name: '',
    majorRegion: 0,
    minorRegion: 0,
    categories: [],
    meta: {
      age: [],
      time: [],
    },
    startDate: null,
    maxMemberCnt: 0,
    introduce: '',
    images: [],
  },

  getters: {
    name(state) {
      return state.name;
    },
    majorRegion(state) {
      return state.majorRegion;
    },
    minorRegion(state) {
      return state.minorRegion;
    },
    categories(state) {
      return state.categories;
    },
    meta(state) {
      return state.meta;
    },
    startDate(state) {
      return state.startDate;
    },
    maxMemberCnt(state) {
      return state.maxMemberCnt;
    },
    introduce(state) {
      return state.introduce;
    },
    images(state) {
      return state.images;
    },
  },

  mutations: {
    setName(state, payload) {
      state.name = payload;
    },
    setMajorRegion(state, payload) {
      state.majorRegion = payload;
    },
    setMinorRegion(state, payload) {
      state.minorRegion = payload;
    },
    setCategories(state, payload) {
      state.categories = payload;
    },
    setMeta(state, payload) {
      state.meta = payload;
    },
    setStartDate(state, payload) {
      state.startDate = payload;
    },
    setMaxMemberCnt(state, payload) {
      state.maxMemberCnt = payload;
    },
    setIntroduce(state, payload) {
      state.intoduce = payload;
    },
    setImages(state, payload) {
      state.images = payload;
    },
  }

}
