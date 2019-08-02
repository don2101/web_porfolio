<template>
  <div>
    <v-toolbar class="mb-5" color="#1a1c33" flat fixed >

      <v-btn icon dark @click="goTo" ><v-icon dark x-large>home</v-icon></v-btn>
      
      <div v-if="isLoggedIn">
        <v-layout class="ml-5">
        <Logout></Logout>
        <router-link v-if="isAdmin===true" :to="{ name: 'adminPage'}" :class="{'red-color': adminPagePicked}" class="small-menu-title mr-5">
          <div @mouseleave="adminPagePick" @mouseover="adminPagePick">
            AdminPage
          </div>
        </router-link>
        </v-layout>
      </div>

      <div v-else>
        <v-layout class="ml-5">
        <LoginModal></LoginModal>
        <SignupModal></SignupModal>
        </v-layout>
      </div>

      <TranslateAll></TranslateAll>
    </v-toolbar>
  </div>
</template>


<script>
import TranslateAll from './translate/TranslateAll'
import LoginModal from '../../components/accounts/LoginModal'
import SignupModal from '../../components/accounts/SignupModal'
import Logout from '../../components/accounts/Logout'
import AdminService from '../../service/AdminService'

export default {
  name: 'Header',

  data () {
    return {
      adminPagePicked: false,
      isAdmin: false,
    }
  },

  components: {
    TranslateAll,
    LoginModal,
    SignupModal,
    Logout,
  },

  methods: {
    goTo() {
      this.$store.commit('setPageDirection', 'slide-down')
      this.$router.push({ name: 'home' })
    },

    adminPagePick(){this.adminPagePicked =! this.adminPagePicked},

    async checkGrade() {
      const grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
      
      if(grade === '0') this.isAdmin = true;
      else this.isAdmin = false;
    }
  },

  created(){
    this.checkGrade();
  },
  
  computed: {
    isLoggedIn () {
      return this.$store.state.isLoggedIn;
    },

  },

  
}
</script>
