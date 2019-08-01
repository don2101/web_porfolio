<template>
  <div>
    <div class="my-5">
      <v-layout>
        <!-- title -->
        <v-flex xs12>
          <v-text-field 
          v-model="title" 
          color="#FAFAFA"
          outline single-line dark
          height="50" style="font-size: 30px"></v-text-field>
        </v-flex>

      </v-layout>
    
      <v-layout class="my-5">
        <!-- markdown view -->
        <v-flex class="markdown-view" xs6>
          <MarkdownItVue class="md-body" :content="content"/>
        </v-flex>

        <v-flex class="ml-3" xs6>
          <!-- markdown writer -->
          <vue-simplemde v-model="content" ref="markdownEditor" />

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

        </v-flex>
      </v-layout>
    </div>
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
    PostService,
  },

  data() {
    return {
      title: '',
      content: '',
      cancelPicked: false,
      updatePicked: false,
    }
  },

  created() {
    this.requestPost();
  },

  methods: {
    cancelPick() { this.cancelPicked = !this.cancelPicked },

    updatePick() { this.updatePicked = !this.updatePicked },

    // 변경할 Post를 요청
    async requestPost() {
      const result = await PostService.getPost(this.idx);
      this.title = result.title;
      this.content = result.content;
    },

    // Post를 수정
    async updatePost() {
      const postBody = {
        mid: sessionStorage.getItem("mid"),
        title: this.title,
        content: this.content,
      }
      
      const result = await PostService.putPost(this.idx, postBody);

      if(result.success == "true") {
        alert("변경되었습니다.");
        this.$router.push({ name: 'postDetail', query: { 'idx': this.idx} })
      } else {
        alert("변경이 거부되었습니다.")
      }
    },

    // 변경 취소
    cancelUpdate() {
      this.$router.push({ name: 'postDetail', query: { 'idx': this.idx} })
    } 

  },

  computed: {
    idx() {
      return this.$route.query.idx
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
