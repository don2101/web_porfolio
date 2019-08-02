/**
 * 서버로 부터 Portfolio를 가져오는 service
 */

import axios from 'axios'

const BASE_URL = "http://localhost:9090"

export default {
  // POST portfolio
  async postPortfolio(postData) {
    let response = {}
    
    response = await axios.post(BASE_URL + '/portfolio', postData)
    
    if(response.data.success==='true'){
      alert("정상적으로 등록 되었습니다.")
      window.location.href='/portfolios'
    } else {
      alert("에러 발생")
    }
  },

  // GET portfolios
  async getPortfolios() {
    let response = [];
    response =  await axios.get(BASE_URL + '/portfolio')
    
    return response.data
  },

  // GET portfolio
  async getPortfolio(pfId){
    let response = [];

    response = await axios.get(BASE_URL + '/portfolio/'+pfId)

    return response.data;
  },

  // PUT portfolio
  async updatePortfolio(pfId,postData) {
    let response = {}
    
    response = await axios.put(BASE_URL + '/portfolio/'+pfId, postData)
    
    if(response.data.success==='true'){
      alert("정상적으로 수정 되었습니다.")
      window.location.href='/portfolios'
    }else{
      alert("에러 발생")
    }
  },

  // DELETE portfolio
  async deletePortfolio(pfId) {
    let response = {}

    response = await axios.delete(BASE_URL + '/portfolio/'+pfId)
    
    if(response.data.success==='true'){
      alert("정상적으로 삭제 되었습니다.")
      window.location.href='/portfolios'
    } else {
      alert("에러 발생")
    }
  },

}
