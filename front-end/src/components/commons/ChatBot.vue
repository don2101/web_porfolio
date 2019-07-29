<template>
  <div id="frogue-container"
        data-chatbot="dd2c2454-826f-4e1b-9b2c-e7493d51d17d"
        data-user="사용자ID"
        data-init-key="value"
        ></div>
</template>
<script>
export default {
    name: "chatbot",
    methods: {
      danbeeAi: (function(d, s, id){
          console.log("here");
          var js, fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) {return;}
          js = d.createElement(s); js.id = id;
          js.src = "https:\/\/danbee.ai/js/plugins/frogue-embed/frogue-embed.min.js";
          fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'frogue-embed'))
    },
    mounted() {
      this.danbeeAi()
    }
  }
</script>
<style>
#frogue-container {
  margin-bottom: auto;
}
</style>
