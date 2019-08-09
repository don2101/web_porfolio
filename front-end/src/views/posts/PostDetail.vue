<template>
  <div>
    <!-- title area -->
    <h1 class="main-title detail-title">{{ title }}</h1>
    <hr/>

    <!-- content view -->
    <v-flex class="markdown-view post-text" xs6>
      <MarkdownItVue class="md-body" :content="content"/>
    </v-flex>

    <hr class="mb-3"/>

    <v-layout justify-end>
      <!-- edit button -->
      <v-btn
        :class="{'red-color': this.editPicked}" color="#FAFAFA"
        flat outline @click="editPost" v-if="isWriter">
        <div @mouseover="editPick" @mouseleave="editPick">
          update
        </div>
      </v-btn>

      <!-- delete button -->
      <v-btn
        :class="{'red-color': this.deletePicked}" color="#FAFAFA"
        flat outline @click="deletePost" v-if="isWriter">
        <div @mouseover="deletePick" @mouseleave="deletePick">
          delete
        </div>
      </v-btn>

      <!-- list button -->
      <v-btn
        :class="{'red-color': this.listPicked}" color="#FAFAFA"
        flat outline to="/posts">
        <div @mouseover="listPick" @mouseleave="listPick">
          list
        </div>
      </v-btn>
    </v-layout>

    <!-- Comment Group -->
    <div class="mt-5">
      <PortfolioCommentWrite
        :postId="pid"
        :isPortfolio="isPortfolio">
      </PortfolioCommentWrite>
      <div class="mt-5">
        <PortfolioCommentsList
          :postId="pid"
          :isPortfolio="isPortfolio">
        </PortfolioCommentsList>
      </div>
    </div>
    
  </div>
</template>


<script>
/**
 * Post의 상세 내역을 출력
 * Edit페이지로 이동하고, post를 삭제하는 버튼 추가
 */
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
import PostService from '../../service/PostService'
import PortfolioCommentWrite from '../comments/PortfolioCommentWrite'
import PortfolioCommentsList from '../comments/PortfolioCommentsList'

export default {
  name: "PostDetail",

  components: {
    MarkdownItVue,
    PortfolioCommentWrite,
    PortfolioCommentsList,
  },

  data() {
    return {
      title: '',
      date: '',
      writer: '',
      content: '',
      post:[],
      editPicked: false,
      deletePicked: false,
      listPicked: false,
      isPortfolio: this.pid == "" ? true : false,
    }
  },

  created() {
    this.requestPost();
  },

  methods: {
    editPick() { this.editPicked = !this.editPicked },
    deletePick() { this.deletePicked = !this.deletePicked },
    listPick() { this.listPicked = !this.listPicked },

    // 상세 Post를 가져온다.
    async requestPost() {
      this.post = await PostService.getPost(this.pid);
      this.title = this.post.title;
      this.date = this.post.date;
      this.writer = this.post.mid;
      this.content = this.post.content;
    },

    // DELETE post
    async deletePost() {
      const result = await PostService.deletePost(this.pid);

      if(result.success == "true") {
        alert("삭제되었습니다.")
        window.location.href = "/posts"
      } else {
        alert("삭제할 수 없습니다.")
      }
    },

    // PUT post
    editPost() {
      this.$router.push({ name: 'postUpdate', query: { 'pid': this.pid} })
    },
  },

  computed: {
    pid() {
      return this.$route.query.pid
    },

    isWriter(){
      return this.post.mid === sessionStorage.getItem("mid")
    },
  },
}
</script>


<style>
@import '~simplemde/dist/simplemde.min.css';

.md-body {
  color: #FAFAFA;
}
</style>
