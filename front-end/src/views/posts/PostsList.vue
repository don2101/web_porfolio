<template>
  <div class="mt-5">

    <v-layout class="post-header">
      <v-flex xs2>
        <p>Writer</p>
      </v-flex>
      <v-flex xs8>
        <p>Title</p>
      </v-flex>
      <v-flex xs2>
        <p>Date</p>
      </v-flex>
    </v-layout>

    <hr class="post-header-hr"/>

    <div v-for="(post, i) in posts" :key="i">
      <div v-if="i < pageNum*pageSize && i >= (pageNum-1)*pageSize">
        <router-link :to="{ name: 'postDetail', query: { 'pid': post.postId } }">
            <Post
              :title="post.title"
              :date="post.date"
              :writer="post.name">
            </Post>
        </router-link>
      </div>
    </div>
    
    <v-layout justify-center>
      <v-btn @click="prevPage"> prev </v-btn>
      <p class="post-header mt-2 mx-3" style="font-size: 1.5rem"> {{ this.pageNum }} / {{ this.totalPage }}  page </p>
      <v-btn @click="nextPage"> next </v-btn>
    </v-layout>
  </div>
</template>


<script>
/**
 * Post list를 가져와 Post.vue에 출력
 */
import PostService from '../../service/PostService'
import Post from './Post'

export default {
  name: "PostsList",
  
  components: {
    Post,
  },

  data() {
    return {
      posts: [],
      pageNum: 1,
      pageSize: 10,
      totalPage: 0,
    }
  },

  mounted() {
    this.requestPostList();
  },

  methods: {
    // post List를 요청하고 저장하는 기능
    async requestPostList() {
      this.posts = await PostService.getPostList();
      this.totalPage = parseInt(this.posts.length/11) + 1;
    },

    nextPage() {
      if((this.pageNum)*this.pageSize < this.posts.length) this.pageNum++;
      else alert('마지막 페이지 입니다.')
    },

    prevPage() {
      if(this.pageNum == 1) alert('첫번째 페이지 입니다.')
      else this.pageNum--;
    }
  },

}
</script>


<style>

</style>
