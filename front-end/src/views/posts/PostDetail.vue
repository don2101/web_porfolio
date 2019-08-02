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
      flat outline @click="editPost">
      <div @mouseover="editPick" @mouseleave="editPick">
        edit
      </div>
    </v-btn>

    <!-- delete button -->
    <v-btn
      :class="{'red-color': this.deletePicked}" color="#FAFAFA"
      flat outline @click="deletePost">
      <div @mouseover="deletePick" @mouseleave="deletePick">
        delete
      </div>
    </v-btn>
    <PortfolioCommentWrite
      :postId="idx"
      :isPortfolio="isPortfolio">
    </PortfolioCommentWrite>
    <PortfolioCommentsList
      :postId="idx"
      :isPortfolio="isPortfolio">
    </PortfolioCommentsList>
  </div>
</template>


<script>
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
import PostService from '../../service/PostService'
import PortfolioCommentWrite from '../comments/PortfolioCommentWrite'
import PortfolioCommentsList from '../comments/PortfolioCommentsList'

export default {
  name: "PostDetail",

  components: {
    MarkdownItVue,
    PostService,
    PortfolioCommentWrite,
    PortfolioCommentsList,
  },

  data() {
    return {
      title: '',
      date: '',
      writer: '',
      content: '',
      editPicked: false,
      deletePicked: false,
      isPortfolio: this.idx == "" ? true : false,
    }
  },

  created() {
    this.requestPost();
  },

  methods: {
    editPick() { this.editPicked = !this.editPicked },

    deletePick() { this.deletePicked = !this.deletePicked },

    async requestPost() {
      const result = await PostService.getPost(this.idx);
      this.title = result.title;
      this.date = result.date;
      this.writer = result.mid;
      this.content = result.content;
    },

    async deletePost() {
      const result = await PostService.deletePost(this.idx);

      if(result.success == "true") {
        alert("삭제되었습니다.")
        window.location.href = "/posts"
      } else {
        alert("삭제할 수 없습니다.")
      }
    },

    editPost() {
      this.$router.push({ name: 'postUpdate', query: { 'idx': this.idx} })
    },
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
