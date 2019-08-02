<template>
<v-list>
  <v-flex
    v-for="i in comments.length"
    class="list">
    <PortfolioComment
      :pfcomId="comments[i - 1].pfcomId"
      :content="comments[i - 1].content"
      :date="comments[i - 1].date"
      :pfId="comments[i - 1].pfId"
      :mid="comments[i - 1].mid"
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
    else  this.getPostComments(this.postId)
  },
}
</script>
<style>
.list {
  background: #1a1c33;
}
</style>
