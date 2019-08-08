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
// import axios from 'axios'

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

  mounted() {
    if (sessionStorage.getItem("isLoggedIn") != null) {
      this.$store.state.isLoggedIn = true;
      this.$store.state.memberId = sessionStorage.getItem("mid");
    }

    this.getGraphdata();

    //push notification
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
    }

    var firebaseConfig = {
      apiKey: "AAAAzVOuSyc:APA91bHNGjZaC4bNmLvz5ZNK6QRK5S8zR0ZNp6FtQlGcm3k0HEibM_xq3xdjlJ8T8b-gBkFO20Yct9D0RxIXezP_b5hdFPGyFumpV-fbDDpv_WdCFQB5a6pbDfMkvgQaDUMR5c5OmeQ4",
      authDomain: "test-2a2a0.firebaseapp.com",
      databaseURL: "https://test-2a2a0.firebaseio.com",
      projectId: "test-2a2a0",
      storageBucket: "test-2a2a0.appspot.com",
      messagingSenderId: "881872227111",
    };
    firebase.initializeApp(firebaseConfig);
  //
    this.messaging = firebase.messaging();

    this.messaging.usePublicVapidKey('BGMLkfJPmn8BAvRGwXlkEDq_XmqvIKLPxWWOK-sjSVMpi8nTh0KwFrDRvvdYZhiS3LtsD2GO-1mpEG3DRGrmXns');

    if(sessionStorage.getItem("mid")!=null){
      this.requestPermission();
    }
  },

  methods: {
    async getGraphdata() {
      const graphData = await GitlabApiService.parseGraphData()
      this.$store.commit('setChartData', graphData)
    },
    pushMessage(){

      // this.messaging.onMessage(())
      let config={
        'headers':{
          'Authorization': "key=AAAAzVOuSyc:APA91bHNGjZaC4bNmLvz5ZNK6QRK5S8zR0ZNp6FtQlGcm3k0HEibM_xq3xdjlJ8T8b-gBkFO20Yct9D0RxIXezP_b5hdFPGyFumpV-fbDDpv_WdCFQB5a6pbDfMkvgQaDUMR5c5OmeQ4",
          'Content-Type':"application/json"
        }
      }

      let content={
        'registration_ids' : [
            "cpZ8SyasN2M:APA91bEQn784n31NHzfVocgxM-JfcEZj2oORswva8A5RtplsF4FLMLQhDNn24WMDPpV595RpwDBoUWKQTAnIOaHb2fD5Y8SQqw0cSNdfRYWQ8rESaP5dZ868q7A0bV70FvrvneitcUQk",
            "chfnOTEmKRY:APA91bGeM8jGuMoe38gD4E6fJPA1T4hMtcmMH7KJBlc8pfJuvgBVtAVYb66laWzFAUza1wyBh6s9NNSzyFoYp68cbaNJrnVMu3Noe_viAVxFfwyEi4RXGUUxgfa-UruG4CqyaXWyyWxe",
            "eW8N7Cp7rDU:APA91bE8dUKsF8KqID7qtu375PTcWxM3yLQaOaUXTQokF531UDDWNgtkRhZEFbGmDGOrntZwW6lkXIRtwNd1sPHnt6O9X4sdKi_BMGqX5aohKzKaCySvLrNnr-k8UkMR8pw1adBWi48y"
        ],
        'priority' : "high",
        'notification' : {
          'body' : "Background Message",
          'title' : "BG Title"
        },
        'data' : {
          'title' : "FG Title",
          'message' : "Foreground Message"
        }
      }
      axios.post("https://fcm.googleapis.com/fcm/send",content,config)

    },
    async getToken() {

      this.messaging.getToken().then((currentToken) => {
        console.log("현재토큰")
        console.log(currentToken)
        const tokenForm={
          'value':currentToken,
          'mid':sessionStorage.getItem("mid")
        }
        TokenService.insertToken(tokenForm)
      }).catch((err) => {
        console.log('An error occurred while retrieving token. ', err);

      });
    },



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
