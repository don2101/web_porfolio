<template>
<div>
  <div v-if="isUpdated">
    <form @submit.prevent="updatePortfolioComment">
      <v-layout>
        <v-flex xs10 offset-xs1>
          <v-text-field
            v-model="content"
            dark>
          </v-text-field>
        </v-flex>
      </v-layout>
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
        <v-flex xs1>
        </v-flex>
      </v-layout>
    </form>
  </div>
  <div v-else>
    <form @submit.prevent="postPortfolioComment">
      <v-text-field
        v-model="contentInput"
        dark placeholder="댓글 추가..."
        color="#E53935">
      </v-text-field>
      <v-layout justify-end>
        <v-btn
          color="#FAFAFA" flat @click="cancle()">
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
    postComId: {
      type: String
    },
    content: {
      type: String
    },
    pfId: {
      type: String
    },
    postId: {
      type: String
    },
    isUpdated: {
      type: Boolean,
      default: false
    },
    isPortfolio: {
      type: Boolean,
      default: false
    },
  },

  methods: {
    async postPortfolioComment() {
      let jsonData = [];
      if (this.isPortfolio) {
        jsonData = {
          content: this.contentInput,
          pfId: this.pfId,
          mid: this.$store.state.memberId,
        }
      } else {
        jsonData = {
          content: this.contentInput,
          postId: this.postId,
          mid: this.$store.state.memberId,
        }
      }
      let response = [];
      if (this.isPortfolio)  response = await CommentService.postPortfolioComment(jsonData)
      else  response = await CommentService.postPostComment(jsonData)
    },

    async updatePortfolioComment() {
      let jsonData = [];
      if (this.isPortfolio) {
        jsonData = {
          pfcomId: this.pfcomId,
          content: this.content,
          pfId: this.pfId,
        }
      } else {
        jsonData = {
          postComId: this.postComId,
          content: this.content,
          postId: this.postId,
        }
      }
      let response = [];
      if (this.isPortfolio)  response = await CommentService.updatePortfolioComment(jsonData)
      else  response = await CommentService.updatePostComment(jsonData)
      // this.$router.push({
      //   path: 'detail',
      //   query: {
      //     pfId: this.pfId
      //   }
      // })
    },

    buttonPick() {
      this.buttonPicked = !this.buttonPicked
    },

    update() {
      this.$emit('update')
    },

    // cancel() {
    //   this.contentInput = ""
    // },
  },
}
</script>
