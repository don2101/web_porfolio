<template>
  <div>

    <form @submit.prevent="postPortfolio">

      <!-- title -->
      <v-layout class="write-title-input">
        <v-flex xs12>
          <v-text-field
          v-model="title"
          dark
          color="#FAFAFA"
          outline single-line
          height="50" style="font-size: 30px" name="title" required></v-text-field>
        </v-flex>
      </v-layout>


      <!-- text view area -->
      <v-layout justify-center>
        <div>
          <v-img class="portfolio-image" id="image" v-bind:src="imageSource" style="max-width: 100%"></v-img>
          
          <div class="portfolio-image">
            <imageUploader @passUploadImage="setImageSource" />
          </div>
        </div>
      </v-layout>

      <!-- text area -->
      <v-textarea
        color="#FAFAFA" class="portfolio-text" id="text-line"
        rows="10"
        outline dark auto-grow flat
        v-model="content" name="content" required>
      </v-textarea>


      <!-- submit button -->
      <v-layout justify-end>
        <v-btn
            :class="{'red-color': this.updateButtonPicked}" color="#FAFAFA"
            flat outline @click="updatePortfolio">
            <div @mouseover="updateButtonPick" @mouseleave="updateButtonPick">
              Update Complete
            </div>
          </v-btn>

          <!-- 뒤로가기 button -->
          <v-btn
            :class="{'red-color': this.cancleButtonPicked}" color="#FAFAFA"
            flat outline :to="{ name: 'portfolios'}">
            <div @mouseover="cancleButtonPick" @mouseleave="cancleButtonPick">
              Cancel
            </div>
          </v-btn>
      </v-layout>

    </form>
  </div>
</template>


<script>
/**
 * portfolio의 상세 내용을 출력하는 component
 */
import Portfolio from './Portfolio'
import PortfolioService from '../../service/PortfolioService'
import imageUploader from '../../components/image/ImageUploader'

export default {
	name: 'PortfolioDetail',
  
  components: {
    PortfolioService,
    imageUploader,
  },

  methods: {
    // GET portfolios
    async getPortfolio() {
      this.portfolio = await PortfolioService.getPortfolio(this.pfId);
    },

    setPortfolio() {
      this.title = this.portfolio.title
      this.content = this.portfolio.content
      this.imageSource = this.portfolio.img
      this.count = this.portfolio.count
    },

    // PUT portfolios
    async updatePortfolio() {
      let jsonData = {
        title: this.title,
        content: this.content,
        count: this.count,
        date: Date.now().toString(),
        img: this.imageSource,
        mid: this.$store.state.memberId,
      };
      
      await PortfolioService.updatePortfolio(this.pfId, jsonData)
    },

    updateButtonPick() { this.updateButtonPicked = !this.updateButtonPicked },

    cancleButtonPick() { this.cancleButtonPicked = !this.cancleButtonPicked },

    setImageSource(resultLink) {
      this.imageSource = resultLink
    },

  },

  async created() {
    await this.getPortfolio()
    this.setPortfolio()
    
    if(sessionStorage.getItem("mid") !== this.portfolio.mid){
      alert("권한이 없습니다.")
      window.location.href="/portfolios"
    }
  },

  data () {
    return {
      title : '',
      content : '',
      imageSource : '',
      count: 0,
      portfolio: [],
      updateButtonPicked: false,
      cancleButtonPicked: false,
    }

  },

  computed: {
    pfId() {
      return this.$route.query.pfId
    },
  },

}
</script>
