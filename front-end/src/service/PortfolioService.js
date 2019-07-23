/**
 * 서버로 부터 Portfolio를 가져오는 service
 */

import axios from 'axios'

const BASE_URL = "http://localhost:9090"

export default {
  async postPortfolio(postData) {
    let response = [];
    response = await axios.post(BASE_URL + '/portfolio', postData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href='/portfolios'
      }else{
        alert("에러 발생")
      }
    })
    return response
  },

  async getPortfolios() {
    let response = [];
    response =  await axios.get(BASE_URL + '/portfolio')
    .then(response=>{
      return response.data
    });

    return response
  }

}
