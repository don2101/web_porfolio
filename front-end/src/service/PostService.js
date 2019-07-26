import axios from 'axios'

const BASE_URL = "http://localhost:9090/post"

export default {
  async getPostList() {
    const response = await axios.get(BASE_URL);

    return response.data
  },

  async postPost(postBody) {
    const response = await axios.post(BASE_URL, postBody);
    
    if(response.data.success == "true") { return true }
    else { return false }
  },

  async getPost(postId) {
    const response = await axios.get(BASE_URL + "/" + postId);
    
    return response.data
  },

  async putPost(postId, postBody) {
    const response = await axios.put(BASE_URL + "/" + postId, postBody);

    return response.data
  },

  async deletePost(postId) {
    const response = await axios.delete(BASE_URL + "/" + postId);
    
    return response.data
  }

}