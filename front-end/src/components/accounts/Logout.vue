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
      alert('로그아웃 되었습니다.')
      TokenService.deleteToken(sessionStorage.getItem("mid"));
      sessionStorage.removeItem("jwt");
      this.$store.commit("setMid", -1);
      this.$store.commit("setLogin", false);
      window.location.href = "/";
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
