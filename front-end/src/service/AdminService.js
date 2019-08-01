/**
 * 서버로 부터 Portfolio를 가져오는 service
 */

import axios from 'axios'

const BASE_URL = "http://localhost:9090"

export default {
  async updateMemberList(postData) {
    let response = [];

    response = await axios.put(BASE_URL + '/member', postData)
    .then(response=>{
      if(response.data.success==='true'){
        alert("정상적으로 수정 되었습니다.")
        window.location.href='/admin'
      }else{
        alert("에러 발생")
      }
    })

    return response
  },

  async getGrade(mid){
    let response=[];
    response=await axios.get(BASE_URL+'/member/'+mid)

    // console.log(response)
    return response.data.grade
  },

  async deleteMemberList(mid){
    let response=[];
    response=await axios.delete(BASE_URL+'/member/'+mid)

    return response
  }
}
