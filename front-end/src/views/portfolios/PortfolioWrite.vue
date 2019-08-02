<template>
  <div>
    <div class="my-5">
      <!-- title -->
      <form @submit.prevent="postPortfolio">
        <v-layout>
          <v-flex xs12>
            <v-text-field
            v-model="title"
            dark
            color="#FAFAFA"
            outline single-line
            height="50" style="font-size: 30px" name="title" required></v-text-field>
          </v-flex>
        </v-layout>

        <v-layout>
          <!-- text view area -->
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

          <!-- text area -->
          <v-flex xs6>
            <imageUploader @passUploadImage="setImageSource" />

            <v-textarea
              class="my-3" color="#FAFAFA"
              outline dark auto-grow flat
              v-model="content" name="content" required>

            </v-textarea>

            <!-- submit button -->
            <v-btn
              :class="{'red-color': this.buttonPicked}" color="#FAFAFA"
              flat outline>
              <input type="submit" value="WRITE" @mouseover="buttonPick" @mouseleave="buttonPick">

            </v-btn>

          </v-flex>
        </v-layout>
      </form>
    </div>
  </div>
</template>


<script>
/**
 * 사용자로 부터 입력을 받아 portfolios를 post하는 component
 */

import imageUploader from '../../components/image/ImageUploader'
import PortfolioService from '../../service/PortfolioService'


export default {
  name: 'PortfolioWrite',
  
  components: {
    imageUploader,
  },

  data() {
    return {
      title: "",
      content: "",
      imageSource: "http://dy.gnch.or.kr/img/no-image.jpg",
      buttonPicked: false,
    }
  },

  methods: {
    // POST portfolio
    async postPortfolio() {
      
      const jsonData = {
        title: this.title,
        content: this.content,
        count: 0,
        date: Date.now().toString(),
        img: this.imageSource,
        mid: this.getMemberId,
      };
      
      console.log(jsonData.mid)
      let response = [];
      response = await PortfolioService.postPortfolio(jsonData)
    },

    // set portfolio Image
    setImageSource(resultLink) {
      this.imageSource = resultLink
    },

    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },

  computed: {
    getMemberId(){
      return sessionStorage.getItem('mid')
    }
  }
}
</script>
