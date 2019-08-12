/**
 * 서버로 부터 Portfolio를 가져오는 service
 */

import axios from 'axios'
//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "http://localhost:9090"

export default {
  async getMemberList(){
    let response=[];
    response = await axios.get(BASE_URL+"/member")

    return response.data;
  },

  async updateMemberList(postData) {
    let response = [];

    response = await axios.put(BASE_URL + '/member', postData)

    if(response.data.success==='true'){
      alert("정상적으로 수정 되었습니다.")
      window.location.href='/admin'
    } else {
      alert("에러 발생")
    }

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
