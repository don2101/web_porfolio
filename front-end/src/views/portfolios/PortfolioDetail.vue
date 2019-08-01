<template>
<div>
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
  </div>
  <PortfolioCommentWrite
    :pfId="this.$route.query.idx">
  </PortfolioCommentWrite>
  <PortfolioCommentsList
    :pfId="this.$route.query.idx">
  </PortfolioCommentsList>
  <!-- <Disqus/> -->
</div>
</template>


<script>
/**
 * portfolio의 상세 내용을 출력하는 component
 */
import Portfolio from './Portfolio'
import PortfolioService from '../../service/PortfolioService'
import PortfolioCommentWrite from '../comments/PortfolioCommentWrite'
import PortfolioCommentsList from '../comments/PortfolioCommentsList'
import { get } from 'http';

export default {
	name: 'PortfolioDetail',

  components: {
    PortfolioService,
    PortfolioCommentWrite,
    PortfolioCommentsList,
  },

  data() {
    return {
      title: '',
      body: '',
      img: '',
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
      for (var i = 0; i < this.portfolios.length; i++) {
        if (this.idx == this.portfolios[i].pfId) {
          this.title = this.portfolios[i].title
          this.body = this.portfolios[i].content
          this.img = this.portfolios[i].pfImg
        }
      }

    },

    buttonPick() {
      this.buttonPicked = !this.buttonPicked
    },

  },

  computed: {
    idx() {
      return this.$route.query.idx
    },
  },

}
</script>
