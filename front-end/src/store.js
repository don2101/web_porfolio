import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    chartData: {},
    isLoggedIn: false,
    direction: 'slide-down',
  },

  mutations: {
    setChartData(state, dataList) {
      state.chartData = dataList
    },

    setPageDirection(state, direction) {
      state.direction = direction
    }
  }

})
