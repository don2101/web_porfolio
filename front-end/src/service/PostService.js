import axios from 'axios'
import TokenService from './TokenService'
//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "https://70.12.246.37:9090/post"

export default {
  // GET Posts
  async getPostList() {
    try {
      const response = await axios.get(BASE_URL);

      return response.data
    } catch(err) {
      alert(err)
    }

  },

  // POST post
  async postPost(postBody) {
    try {
      const response = await axios.post(BASE_URL, postBody);

      if(response.data.success == "true") {
        TokenService.notificationAll('new post!!')
        return true
      }
      else {
        return false
      }
    } catch(err) {
      alert(err)
    }
  },

  // GET post
  async getPost(postId) {
    try {
      const response = await axios.get(BASE_URL + "/" + postId);

      return response.data
    } catch(err) {

    }
  },

  // PUT post
  async putPost(postId, postBody) {
    try {
      const response = await axios.put(BASE_URL + "/" + postId, postBody);

      return response.data
    } catch(err) {
      alert(err)
    }

  },

  // DELETE post
  async deletePost(postId) {
    try {
      const response = await axios.delete(BASE_URL + "/" + postId);

      return response.data
    } catch(err) {
      alert(err)
    }
  }

}
