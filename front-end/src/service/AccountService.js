/**
 * 서버에 계정 관련 요청을 담당하는 service
 */

import axios from 'axios'

//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "https://70.12.246.37:9090"

export default {
  async signUpSubmit(signupForm) {
    const response = {};

    await axios.post(BASE_URL + '/member', signupForm)
    .then(response => {
      this.result = response.data;
      if(this.result.success==='false'){
        alert("이미 등록되어있는 이메일 입니다.")
      } else {
        alert("정상적으로 회원가입 되었습니다.")
        window.location.href='/'
      }
    })

    return response
  },

  async loginSubmit(loginForm) {
    let result = {};
    await axios.post(BASE_URL + '/login', loginForm)
    .then(response => {
      if (response.data.success == "true") {
        result = {
          isLoggedIn: true,
          id: Number(response.data.mid),
          jwt: response.data.jwt,
        }
      }
    })

    return result
  },

}
