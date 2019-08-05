<template>
  <v-card>
    <!-- thumbnail image -->
    <v-img :src="imgSrc" height="200px"></v-img>
    
    <!-- text content -->
    <v-card-title primary-title>
      <v-flex>
        <p id="title" class="headline" >{{title}}</p>
        <p id="body" class="grey--text">{{body}}</p>
      </v-flex>
    </v-card-title>
  </v-card>
</template>


<script>
/**
 * PortfoliosList.vue에서 받은 portfolio를 card로 출력하는 component
 */
export default {
  name: 'Portfolio',

	props: {
		date: {type: String},
		title: {type: String},
		body: {type: String},
		imgSrc: {type: String},
  },

	data() {
		return {
			//
		}
	}
}
</script>


<style>
#title {
   overflow: hidden;
   text-overflow: ellipsis;
   display: -webkit-box;
   line-height: 16px;
   max-height: 32px;
   -webkit-line-clamp: 1;
   -webkit-box-orient: vertical;
}
a{
  text-decoration: none;
}
#body {
   overflow: hidden;
   text-overflow: ellipsis;
   display: -webkit-box;
   line-height: 1.2em;
   max-height: 3.6em;
   word-wrap: break-word;
   -webkit-line-clamp: 3;
   -webkit-box-orient: vertical;
}
</style>
