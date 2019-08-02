<template>
  <div>
    
    <h1 class="main-title my-5">Portfolios</h1>
    
    <!-- write portfolio button -->
    <v-btn
      depressed large flat outline
      :class="{'red-color': buttonPicked}"
      color="#FAFAFA"
      to="/portfolios/write"
      v-if="isAdmin">

      <div @mouseover="buttonPick" @mouseleave="buttonPick">
        Write Portfolio
      </div>
    </v-btn>

    <!-- portfolios lists -->
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
    
    async checkGrade() {
      const grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
      
      if(grade === '0') this.isAdmin = true;
      else this.isAdmin = false;  
    }
  },

  created(){
    this.checkGrade();  
  },

  data() {
    return {
      buttonPicked: false,
      isAdmin: false,
    }
  },
}


</script>
