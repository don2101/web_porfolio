<template>
  <div>
    <!-- title area -->
    <h1 class="main-title portfolio-title">{{ title }}</h1>
    <hr/>

    <!-- image view area -->
    <v-layout justify-center>
      <v-img id="image" class="portfolio-image" v-bind:src="img" style="max-width: 70%"></v-img>
    </v-layout>
    
    <!-- text view area -->
    <v-textarea
      dark readonly row-height="1" flat auto-grow solo
      :value="body" background-color="#1a1c33"
      class="portfolio-text" id="text-line">
    </v-textarea>

    <hr class="mb-3"/>
    
    <!-- button group -->
    <v-layout justify-end>
      <div>
        <!-- Update button -->
        <v-btn
          :class="{'red-color': this.updateButtonPicked}" v-if="isWriter" color="#FAFAFA"
          flat outline :to="{ name: 'portfolioUpdate', query: { 'pfId': this.pfId }}">
          <div @mouseover="updateButtonPick" @mouseleave="updateButtonPick">
            Update
          </div>
        </v-btn>

        <!-- Delete button -->
        <v-btn
          :class="{'red-color': this.deleteButtonPicked}" v-if="isWriter" color="#FAFAFA"
          flat outline @click="deletePortfolio">
          <div @mouseover="deleteButtonPick" @mouseleave="deleteButtonPick">
            Delete
          </div>
        </v-btn>

        <!-- 뒤로가기 button -->
        <v-btn
          :class="{'red-color': this.listButtonPicked}" color="#FAFAFA"
          flat outline :to="{ name: 'portfolios'}">
          <div @mouseover="listButtonPick" @mouseleave="listButtonPick">
            List
          </div>
        </v-btn>

      </div>
    </v-layout>
    

    <!-- Comment Group -->
    <div class="mt-5">
      <PortfolioCommentWrite
        :pfId="pfId"
        :isPortfolio="isPortfolio">
      </PortfolioCommentWrite>
      
      <div class="mt-5">
        <PortfolioCommentsList
          :pfId="pfId"
          :isPortfolio="isPortfolio">
        </PortfolioCommentsList>
      </div>
    </div>
    <!-- <Disqus/> -->
  </div>
</template>


<script>
/**
 * portfolio의 상세 내용을 출력하는 component
 */
import PortfolioService from '../../service/PortfolioService'
import PortfolioCommentWrite from '../comments/PortfolioCommentWrite'
import PortfolioCommentsList from '../comments/PortfolioCommentsList'

export default {
	name: 'PortfolioDetail',

  components: {
    PortfolioCommentWrite,
    PortfolioCommentsList,
  },

  data() {
    return {
      title: '',
      body: '',
      img: '',
      index: 0,
      count: 0,
      date: '',
      portfolio: [],
      portId: 0,
      listButtonPicked: false,
      updateButtonPicked: false,
      deleteButtonPicked: false,
      isPortfolio: this.pfId == "" ? false : true,
    }

  },

  async created() {
    await this.getPortfolio()
    this.setPortfolio()
  },

  methods: {
    // GET portfolio
    async getPortfolio() {
      this.portfolio = await PortfolioService.getPortfolio(this.pfId);
    },

    // DELETE portfolio
    async deletePortfolio(){
      await PortfolioService.deletePortfolio(this.pfId);
    },

    setPortfolio() {
      this.title = this.portfolio.title
      this.body = this.portfolio.content
      this.img = this.portfolio.img
      this.count = this.portfolio.count
      this.date = this.portfolio.date      
    },

    listButtonPick() { this.listButtonPicked = !this.listButtonPicked },

    updateButtonPick() { this.updateButtonPicked = !this.updateButtonPicked },

    deleteButtonPick() { this.deleteButtonPicked = !this.deleteButtonPicked },
  },

  computed: {
    pfId() {
      return this.$route.query.pfId
    },

    isWriter(){
      return this.portfolio.mid === sessionStorage.getItem("mid")
    },
  },

}
</script>


<style>
#text-line {
  line-height: 3rem !important
}
</style>
