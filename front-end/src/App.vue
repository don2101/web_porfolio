<template>
  <div id="app">
    <v-app>
      <v-content>
        <Header></Header>

        <v-container>
          <transition :name="this.pageDirection" mode="out-in">
            <router-view class="my-5"></router-view>
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
import * as firebase from "firebase/app"
import "firebase/messaging"
import TokenService from './service/TokenService'


export default {
  name: 'App',

  components: {
    Footer,
    Header,
    ChatBot,
  },

  data() {
    return {
      menuCount: 2,
      iconClass: ['fa-sign-in-alt', 'fa-user-plus'],
      menuDirect: ['/', '/'],
      messaging: '',
    }
  },

  computed: {
    pageDirection() {
      return this.$store.state.direction
    }
  },

  async mounted() {
    // jwt를 사용해서 mounted 될 때 마다 login되어 있는지를 확인하고 mid값을 저장
    if (sessionStorage.getItem("jwt") != null) {
      let result = await TokenService.checkToken()
      
      this.$store.commit('setMid', result.data.mid);
      this.$store.commit('setLogin', true);
    } else {
      this.$store.commit('setMid', -1);
      this.$store.commit('setLogin', false);
    }

    this.getGraphdata();
    
    /* service worker regist */
    if ('serviceWorker' in navigator) {
      window.addEventListener('load', function() {
        navigator.serviceWorker.register('./firebase-messaging-sw.js').then(function(registration) {
          // Registration was successful
          console.log('ServiceWorker registration successful with scope: ', registration.scope);
        }, function(err) {
          // registration failed :(
          console.log('ServiceWorker registration failed: ', err);
        });
      });
    } else {
      console.log("can't find service worker")
    }
    /* service worker regist */

    /* firebase initilization */
    var firebaseConfig = {
      apiKey: "AAAAzVOuSyc:APA91bHNGjZaC4bNmLvz5ZNK6QRK5S8zR0ZNp6FtQlGcm3k0HEibM_xq3xdjlJ8T8b-gBkFO20Yct9D0RxIXezP_b5hdFPGyFumpV-fbDDpv_WdCFQB5a6pbDfMkvgQaDUMR5c5OmeQ4",
      authDomain: "test-2a2a0.firebaseapp.com",
      databaseURL: "https://test-2a2a0.firebaseio.com",
      projectId: "test-2a2a0",
      storageBucket: "test-2a2a0.appspot.com",
      messagingSenderId: "881872227111",
    };

    firebase.initializeApp(firebaseConfig);

    this.messaging = firebase.messaging();

    this.messaging.usePublicVapidKey('BGMLkfJPmn8BAvRGwXlkEDq_XmqvIKLPxWWOK-sjSVMpi8nTh0KwFrDRvvdYZhiS3LtsD2GO-1mpEG3DRGrmXns');

    if(sessionStorage.getItem("mid") != null){
      this.requestPermission();
    }
    /* firebase initilization */
    
  },

  methods: {
    async getGraphdata() {
      const graphData = await GitlabApiService.parseGraphData()
      this.$store.commit('setChartData', graphData)
    },

    // token insert
    getToken() {
      this.messaging.getToken().then((currentToken) => {
        const tokenForm = {
          'value':currentToken,
          'mid':sessionStorage.getItem("mid")
        }
        TokenService.insertToken(tokenForm)
      }).catch((err) => {
        console.log('An error occurred while retrieving token. ', err);
      });
    },

    // token permission request
    requestPermission() {
      console.log('Requesting permission...');

      Notification.requestPermission().then((permission) => {
        if (permission === 'granted') {
          console.log('Notification permission granted.');
          this.getToken();
        } else {
          console.log('Unable to get permission to notify.');
        }
      });
    },

  }

}
</script>


<style>
@import url('https://fonts.googleapis.com/css?family=Lato:400,700&display=swap');
@import './assets/style.css';

</style>
