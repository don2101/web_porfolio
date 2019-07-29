<template>
  <v-list>
    <v-flex
      v-for="i in portfolioComments.length"
      v-if="pfId == portfolioComments[i - 1].pfId"
    >
        <PortfolioComment
          :pfcomId="portfolioComments[i - 1].pfcomId"
          :content="portfolioComments[i - 1].content"
          :date="portfolioComments[i - 1].date"
          :pfId="portfolioComments[i - 1].pfId"
          :mid="portfolioComments[i - 1].mid"
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

  props: {
    pfId: {type: String},
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
