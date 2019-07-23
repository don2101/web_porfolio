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
export default {
  name: 'logout',

  data() {
    return {
      loginPicked: false,
    }
  },

  methods: {
    logout: function (event) {
      sessionStorage.removeItem("isLoggedIn");
      sessionStorage.removeItem("isAdmin");
      this.$store.state.isLoggedIn = false;
      this.$store.state.isAdmin = false;
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
