<template>
  <v-layout mt-5 wrap>

    <v-flex v-for="i in portfolios.length > limits ? limits : portfolios.length" xs4 sm4 md4>
      <router-link :to="{ name: 'portfolioDetail', query: { 'pf_id': portfolios[i-1].pfId } }">
        <Portfolio class="ma-3"
                :date="portfolios[i - 1].date"
                :title="portfolios[i - 1].title"
                :body="portfolios[i - 1].content"
                :imgSrc="portfolios[i - 1].pfImg"
                ></Portfolio>
      </router-link>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn depressed large flat outline
        :class="{'red-color': buttonPicked}"
        color="#FAFAFA"
        v-on:click="loadMorePortfolios">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>
          <div @mouseover="buttonPick" @mouseleave="buttonPick">
          더 보기
          </div>
      </v-btn>
    </v-flex>

  </v-layout>
</template>


<script>
/**
 * portfolio list를 가져와 Portfolio.vue로 넘기는 component
 */
import Portfolio from './Portfolio'
import PortfolioService from '../../service/PortfolioService'


export default {
  name: 'PortfoliosList',

  components: {
		Portfolio
  },

	props: {
		limits: {type: Number},
    loadMore: {type: Boolean, default: true}
  },

	data() {
		return {
      portfolios: [],
      buttonPicked: false
		}
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },

		async getPortfolios() {
      this.portfolios = await PortfolioService.getPortfolios()
    },

		loadMorePortfolios() {
      this.limits+=3;

      if(this.limits >= this.portfolios.length){
        this.loadMore = false;
      }
    },
	},

	mounted() {
		this.getPortfolios()
  },
}
</script>
