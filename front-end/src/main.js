import Vue from 'vue'
import App from './App.vue'

import router from './router'
import Vuetify from 'vuetify'
import store from './store'

import 'vuetify/dist/vuetify.min.css' // Ensure you are using css-loader
import VueSimplemde from 'vue-simplemde'
import 'simplemde/dist/simplemde.min.css'

Vue.config.productionTip = false
Vue.use(Vuetify, {
  iconfont: 'fa',
})


Vue.use(VueSimplemde)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')


window.addEventListener("keyup", keyboardGo)


function keyboardGo(item) {
  if(item.key === "ArrowRight") {
    store.commit('setPageDirection', 'slide-right')
    router.push({ name: 'aboutus' })
  }
  else if(item.key === "ArrowUp") {
    store.commit('setPageDirection', 'slide-up')
    router.push({ name: 'portfolios' })
  }
  else if(item.key === "ArrowLeft") {
    store.commit('setPageDirection', 'slide-left')
    router.push({ name: 'posts' })
  }
  else if(item.key === "ArrowDown") {
    store.commit('setPageDirection', 'slide-down')
    router.push({ name: 'home' })
  }
}
