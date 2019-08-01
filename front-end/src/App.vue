<template>
  <div id="app">
    <v-app>
      <v-content>
        <v-container>
          <Header></Header>
          <transition :name="this.pageDirection" mode="out-in">
            <router-view></router-view>
          </transition>
        </v-container>
        <ChatBot></ChatBot>
      </v-content>
    </v-app>
  </div>
</template>

<script>
/*jslint devel: true */
/* eslint-disable */
import Footer from './components/commons/Footer'
import Header from './components/commons/Header'
import GitlabApiService from './service/GitlabApiService'
import ChatBot from './components/commons/ChatBot'

export default {
  name: 'App',

  components: {
    Footer,
    Header,
    ChatBot,
  },

  created() {


  },

  data() {
    return {
      menuCount: 2,
      iconClass: ['fa-sign-in-alt', 'fa-user-plus'],
      menuDirect: ['/', '/'],
    }
  },

  computed: {
    pageDirection() {
      return this.$store.state.direction
    }
  },

  mounted() {
<<<<<<< HEAD
    var mid = sessionStorage.getItem("isLoggedIn");
    if (mid != null) {
      this.$store.state.isLoggedIn = true;
      this.$store.state.mid = mid;
      if (sessionStorage.getItem("isAdmin") != null)
        this.$store.state.isAdmin = true;
=======

    if (sessionStorage.getItem("isLoggedIn") != null) {
      this.$store.state.isLoggedIn = true;
      this.$store.state.memberId = sessionStorage.getItem("mid");
>>>>>>> bef11c6b7392391631d23aec0e12faed88835c80
    }
  },

  methods: {
    async getGraphdata() {
      const graphData = await GitlabApiService.parseGraphData()
      this.$store.commit('setChartData', graphData)
    },

  }

}
</script>


<style>
@import url('https://fonts.googleapis.com/css?family=Lato:400,700&display=swap');
@import './assets/style.css';


</style>
