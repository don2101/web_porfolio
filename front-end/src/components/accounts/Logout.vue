<template>
  <div>
    <div
      v-on:click="logout"
      :class="{ 'red-color': loginPicked }"
      class="small-menu-title mr-5"
    >
      <div @mouseleave="loginUnPick" @mouseover="loginPick">
        Logout
      </div>
    </div>

  </div>
</template>


<script>
import TokenService from '../../service/TokenService'

export default {
  name: 'logout',

  data() {
    return {
      loginPicked: false,
    }
  },

  methods: {
    logout() {
      TokenService.deleteToken(sessionStorage.getItem("mid"));
      sessionStorage.removeItem("mid");
      sessionStorage.removeItem("jwt");
      this.$store.state.isLoggedIn = false;
      this.$store.state.mid = -1;
      // window.location.href = "/"
    },

    loginPick() {
      document.body.style.cursor = 'pointer';
      this.loginPicked = !this.loginPicked
    },

    loginUnPick() {
      document.body.style.cursor = 'initial';
      this.loginPicked = !this.loginPicked
    },
  },
}

</script>
