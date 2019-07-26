<template>
  <v-list>
    <v-flex
      v-for="i in portfolioComments.length"
    >
      <PortfolioComment
        :content="portfolioComments[i - 1].content"
      >
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
      portfolioComments: [],
    }
  },

  components: {
    PortfolioComment,
  },

  methods: {
    async getPortfolioComments() {
      this.portfolioComments = await CommentService.getPortfolioComments()
    },
  },

  mounted() {
    this.getPortfolioComments()
  },
}
</script>
