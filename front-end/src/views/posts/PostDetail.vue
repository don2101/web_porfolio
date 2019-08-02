<template>
  <div>
    <!-- title view -->
    <v-flex xs12>
      <v-text-field 
      :value="title"
      color="#FAFAFA"
      dark outline single-line readonly
      height="50" style="font-size: 30px"></v-text-field>
    </v-flex>

    <!-- content view -->
    <v-flex class="markdown-view" xs6>
      <MarkdownItVue class="md-body" :content="content"/>
    </v-flex>

    <!-- edit button -->
    <v-btn 
      :class="{'red-color': this.editPicked}" color="#FAFAFA"
      flat outline @click="editPost" v-if="isWriter">
      <div @mouseover="editPick" @mouseleave="editPick">
        edit
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

export default {
  name: "PostDetail",

  components: {
    MarkdownItVue,
    PostService,
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
    }
  },

  created() {
    this.requestPost();
  },

  methods: {
    editPick() { this.editPicked = !this.editPicked },

    deletePick() { this.deletePicked = !this.deletePicked },

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
