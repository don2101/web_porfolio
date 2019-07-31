import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    chartData: {},
    isLoggedIn: false,
    memberId: 0,

  },

  mutations: {
    setChartData(state, dataList) {
      state.chartData = dataList
    },
  }

})
