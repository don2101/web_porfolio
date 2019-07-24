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
        </v-flex>

        <!-- text view area -->
        <v-flex xs6>
          <imageUploader @passUploadImage="setImageSource" />

          <v-textarea
            class="my-3" color="#FAFAFA"
            :value="body"
            outline dark auto-grow flat>
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

  data () {
    return {
      title : '',
      body : '',
      imageSource : '',
      index: 0,
      msg: 'Hey Nic Raboy',
      // portfolios: {},
      portfolio: [],
      updateButtonPicked: false,
      cancleButtonPicked: false,
    }

  },

  async created() {
    await this.getPortfolio()
    this.setPortfolio()
  },

  methods: {
    async getPortfolio() {
      // this.portfolios = await PortfolioService.getPortfolios()
      this.portfolio= await PortfolioService.getPortfolio(this.pf_id);
    },

    setPortfolio() {
      this.title = this.portfolio.title
      this.body = this.portfolio.content
      this.imageSource = this.portfolio.pfImg
    },

    async updatePortfolio(){
      alert("updatePortfolio")
      await PortfolioService.updatePortfolio(this.portfolio)
    },

    updateButtonPick() { this.updateButtonPicked = !this.updateButtonPicked },

    cancleButtonPick() { this.cancleButtonPicked = !this.cancleButtonPicked },

    setImageSource(resultLink) {
      this.imageSource = resultLink
    },

  },

  computed: {
    pf_id() {
      return this.$route.query.pf_id
    },
  },

}
</script>
