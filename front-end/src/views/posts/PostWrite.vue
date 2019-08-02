<template>
  <div>
    <div class="my-5">
      <v-layout>
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
            :class="{'red-color': this.buttonPicked}" color="#FAFAFA"
            flat outline @click="writePost">
            <div @mouseover="buttonPick" @mouseleave="buttonPick">
              write
            </div>
          </v-btn>

        </v-flex>
      </v-layout>
    </div>
  </div>
</template>


<script>
/**
 * markdown을 사용하여 post를 작성하는 component
 */
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
import VueSimplemde from 'vue-simplemde'
import PostService from '../../service/PostService'
import AdminService from '../../service/AdminService'

export default {
  name: 'PostWrite',

  components: {
    MarkdownItVue,
    VueSimplemde
  },

  data () {
    return {
      content: '',
      title: '',
      buttonPicked: false,
    }
  },

  async created() {
    let grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
    
    if(grade !== '0' && grade !== '1'){
      alert("권한이 없습니다.")
      window.location.href="/posts"
    }
  },

  methods: {
    buttonPick() { this.buttonPicked = !this.buttonPicked },
    
    // POST post
    writePost() {
      const postBody = {
        mid: sessionStorage.getItem("mid"),
        title: this.title,
        content: this.content
      }
      
      const result = PostService.postPost(postBody);
      if(result) {
        alert("작성되었습니다.");
        window.location.href='/posts';
      } else {
        alert("작성이 거부되었습니다.")
      }
    }
  }

}
</script>


<style>
@import '~simplemde/dist/simplemde.min.css';

.md-body {
  color: #FAFAFA;
}
</style>
