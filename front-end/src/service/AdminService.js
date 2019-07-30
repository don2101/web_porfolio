/**
 * 서버로 부터 Portfolio를 가져오는 service
 */

import axios from 'axios'

const BASE_URL = "http://localhost:9090"

export default {
  async updateMemberList(postData) {
    let response = [];

    response = await axios.put(BASE_URL + '/updatetest', postData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 등록 되었습니다.")
        window.location.href='/portfolios'
      }else{
        alert("에러 발생")
      }
    })

    return response
  }
}
