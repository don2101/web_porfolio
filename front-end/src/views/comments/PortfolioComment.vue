<template>
<div>
  <div
    v-if="isUpdated">
    <PortfolioCommentWrite
      :pfcomId="pfcomId"
      :content="content"
      :pfId="pfId"
      :isUpdated="true"
      v-on:update="update()">
    </PortfolioCommentWrite>
  </div>

  <div v-else>
    <v-layout>
      <v-flex xs11>
        <v-textarea
          v-model="content"
          readonly dark no-resize rows="1">
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
    content: {
      type: String
    },
    date: {
      type: String
    },
    pfId: {
      type: String
    },
    mid: {
      type: String
    },
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

    isUpdatable() {
      return this.mid == this.$store.state.mid
    },
  },
}
</script>
