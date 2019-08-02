<template>
  <div>
    <div class="my-5">
      <!-- title area -->
      <v-layout>
        <v-flex xs8>
          <v-text-field
          :value="title"
          color="#FAFAFA"
          dark outline single-line
          height="50" style="font-size: 30px">
          </v-text-field>
        </v-flex>
      </v-layout>

      <v-layout>
        <!-- image view area -->
        <v-flex xs6 class="mr-3">
          <img id="image" v-bind:src="imageSource" style="max-width: 100%">

          <div id="registedImages">
            <v-textarea
              background-color="transparent" color="#FAFAFA"
              auto-grow dark solo readonly flat
              v-bind:value="content">
              {{ imageSource }}
            </v-textarea>
          </div>
        </v-flex>

        <!-- text view area -->
        <v-flex xs6>
          <imageUploader @passUploadImage="setImageSource" />

          <v-textarea
            class="my-3" color="#FAFAFA"
            outline dark auto-grow flat
            v-model="content" name="content" required>

          </v-textarea>

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

        </v-flex>
      </v-layout>

    </div>
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
