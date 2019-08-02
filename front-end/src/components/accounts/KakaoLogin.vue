<template>
  <div>
    <button v-on:click="loginWithKakao">
       <img v-if="kakaoImgChange" src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="300"/>
    </button>
    <!-- <button v-on:click="logoutWithKakao">
       <img v-if="!kakaoImgChange" src="../assets/logoutBtn.png" width="300"/>
    </button> -->

  </div>
</template>

<script type='text/javascript'>
  //<![CDATA[
  /* eslint-disable */
  // 사용할 앱의 JavaScript 키를 설정해 주세요.

  export default {
    name : 'KakaoLogin',
    
    data :function() {
      return{
        kakaoImgChange : true,
      }
    },

    mounted(){
        Kakao.init('03de759afadd1336049ea03e80654f63');
    },

    methods : {
      loginWithKakao : function(){
        console.log("로그인")
        let vm = this;
        Kakao.Auth.login({
          success : function(authObj){
            // alert(JSON.stringify(authObj));
            
            console.log("login success");
            Kakao.API.request({
              url: '/v1/user/me',
              success : function(res){
                console.log(vm);
                vm.$store.state.isLoggedIn = true;
                
                console.log("API success");
                console.log(res);

                var userId = res.id;
                sessionStorage.setItem('isLoggedIn', userId);
                var userEmail = res.kaccount_email;
                var userNickName = res.properties.nickname;
                var location = 1;
                sessionStorage.setItem("sessionId", userId);
                console.log(sessionStorage.getItem("sessionId"));
                console.log(userId);
                // console.log("카카오이미지 변환")
                // console.log(typeof(this.kakaoImgChange));

                this.kakaoImgChange = false;
                axios.post('http://localhost:9090/member',{
                  'email' : userId,
                  'name' : userNickName,
                  'location' : '2',
                })
                .then(response=>{
                  console.log(response.data);


                })
                .catch(error=>{
                  console.log("에러발생");
                })

              },
              fail: function(error){
                // alert(JSON.stringfy(error));
              }
            })
        },
        fail : function(err) {
          // alert(JSON.stringfy(err));
        }
      })

    }, 
    
    //end of loginWithKakao
    // logoutWithKakao : function(){
    //   console.log("logoutWithKakao function");
    //     Kakao.Auth.logout({
    //       success : function(){
    //         this.kakaoImgChange = true;
    //     }
    //   })//end of logout
    // }//end of loginWithKakao

      }
  }


</script>
