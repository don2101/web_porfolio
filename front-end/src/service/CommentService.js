import axios from 'axios'

const BASE_URL = "http://localhost:9090"

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
    let response = [];
    response = await axios.post(BASE_URL + '/portfolio/comments', pfComData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href = '/portfolios/detail?idx=' + pfComData.pfId  // ?idx=
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async deletePortfolioComment(pfcomId) {
    let response = [];
    response = await axios.delete(BASE_URL + '/portfolio/comments/' + pfcomId)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 삭제 되었습니다.")
        window.location.href='/portfolios/'
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async updatePortfolioComment(pfcomData) {
    let response = [];
    response = await axios.put(BASE_URL + '/portfolio/comments/' + pfcomData.pfcomId, pfcomData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async getPostComments() {
    let response = [];
    response = await axios.get(BASE_URL + '/post/comments')
    .then(response=>{
      return response.data
    })

    return response
  },
}
