<template>
  <div>
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

  methods: {
    async postPortfolioComment() {
      let jsonData = {
        content: this.content,
        // pfId: this.pfId,
        // mid: this.mid,
      };
      let response = [];
      response = await CommentService.postPortfolioComment(jsonData)
      alert(response);
    },

    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },
}


</script>
