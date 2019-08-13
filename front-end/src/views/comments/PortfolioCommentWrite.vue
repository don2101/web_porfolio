<template>
<div>
  <!-- comment update -->
  <div v-if="isUpdated">
    <form @submit.prevent="updatePortfolioComment">
      <!-- input area -->
      <v-layout>
        <v-flex xs10 offset-xs1>
          <v-text-field
            v-model="updateInput"
            dark color="#E53935">
          </v-text-field>
        </v-flex>
      </v-layout>

      <!-- button group -->
      <v-layout justify-end>
        <v-btn
          color="#FAFAFA" flat
          @click="cancel">
          Cancel
        </v-btn>
        <v-btn
          :class="{'red-color': this.buttonPicked}"
          color="#FAFAFA" flat outline>
          <input
            type="submit" value="Update" @mouseover="buttonPick" @mouseleave="buttonPick">
        </v-btn>
        <v-flex xs1>
        </v-flex>
      </v-layout>
    </form>
  </div>

  <!-- comment add -->
  <div v-else>
    <form @submit.prevent="postPortfolioComment">
      <!-- input area -->
      <v-text-field
        v-model="contentInput"
        dark placeholder="댓글 추가..."
        color="#E53935">
      </v-text-field>

      <!-- button group -->
      <v-layout justify-end>
        <v-btn
          color="#FAFAFA" flat @click="cancel">
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
      updateInput: this.$props.content,
      buttonPicked: false,
    }
  },

  props: {
    pfcomId: {
      type: Number
    },
    postcomId: {
      type: Number
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
          mid: this.$store.state.mid,
        }
      } else {
        jsonData = {
          content: this.contentInput,
          postId: this.postId,
          mid: this.$store.state.mid,
        }
      }
      let response = [];
      if (this.isPortfolio)  response = await CommentService.postPortfolioComment(jsonData)
      else  response = await CommentService.postPostComment(jsonData)
    },

    async updatePortfolioComment() {
      let jsonData = [];
      console.log(this.pfId)

      if (this.isPortfolio) {
        jsonData = {
          pfcomId: this.pfcomId,
          content: this.updateInput,
          pfId: this.pfId,
        }
      } else {
        jsonData = {
          postcomId: this.postcomId,
          content: this.updateInput,
          postId: this.postId,
        }
      }

      let response = [];
      if (this.isPortfolio)  response = await CommentService.updatePortfolioComment(jsonData)
      else  response = await CommentService.updatePostComment(jsonData)

    },

    buttonPick() {
      this.buttonPicked = !this.buttonPicked
    },

    cancel() {
      this.$emit('update')
    },


  },
}
</script>
