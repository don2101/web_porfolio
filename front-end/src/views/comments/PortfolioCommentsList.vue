<template>
  <v-list style="padding: 3px 0px">
    <v-flex
      v-for="(comment, i) in comments" :key="i"
      class="list">
      <PortfolioComment
        :pfcomId=comment.pfcomId
        :postcomId=comment.postcomId
        :content="comment.content"
        :date="comment.date"
        :pfId="pfId"
        :postId="postId"
        :mid=comment.mid
        :name="comment.name"
        :isPortfolio="isPortfolio">
      </PortfolioComment>
    </v-flex>
  </v-list>
</template>

<script>
import CommentService from '../../service/CommentService.js'
import PortfolioComment from './PortfolioComment'

export default {
  name: 'PortfolioCommentsList',

  data() {
    return {
      comments: [],
    }
  },

  props: {
    pfId: {
      type: String
    },
    postId: {
      type: String
    },
    isPortfolio: {
      type: Boolean,
      default: false
    },
  },

  components: {
    PortfolioComment,
  },

  methods: {
    async getPortfolioComments(pfId) {
      this.comments = await CommentService.getPortfolioComments(pfId)
    },

    async getPostComments(postId) {
      this.comments = await CommentService.getPostComments(postId)
    },
  },

  mounted() {
    if (this.isPortfolio) this.getPortfolioComments(this.pfId)
    else this.getPostComments(this.postId)
  },
}
</script>
<style>
.list {
  background: #1a1c33;
}
</style>
