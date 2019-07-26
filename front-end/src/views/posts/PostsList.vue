<template>
  <div class="mt-5">
    <v-card>
      <v-list two-line dark>
        <v-list-tile v-for="i in posts.length" xs12>
          <router-link :to="{ name: 'postDetail', query: { 'idx': posts[i-1].postId } }">
            <v-list-tile-content>
              <Post
                :title="posts[i-1].title"
                :date="posts[i-1].date"
                :writer="posts[i-1].mid">
              </Post>

            </v-list-tile-content>
          </router-link>
        </v-list-tile>
      </v-list>
    </v-card>
    
  </div>
</template>


<script>
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
    }
  },

  mounted() {
    this.requestPostList();
  },

  methods: {
    // post List를 요청하고 저장하는 기능
    async requestPostList() {
      this.posts = await PostService.getPostList();
    },
  },

}
</script>


<style>

</style>
