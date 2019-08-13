import axios from 'axios'
import TokenService from './TokenService'
//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "https://70.12.246.56:9090"

export default {
  async getPortfolioComments(pfId) {
    let response = [];
    response = await axios.get(BASE_URL + '/portfolio/comments/' + pfId)
    .then(response=>{
      return response.data
    })

    return response
  },

  async postPortfolioComment(pfComData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.post(BASE_URL + '/portfolio/comments', pfComData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href = '/portfolios/detail?pfId=' + pfComData.pfId
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async deletePortfolioComment(jsonData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.delete(BASE_URL + '/portfolio/comments/' + jsonData.pfcomId)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 삭제 되었습니다.")
        window.location.href='/portfolios/detail?pfId=' + jsonData.pfId
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async updatePortfolioComment(pfcomData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.put(BASE_URL + '/portfolio/comments/' + pfcomData.pfcomId, pfcomData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href = "/portfolios/detail?pfId=" + pfcomData.pfId;
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async getPostComments(postId) {
    let response = [];
    response = await axios.get(BASE_URL + '/post/comments/' + postId)
    .then(response=>{
      return response.data
    })
    return response
  },

  async postPostComment(jsonData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.post(BASE_URL + '/post/comments', jsonData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href = '/posts/detail?pid=' + jsonData.postId
      } else {
        alert("에러 발생")
      }
    })
    return response
  },

  async deletePostComment(jsonData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.delete(BASE_URL + '/post/comments/' + jsonData.postcomId)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 삭제 되었습니다.")
        window.location.href='/posts/detail?pid=' + jsonData.postId
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async updatePostComment(jsonData) {
    TokenService.checkToken();
    let response = [];
    response = await axios.put(BASE_URL + '/post/comments/', jsonData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href = "/posts/detail?pid=" + jsonData.postId
      }else{
        alert("에러 발생")
      }
    })
    return response
  },
}
