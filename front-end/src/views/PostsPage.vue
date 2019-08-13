<template>
  <div>
    <h1 class="main-title">Posts</h1>

    <!-- write post button -->
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

    <!-- post list -->
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

  data() {
    return {
      buttonPicked: false,
      isAdmin: false,
    }
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
    
    async checkGrade() {
      const grade = await AdminService.getGrade(sessionStorage.getItem("jwt"));
      
      if(grade === '0' || grade === '1') this.isAdmin = true;
      else this.isAdmin = false;
    }
  },

  mounted(){
    this.checkGrade();
  },

}
</script>
