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
        <v-flex xs2>
          <div style="float: right">{{date}}</div>
        </v-flex>
        <v-flex xs2>
          <div style="float: right">{{count}}</div>
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
            outline dark auto-grow flat readonly>
          </v-textarea>

          <!-- Update button -->
          <v-btn
            :class="{'red-color': this.updateButtonPicked}" v-if="isAuthorized" color="#FAFAFA"
            flat outline :to="{ name: 'portfolioUpdate', query: { 'pfId': this.pfId }}">
            <div @mouseover="updateButtonPick" @mouseleave="updateButtonPick">
              Update
            </div>
          </v-btn>

          <!-- Delete button -->
          <v-btn
            :class="{'red-color': this.deleteButtonPicked}" v-if="isAuthorized" color="#FAFAFA"
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
      count: 0,
      date: '',
      msg: 'Hey Nic Raboy',
      // portfolios: {},
      portfolio: [],
      listButtonPicked: false,
      updateButtonPicked: false,
      deleteButtonPicked: false,

    }

  },

  async created() {
    await this.getPortfolio()
    this.setPortfolio()
  },

  methods: {
    async getPortfolio() {
      // this.portfolios = await PortfolioService.getPortfolios()
      this.portfolio= await PortfolioService.getPortfolio(this.pfId);
    },

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

    isAuthorized() {
      const isAdmin = this.$store.state.isAdmin;
      const isLoggedIn = this.$store.state.isLoggedIn;
      if (isAdmin && isLoggedIn)
        return true;
    },
  },

}
</script>
