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
