<template>
	<div>
		<input class="filebox" id="file" ref="imageFile" v-on:change="postImageSource()" type="file">
	</div>
</template>


<script>
/**
 * Imgur에 axios로 이미지를 업로드 하고, 화면에 띄우는 기능
 */
import ImageService from '../../service/ImageService'

export default {
	name: 'Imgur',
	data() {
		return {
			imageSource: 'http://dy.gnch.or.kr/img/no-image.jpg'
		}
	},
  
	methods: {
    // imgur에 이미지를 업로드 하고 저장
		async postImageSource() {
			let imageData = new FormData();
      this.imageSource='';
      
      imageData.append('image', this.$refs.imageFile.files[0])

      let response = await ImageService.postImage(imageData)
      
      // TODO: 요청 성공 실패 나누기
      this.imageSource = response.data.data.link
      this.$emit("passUploadImage", this.imageSource)
		},
  },
  
}
</script>


<style>
input[type="file"] {
  background-color: #FAFAFA!important;
}
</style>
