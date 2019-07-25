import axios from 'axios'

const BASE_URL = "http://localhost:9090/post"

export default {
  async getPostList() {
    const resposne = await axios.get(BASE_URL);

  },

  async postPost(postBody) {
    const response = await axios.post(BASE_URL, postBody);
    
    if(response.data.success == "true") { return true; }
    else { return false; }
  },

  async getPost(postId) {
    const response = await axios.get(BASE_URL + "/" + postId);

  },

  async putPost(postId, postBody) {
    const response = await axios.put(BASE_URL + "/" + postId, postBody);

  },

  async deletePost(postId) {
    const response = await axios.delete(BASE_URL + "/" + postId);
  }

}