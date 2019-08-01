<template>
<div>
  <div v-if="isUpdated">
    <form @submit.prevent="updatePortfolioComment">
      <v-text-field
        v-model="content"
        dark>
      </v-text-field>
      <v-layout justify-end>
        <v-btn
          color="#FAFAFA" flat
          @click="update()">
          수정취소
        </v-btn>
        <v-btn
          :class="{'red-color': this.buttonPicked}"
          color="#FAFAFA" flat outline>
          <input
            type="submit" value="SAVE" @mouseover="buttonPick" @mouseleave="buttonPick">
        </v-btn>
      </v-layout>
    </form>
  </div>
  <div v-else>
    <form @submit.prevent="postPortfolioComment">
      <v-text-field
        v-model="contentInput"
          dark placeholder="댓글 추가...">
      </v-text-field>
      <v-layout justify-end>
        <v-btn
          color="#FAFAFA" flat>
          <input
            type="reset" value="CANCEL">
        </v-btn>
        <v-btn
          :class="{'red-color': this.buttonPicked}"
          color="#FAFAFA" flat outline>
          <input
            type="submit" value="WRITE" @mouseover="buttonPick" @mouseleave="buttonPick">
        </v-btn>
      </v-layout>
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
      contentInput: "",
      buttonPicked: false,
    }
  },

  props: {
    pfcomId: {
      type: String
    },
    content: {
      type: String
    },
    pfId: {
      type: String
    },
    isUpdated: {
      type: Boolean,
      default: false
    },
  },

  methods: {
    async postPortfolioComment() {
      let jsonData = {
        content: this.contentInput,
        pfId: this.pfId,
        mid: this.$store.state.mid,
      };
      let response = [];
      response = await CommentService.postPortfolioComment(jsonData)
      alert(response);
    },

    async updatePortfolioComment() {
      alert(this.pfId)
      let jsonData = {
        pfcomId: this.pfcomId,
        content: this.content,
        pfId: this.pfId,
      };
      let response = [];
      response = await CommentService.updatePortfolioComment(jsonData)
      // window.location.href = "detail?idx=" + this.pfId;
      this.$router.push({
        path: 'detail',
        query: {
          idx: this.pfId
        }
      })
    },

    buttonPick() {
      this.buttonPicked = !this.buttonPicked
    },

    update() {
      this.$emit('update')
    },
  },
}
</script>
