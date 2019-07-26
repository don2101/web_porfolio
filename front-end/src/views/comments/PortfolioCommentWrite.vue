<template>
  <div>
    <div v-if="isUpdated">
      <form @submit.prevent="updatePortfolioComment">
        <v-text-field
          v-model="content"
        >
        </v-text-field>
        <v-btn
          :class="{'red-color': this.buttonPicked}" color="#FAFAFA"
          flat outline>
          <input type="submit" value="WRITE" @mouseover="buttonPick" @mouseleave="buttonPick">

        </v-btn>
      </form>
    </div>
    <div v-else>
      <form @submit.prevent="postPortfolioComment">
        <v-text-field
          v-model="content"
        >
        </v-text-field>
        <v-btn
          :class="{'red-color': this.buttonPicked}" color="#FAFAFA"
          flat outline>
          <input type="submit" value="WRITE" @mouseover="buttonPick" @mouseleave="buttonPick">

        </v-btn>
      </form>
    </div>

  </div>
</template>


<script>
import CommentService from '../../service/CommentService.js'
export default {
  name: 'PortfolioCommentWrite',

  data() {
    return {
      content: "",
      buttonPicked: false,
    }
  },

  props: {
    pfcomId: {type: String},
    content: {type: String},
    pfId: {type: String},
    isUpdated: {type: Boolean, default: false},
  },

  methods: {
    async postPortfolioComment() {
      let jsonData = {
        content: this.content,
        pfId: this.pfId,
      };
      let response = [];
      response = await CommentService.postPortfolioComment(jsonData)
      alert(response);
    },

    async updatePortfolioComment() {
      let jsonData = {
        pfcomId: this.pfcomId,
        content: this.content,
        pfId: this.pfId,
      };
      let response = [];
      response = await CommentService.updatePortfolioComment(jsonData)
    },

    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },
}


</script>
