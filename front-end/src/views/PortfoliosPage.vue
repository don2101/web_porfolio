<template>
  <div>

    <h1 class="main-title my-2">Portfolios</h1>
    <v-btn
      depressed large flat outline
      :class="{'red-color': buttonPicked}"
      color="#FAFAFA"
      to="/portfolios/write"
      v-if="isAuthorized">

      <div @mouseover="buttonPick" @mouseleave="buttonPick">
        Add Portfolio
      </div>
    </v-btn>


    <v-layout>
      <v-flex xs12>
        <PortfoliosList :limits="6" :load-more="true"></PortfoliosList>
      </v-flex>
    </v-layout>
    <PortfolioCommentWrite/>
    <PortfolioCommentsList/>

  </div>
</template>

<script>
/**
 * 전체 portfolio list를 가져와 출력하는 페이지
 */

import PortfoliosList from './portfolios/PortfoliosList'
import PortfolioCommentsList from './comments/PortfolioCommentsList'
import PortfolioCommentWrite from './comments/PortfolioCommentWrite'

export default {
  name: 'PortfoliosPage',

	components: {
		PortfoliosList,
    PortfolioCommentsList,
    PortfolioCommentWrite,
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },

  computed: {
    // 관리자인지 확인하는 isAdmin
    isAuthorized() {
      const isAdmin = this.$store.state.isAdmin;
      const isLoggedIn = this.$store.state.isLoggedIn;
      if (isAdmin && isLoggedIn)
        return true;
    },
  },

  data() {
    return {
      buttonPicked: false
    }
  },
}


</script>
