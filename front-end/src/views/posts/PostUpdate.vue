<template>
  <div>

    <v-layout class="write-title-input">
      <!-- title -->
      <v-flex xs12>
        <v-text-field 
        v-model="title" 
        dark
        color="#FAFAFA"
        outline single-line 
        height="50" style="font-size: 30px"></v-text-field>
      </v-flex>
    </v-layout>
  
    <v-layout class="my-3">
      <!-- markdown view -->
      <v-flex class="markdown-view" xs6>
        <MarkdownItVue class="md-body" :content="content"/>
      </v-flex>
      
      <!-- markdown writer -->
      <v-flex class="ml-3" xs6>
        <vue-simplemde v-model="content" ref="markdownEditor" />
      </v-flex>
    </v-layout>

    <v-layout justify-end>
      <!-- submit button -->
      <v-btn 
        :class="{'red-color': this.cancelPicked}" color="#FAFAFA"
        flat outline @click="cancelUpdate">
        <div @mouseover="cancelPick" @mouseleave="cancelPick">
          cancel
        </div>
      </v-btn>

      <!-- submit button -->
      <v-btn 
        :class="{'red-color': this.updatePicked}" color="#FAFAFA"
        flat outline @click="updatePost">
        <div @mouseover="updatePick" @mouseleave="updatePick">
          update
        </div>
      </v-btn>
    </v-layout>

  </div>
</template>


<script>
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
import VueSimplemde from 'vue-simplemde'
import PostService from '../../service/PostService'

export default {
  name: "PostUpdate",

  components: {
    MarkdownItVue,
    VueSimplemde,
  },

  data() {
   return {
      title: '',
      content: '',
      post: [],
      cancelPicked: false,
      updatePicked: false,
    }
  },

  async created() {
    await this.requestPost();
    
    if(this.post.mid !== sessionStorage.getItem("mid")){
      alert("권한이 없습니다.")
      window.location.href="/posts"
    }
  },

  methods: {
    cancelPick() { this.cancelPicked = !this.cancelPicked },

    updatePick() { this.updatePicked = !this.updatePicked },

    // GET post
    async requestPost() {
      this.post = await PostService.getPost(this.pid);
      this.title = this.post.title;
      this.content = this.post.content;
    },

    // PUT post
    async updatePost() {
      const postBody = {
        mid: sessionStorage.getItem("mid"),
        title: this.title,
        content: this.content,
      }
      
      const result = await PostService.putPost(this.pid, postBody);

      if(result.success == "true") {
        alert("변경되었습니다.");
        this.$router.push({ name: 'postDetail', query: { 'pid': this.pid} })
      } else {
        alert("변경이 거부되었습니다.")
      }
    },

    // 변경 취소
    cancelUpdate() {
      this.$router.push({ name: 'postDetail', query: { 'pid': this.pid} })
    } 

  },

  computed: {
    pid() {
      return this.$route.query.pid
    }
  },
}
</script>


<style>
@import '~simplemde/dist/simplemde.min.css';

.md-body {
  color: #FAFAFA;
}
</style>
