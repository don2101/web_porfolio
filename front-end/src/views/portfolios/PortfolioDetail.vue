<template>
  <div>
    <div class="my-5">
      <!-- title area -->
      <v-layout>
        <v-flex xs8>
          <v-text-field
          :value="title"
          color="#FAFAFA"
          dark outline single-line readonly
          height="50" style="font-size: 30px">
          </v-text-field>
        </v-flex>
      </v-layout>

      <v-layout>
        <!-- image view area -->
        <v-flex xs6 class="mr-3">
          <img id="image" v-bind:src="img" style="max-width: 100%">
        </v-flex>

        <!-- text view area -->
        <v-flex xs6>
          <v-textarea
            class="my-3" color="#FAFAFA"
            :value="body"
            outline dark auto-grow flat>
          </v-textarea>

          <!-- 뒤로가기 button -->
          <v-btn
            :class="{'red-color': this.buttonPicked}" color="#FAFAFA"
            flat outline to="/portfolios">
            <div @mouseover="buttonPick" @mouseleave="buttonPick">
              List
            </div>
          </v-btn>

        </v-flex>
      </v-layout>

    </div>
    <PortfolioCommentWrite/>
    <PortfolioCommentsList/>
  </div>
</template>


<script>
/**
 * portfolio의 상세 내용을 출력하는 component
 */
import Portfolio from './Portfolio'
import PortfolioService from '../../service/PortfolioService'
import PortfolioCommentsList from '../comments/PortfolioCommentsList'
import PortfolioCommentWrite from '../comments/PortfolioCommentWrite'
import { get } from 'http';

export default {
	name: 'PortfolioDetail',

  components: {
    PortfolioService,
    PortfolioCommentsList,
    PortfolioCommentWrite,
  },

  data () {
    return {
      title : '',
      body : '',
      img : '',
      index: 0,
      msg: 'Hey Nic Raboy',
      portfolios: {},
      buttonPicked: false,
    }

  },

  async created() {
    await this.getPortfolio()
    this.setPortfolio()
  },

  methods: {
    async getPortfolio() {this.portfolios = await PortfolioService.getPortfolios()},

    setPortfolio() {
      this.title = this.portfolios[this.idx-1].title
      this.body = this.portfolios[this.idx-1].content
      this.img = this.portfolios[this.idx-1].pfImg
    },

    buttonPick() { this.buttonPicked = !this.buttonPicked },

  },

  computed: {
    idx() {
      return this.$route.query.idx
    },
  },

}
</script>
