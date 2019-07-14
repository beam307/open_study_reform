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
      images:[],
    },
    startDate: null,
    maxMemberCnt: 0,
    introduce: '',
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
    age(state) {
      return state.meta.age;
    },
    time(state) {
      return state.meta.time;
    },
    images(state) {
      return state.meta.images;
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
    setAge(state, payload) {
      state.meta.age = payload;
    },
    setTime(state, payload) {
      state.meta.time = payload;
    },
    setStartDate(state, payload) {
      state.startDate = payload;
    },
    setMaxMemberCnt(state, payload) {
      state.maxMemberCnt = payload;
    },
    setIntroduce(state, payload) {
      state.introduce = payload;
    },
    setImages(state, payload) {
      state.meta.images = payload;
    },
  }

}
