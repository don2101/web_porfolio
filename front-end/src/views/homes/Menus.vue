<template>
  
  <v-container fill-height style="height: 90vh" d-flex>
    <CircularMenu></CircularMenu>
  </v-container>

</template>


<script>
/**
 * 각 페이지로 이동하는 Menu를 출력하는 component
 * Login과 Signup은 Modal을 사용하기 때문에 따로 component로 분리
 */

import CircularMenu from '../../components/commons/CircularMenu'


export default {
  name: "Menus",

  components: {
    CircularMenu,
  },

  methods: {
    postsPick() { this.postsPicked = !this.postsPicked },
    portfoliosPick() { this.portfoliosPicked = !this.portfoliosPicked },
    aboutUsPick() { this.aboutUsPicked = !this.aboutUsPicked },
    signupPick() { this.signupPicked = !this.signupPicked },
  },

  data() {
    return {
      postsPicked: false,
      portfoliosPicked: false,
      aboutUsPicked: false,
      signupPicked: false,
    }
  },

  

  
}
</script>
