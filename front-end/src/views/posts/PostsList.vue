<template>
  <div class="mt-5">

    <v-layout class="post-header">
      <v-flex xs2>
        <p>작성자</p>
      </v-flex>
      <v-flex xs8>
        <p>제목</p>
      </v-flex>
      <v-flex xs2>
        <p>작성일</p>
      </v-flex>
    </v-layout>

    <hr class="post-header-hr"/>

    <div v-for="i in posts.length" :key="posts[i-1].postId">
      <router-link :to="{ name: 'postDetail', query: { 'pid': posts[i-1].postId } }">
          <Post
            :title="posts[i-1].title"
            :date="posts[i-1].date"
            :writer="posts[i-1].mid">
          </Post>
      </router-link>
    </div>

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

      headers: [
        {
          align: 'left',
          sortable: false,
          value: 'name'
        },
        { text: 'Calories', },
        { text: 'Fat (g)', },
        { text: 'Carbs (g)', },
        { text: 'Protein (g)', },
        { text: 'Iron (%)', }
      ],
      desserts: [
        {
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          iron: '1%'
        },
        {
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          iron: '1%'
        },
        {
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          iron: '7%'
        },
        {
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          iron: '8%'
        },
        {
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          iron: '16%'
        },
        {
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          iron: '0%'
        },
        {
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          iron: '2%'
        },
        {
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          iron: '45%'
        },
        {
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          iron: '22%'
        },
        {
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          iron: '6%'
        }
      ]
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
