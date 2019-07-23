/**
 * 서버에 계정 관련 요청을 담당하는 service
 */

import axios from 'axios'

const BASE_URL = "http://localhost:9090"

export default {
  async signUpSubmit(signupForm) {
    const response = await axios.post(BASE_URL + '/member', signupForm)
    .then(response => {
      this.result = response.data;
      if(this.result.success==='true'){
        alert("정상적으로 회원가입 되었습니다.")
        window.location.href='/'
      } else {
        alert("이미 등록되어있는 이메일 입니다.")
      }
    })

    return response
  },

  async loginSubmit(loginForm) {
    var result = {};
    const response = await axios.post(BASE_URL + '/login', loginForm)
    .then(response => {
      if (response.data.success == "true") {
        result = {
          isLoggedIn: true,
          id: Number(response.data.mid),
          loc: Number(response.data.loc),
        }
      }
    })

    return result
  },
}
