import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

import studyModule from './studyModule';

export default new Vuex.Store({

  modules: {
    study: studyModule,
  }

});
