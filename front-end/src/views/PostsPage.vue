<template>
  <div>
    <h1 class="main-title my-2">Posts</h1>
    <v-btn
    depressed large flat outline
      :class="{'red-color': buttonPicked}"
      color="#FAFAFA"
      to="/posts/write"
      v-if="isAuthorized">

      <div @mouseover="buttonPick" @mouseleave="buttonPick">
        Write Post
      </div>
    </v-btn>

  </div>
</template>


<script>
/**
 * 전체 post list를 가져와 출력하는 페이지
 */

import PostsList from './posts/PostsList'

export default {
  name: 'PostPage',

  components: {
    PostsList,
  },

  created() {
    this.requestPostList()
  },

  methods: {
    // post List를 요청하고 저장하는 기능
    requestPostList() {
      //
    },

    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },

  computed: {
    // post list를 vuex에서 가져와 관리

    getPostsList() {
      // return
    },

    // 관리자인지 확인하는 isAdmin
    isAuthorized() {
      const isAdmin = this.$store.state.isAdmin;
      const isLoggedIn = this.$store.state.isLoggedIn;
      if (isAdmin && isLoggedIn)
        return true;
    }
  },

  data() {
    return {
      buttonPicked: false
    }
  }

}
</script>
