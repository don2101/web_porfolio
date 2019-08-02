<template>
<div>
  <div
    v-if="isUpdated">
    <PortfolioCommentWrite
      :pfcomId="pfcomId"
      :content="content"
      :pfId="pfId"
      :isUpdated="true"
      :isPortfolio="isPortfolio"
      v-on:update="update()">
    </PortfolioCommentWrite>
  </div>

  <div v-else>
    <v-layout>
      <v-flex xs10 offset-xs1>
        <v-textarea
          v-model="content"
          readonly dark auto-grow rows="1" color="#FAFAFA">
        </v-textarea>
      </v-flex>
      <v-flex
        v-if="isUpdatable()"
        xs1>
        <v-menu
          bottom left>
          <template v-slot:activator="{ on }">
            <v-btn
              dark icon v-on="on">
              <v-icon>more_vert</v-icon>
            </v-btn>
          </template>
          <v-list>
            <v-list-tile @click="update()">
              <v-list-tile-title>
                <v-icon left>create</v-icon>EDIT
              </v-list-tile-title>
            </v-list-tile>
            <v-list-tile @click="deletePortfolioComment(pfcomId)">
              <v-list-tile-title>
                <v-icon left>delete</v-icon>DELETE
              </v-list-tile-title>
            </v-list-tile>
          </v-list>
        </v-menu>

      </v-flex>
    </v-layout>
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
    pfcomId: {
      type: String
    },
    postComId: {
      type: String
    },
    content: {
      type: String
    },
    date: {
      type: String
    },
    pfId: {
      type: String
    },
    postId: {
      type: String
    },
    mid: {
      type: String
    },
    isPortfolio: {
      type: Boolean,
      default: false
    },
  },

  components: {
    PortfolioCommentWrite,
  },

  methods: {
    async deletePortfolioComment() {
      let jsonData = [];
      if (this.isPortfolio) {
        jsonData = {
          pfcomId: this.pfcomId,
          pfId: this.pfId,
        }
      } else {
        jsonData = {
          postComId: this.postComId,
          postId: this.postId,
        }
      }
      let response = [];
      if (this.isPortfolio) response = await CommentService.deletePortfolioComment(jsonData)
      else response = await CommentService.deletePostComment(jsonData)
    },

    update() {
      this.isUpdated = !this.isUpdated;
    },

    isUpdatable() {
      return this.mid == this.$store.state.memberId
    },
  },
}
</script>
