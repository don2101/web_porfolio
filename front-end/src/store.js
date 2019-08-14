import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    chartData: {},
    isLoggedIn: false,
    mid: -1,
    direction: 'slide-down',
  },

  mutations: {
    setChartData(state, dataList) {
      state.chartData = dataList
    },

    setPageDirection(state, direction) {
      state.direction = direction
    },

    setMid(state, mid) {
      state.mid = mid
    },

    setLogin(state, login) {
      state.isLoggedIn = login
    },
  }

})
