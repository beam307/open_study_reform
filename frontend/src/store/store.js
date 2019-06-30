import Vue from 'vue'
import Vuex from 'vuex'
import studyModule from './studyModule';

Vue.use(Vuex);

export default new Vuex.Store({

  modules: {
    study: studyModule,
  }

});
