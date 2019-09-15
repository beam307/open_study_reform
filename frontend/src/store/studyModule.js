const study = () => {
  return {
    name: '',
    majorRegionId: 0,
    minorRegionId: 0,
    categoryIds: [],
    meta: {
      age: [],
      time: [],
      images: [],
    },
    startDate: null,
    maxMemberCnt: 0,
    introduce: ''
  }
};

export default {
  namespaced: true,

  state: study(),

  getters: {
    name: function (state) {
      return state.name;
    },
    majorRegion: function (state) {
      return state.majorRegionId;
    },
    minorRegion: function (state) {
      return state.minorRegionId;
    },
    categories: function (state) {
      return state.categoryIds;
    },
    meta: function (state) {
      return state.meta;
    },
    age: function (state) {
      return state.meta.age;
    },
    time: function (state) {
      return state.meta.time;
    },
    images: function (state) {
      return state.meta.images;
    },
    startDate: function (state) {
      return state.startDate;
    },
    maxMemberCnt: function (state) {
      return state.maxMemberCnt;
    },
    introduce: function (state) {
      return state.introduce;
    },
  },

  mutations: {
    setName: (state, payload) => {
      state.name = payload;
    },
    setMajorRegion: (state, payload) => {
      state.majorRegionId = payload;
    },
    setMinorRegion: (state, payload) => {
      state.minorRegionId = payload;
    },
    setCategories: (state, payload) => {
      state.categoryIds = payload;
    },
    setMeta: (state, payload) => {
      state.meta = payload;
    },
    setAge: (state, payload) => {
      state.meta.age = payload;
    },
    setTime: (state, payload) => {
      state.meta.time = payload;
    },
    setStartDate: (state, payload) => {
      state.startDate = payload;
    },
    setMaxMemberCnt: (state, payload) => {
      state.maxMemberCnt = payload;
    },
    setIntroduce: (state, payload) => {
      state.introduce = payload;
    },
    setImages: (state, payload) => {
      state.meta.images = payload;
    },
    resetState: (state) => {
      Object.assign(state, study());
    }
  }

}
