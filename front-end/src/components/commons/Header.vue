<template>
  <div>
    <v-toolbar class="mt-2 ml-2" color="transparent" flat fixed>

      <v-btn icon dark to="/"><v-icon dark x-large>home</v-icon></v-btn>
      
      <div v-if="isLoggedIn">
        <v-layout class="ml-3">
        <Logout></Logout>
        </v-layout>
      </div>

      <div v-else>
        <v-layout class="ml-2">
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

export default {
  name: 'Header',

  data () {
    return {
      //
    }
  },

  components: {
    TranslateAll,
    LoginModal,
    SignupModal,
    Logout,
  },

  computed: {
    isLoggedIn () {
      return this.$store.state.isLoggedIn;
    }
  }
}
</script>
