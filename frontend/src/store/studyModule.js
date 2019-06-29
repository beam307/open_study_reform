const SET_NAME = 'SET_NAME';
const SET_MAJOR_REGION = 'SET_MAJOR_REGION';
const SET_MINOR_REGION = 'SET_MINOR_REGION';
const SET_CATEGORY = 'SET_CATEGORY';
const SET_META = 'SET_META';
const SET_START_DATE = 'SET_START_DATE';
const SET_MAX_MEMBER_CNT = 'SET_MAX_MEMBER_CNT';
const SET_INTRODUCE = 'SET_INTRODUCE';
const SET_IMAGES = 'SET_IMAGES';


export default {
  namespaced: true,

  state: {
    name: '',
    majorRegion: 0,
    minorRegion: 0,
    category: [],
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
    category(state) {
      return state.category;
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
    [SET_NAME](state, payload) {
      state.name = payload;
    },
    [SET_MAJOR_REGION](state, payload) {
      state.majorRegion = payload;
    },
    [SET_MINOR_REGION](state, payload) {
      state.minorRegion = payload;
    },
    [SET_CATEGORY](state, payload) {
      state.category = payload;
    },
    [SET_META](state, payload) {
      state.meta = payload;
    },
    [SET_START_DATE](state, payload) {
      state.startDate = payload;
    },
    [SET_MAX_MEMBER_CNT](state, payload) {
      state.maxMemberCnt = payload;
    },
    [SET_INTRODUCE](state, payload) {
      state.intoduce = payload;
    },
    [SET_IMAGES](state, payload) {
      state.images = payload;
    },
  },

  actions: {
    setName: (context, payload) => {
      context.commit(SET_NAME, payload);
    },
    setMajorRegion: (context, payload) => {
      context.commit(SET_MAJOR_REGION, payload);
    },
    setMinorRegion: (context, payload) => {
      context.commit(SET_MINOR_REGION, payload);
    },
    setCategory: (context, payload) => {
      context.commit(SET_CATEGORY, payload);
    },
    setMeta: (context, payload) => {
      context.commit(SET_META, payload);
    },
    setStartDate: (context, payload) => {
      context.commit(SET_START_DATE, payload);
    },
    setMaxMemberCnt: (context, payload) => {
      context.commit(SET_MAX_MEMBER_CNT, payload);
    },
    setIntroduce: (context, payload) => {
      context.commit(SET_INTRODUCE, payload);
    },
    setImages: (context, payload) => {
      context.commit(SET_IMAGES, payload);
    }
  }


}
