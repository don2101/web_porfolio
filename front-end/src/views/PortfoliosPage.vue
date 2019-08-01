<template>
  <div>

    <h1 class="main-title my-2">Portfolios</h1>
    <v-btn
      depressed large flat outline
      :class="{'red-color': buttonPicked}"
      color="#FAFAFA"
      to="/portfolios/write"
      v-if="isAdmin">

      <div @mouseover="buttonPick" @mouseleave="buttonPick">
        Add Portfolio
      </div>
    </v-btn>


    <v-layout>
      <v-flex xs12>
        <PortfoliosList :limits="6" :load-more="true"></PortfoliosList>
      </v-flex>
    </v-layout>


  </div>
</template>

<script>
/**
 * 전체 portfolio list를 가져와 출력하는 페이지
 */

import PortfoliosList from './portfolios/PortfoliosList'
import AdminService from '../service/AdminService'

export default {
  name: 'PortfoliosPage',

	components: {
		PortfoliosList,
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },

  async created(){
    const grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
    if(grade === '0') {
      this.isAdmin=true;
    }else{
      this.isAdmin=false;
    }
  },

  data() {
    return {
      buttonPicked: false,
      isAdmin: false,

    }
  },
}


</script>
