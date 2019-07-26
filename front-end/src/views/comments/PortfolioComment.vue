<template>
  <div>
    <p>{{ content }} {{ mid }}</p>
    <v-btn
      @click="update()"
    >
      수정
    </v-btn>
    <v-btn
      @click="deletePortfolioComment(pfcomId)"
    >
      삭제
    </v-btn>
    <div
      v-if="isUpdated"
    >
      <PortfolioCommentWrite
        :pfcomId="pfcomId"
        :content="content"
        :pfId="pfId"
        :isUpdated=true
      >
      </PortfolioCommentWrite>
    </div>
  </div>
</template>


<script>
import CommentService from '../../service/CommentService.js'
import PortfolioCommentWrite from './PortfolioCommentWrite'
export default {
  name: 'PortfolioComment',

  data() {
    return {
      isUpdated: false,
    }
  },

  props: {
    pfcomId: {type: String},
    content: {type: String},
    date: {type: String},
    pfId: {type: String},
    mid: {type: String},
  },

  components: {
    PortfolioCommentWrite,
  },

  methods: {
    async deletePortfolioComment() {
      let response = [];
      response = await CommentService.deletePortfolioComment(this.pfcomId)
      alert(response);
    },

    update() {
      this.isUpdated = !this.isUpdated;
    },
  },
}

</script>
