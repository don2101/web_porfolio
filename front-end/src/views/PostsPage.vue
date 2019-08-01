<template>
  <div>
    <h1 class="main-title my-2">Posts</h1>
    <v-btn
    depressed large flat outline
      :class="{'red-color': buttonPicked}"
      color="#FAFAFA"
      to="/posts/write"
      v-if="isAdmin">

      <div @mouseover="buttonPick" @mouseleave="buttonPick">
        Write Post
      </div>
    </v-btn>

    <PostsList />

  </div>
</template>


<script>
/**
 * 전체 post list를 가져와 출력하는 페이지
 */

import PostsList from './posts/PostsList'
import AdminService from '../service/AdminService'

export default {
  name: 'PostPage',

  components: {
    PostsList,
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
  },

  async created(){
    const grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
    if(grade === '0') {
      this.isAdmin=true;
    }else{
      this.isAdmin=false;
    }
  },

  data() {
    return {
      buttonPicked: false,
      isAdmin: false,
    }
  }

}
</script>
