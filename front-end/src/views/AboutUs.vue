<template>
  <div>
    <div style="height: 100vh">
      <h1 class="main-title">About Us</h1>
      
      <p class="main-text">
        We are <strong class="highlight-text">developers</strong> <br>
        We think <strong class="highlight-text">simple is best</strong> <br>
        We always try to consider <strong class="highlight-text">user's favorite</strong> <br>
        We are slow, but <strong class="highlight-text">do our best</strong> <br>
        
        Here are contributors for this page
      </p>
      
      <div class="my-3" justify-center>
        <v-btn 
          depressed large flat outline
          :class="{'red-color': buttonPicked}" color="#FAFAFA">
          
          <div @mouseover="buttonPick" @mouseleave="buttonPick" @click="moveToContributors"> 
            Contributors
          </div>
        </v-btn>
      </div>
    </div>

    <div id="contributors">
      <Contributors></Contributors>
    </div>
    
  </div>
</template>


<script>
/**
 * 소개 문구와 contributors chart를 출력하는 페이지
 */
import Contributors from './homes/Contributors'

export default {
  name: "AboutUs",

  components: {
    Contributors,
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
    
    moveToContributors() {
      let element = document.getElementById('contributors')
      element.scrollIntoView()
    }
  },

  data() {
    return {
      buttonPicked: false
    }
  }

}
</script>
