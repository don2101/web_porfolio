/**
 * Imgur에 API요청을 담당하는 service
 */

import axios from 'axios'

const BASE_URL = "https://api.imgur.com/3/image/"

export default {
  async postImage(imageData) {
    let response = await axios({
      method: 'post',
      url: BASE_URL,
      data: imageData,
      headers: { Authorization: 'Client-ID 17491fbdf303ae1'},
      mimeType: 'multipart/form-data'
    })

    return response
  }
  
}