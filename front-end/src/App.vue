<template>
  <div id="app">
    <v-app>
      <v-content>
        <v-container>
          <Header></Header>
          <router-view></router-view>
        </v-container>

        <Footer></Footer>
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


export default {
  name: 'App',

  components: {
    Footer,
    Header,
  },

  created() {
    this.getGraphdata()
  },

  data() {
    return {
      menuCount: 2,
      iconClass: ['fa-sign-in-alt', 'fa-user-plus'],
      menuDirect: ['/', '/'],
    }
  },

  mounted() {
    if (sessionStorage.getItem("isLoggedIn") != null) {
      this.$store.state.isLoggedIn = true;
      this.$store.state.memberId = sessionStorage.getItem("isLoggedIn");
      if (sessionStorage.getItem("isAdmin") != null)
        this.$store.state.isAdmin = true;
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

#app {
  font-family: 'Lato', sans-serif;
  background: #1a1c33;
  padding-bottom: 30px;
}

.red-color {
  color: #E53935!important;
}

.menu-title {
  font-size: 8.0rem;
  color: #FAFAFA;
  text-decoration: none !important;
}

.main-title {
  font-size: 5.0rem;
  color: #FAFAFA;
}

.small-menu-title {
  font-size: 2.0rem;
  color: #FAFAFA;
  text-decoration: none !important;
}

.main-text {
  font-size: 2.0em;
  line-height: 2.0em;
  color: #FAFAFA;
}

.highlight-text {
  line-height: 2.0em;
  color: #E53935;
}

</style>
