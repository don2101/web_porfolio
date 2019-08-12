import axios from 'axios'
//back 서버를 켠 노트북의 ip로 넣을것
const BASE_URL = "http://localhost:9090"

export default {
  async insertToken(tokenForm){
    axios.post(BASE_URL + '/token', tokenForm)
    .then(response=>{
      if(response.data.success==="false"){
        console.log("insertToken error")
      }
    })
  },

  async deleteToken(mid){
    axios.delete(BASE_URL + '/token/'+mid)
    .then(response=>{
      if(response.data.success!="true"){
        console.log("deleteToken error")
      }
    })
  },

  async notificationAll(what){
    axios.get(BASE_URL+'/token')
    .then(response=>{
      let config={
        'headers':{
          'Authorization': "key=AAAAzVOuSyc:APA91bHNGjZaC4bNmLvz5ZNK6QRK5S8zR0ZNp6FtQlGcm3k0HEibM_xq3xdjlJ8T8b-gBkFO20Yct9D0RxIXezP_b5hdFPGyFumpV-fbDDpv_WdCFQB5a6pbDfMkvgQaDUMR5c5OmeQ4",
          'Content-Type':"application/json"
        }
      }

      let content={
        'registration_ids' : response.data,
        'priority' : "high",
        'notification' : {
          'body' : what,
          'title' : "Title"
        },

      }
      axios.post("https://fcm.googleapis.com/fcm/send",content,config)
    })
  },

  async checkToken(){
    let result = {};
    const jwtForm = {
      jwt: window.sessionStorage.getItem("jwt")
    }
    console.log("jwt: " + window.sessionStorage.getItem("jwt"))
    axios.post(BASE_URL + '/jwt/auth', jwtForm)
    .then(response=>{
      if(response.data.success==="true") {
        alert("valid Token")
        result = {
          mid: Number(response.data.mid),
          name: response.data.name,
        }
        console.log("mid: " + result.mid)
      } else if (response.data.success==="invalidToken") {
        alert("invalid Token")
        window.sessionStorage.removeItem("jwt")
        window.location.href='/'
      }
    })
    return result
  },

}
