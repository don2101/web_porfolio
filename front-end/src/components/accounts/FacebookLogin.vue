<template>
  <div>
  <button v-on:click="fbLogin">
     <img  src="../../assets/facebookLogin.png" width="300"/>
  </button>
  </div>
</template>

<script type='text/javascript'>

export default {
  name : 'FacebookLogin',
  data : function() {
    return {

    }
  },
  mounted(){
    window.fbAsyncInit = function() {
    console.log("시작");
    FB.init({
      appId      : '1219560174891045',
      cookie     : true,  // enable cookies to allow the server to access
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v3.3' // The Graph API version to use for the call
    });


    FB.getLoginStatus(function(response) {
      // console.log("getLoginStatus "+ response);
      statusChangeCallback(response);
    });

    };

    // Load the SDK asynchronously
    (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
  },
  /* eslint-disable */
  methods : {
    // checkLoginState : function(){
    //   FB.getLoginStatus(function(response){
    //     statusChangeCallback(response);
    //   });
    // },//end of checkLoginState
    // statusChangeCallback : function(response){
    //   console.log('statusChangeCallback');
    //   console.log(response);
    //   if(response.status === 'connected'){
    //     testAPI();
    //   }else{
    //     console.log("연결되지않았습니다.");
    //   }
    // },
    fbLogin : function(){
      var vm = this;
      console.log("fbLogin");
      FB.login(function(response){
        if(response.status ==='connected'){
          // console.log(response.authResponse.accessToken);
          // console.log('로그인 성공');
          // console.log(this);
          // console.log('Welcome ! Fecting your imformation');
            vm.$store.state.isLoggedIn = true;
            sessionStorage.setItem('isLoggedIn', response.id);
            console.log(sessionStorage.getItem('isLoggedIn'))
            
            FB.api('/me',
            {"fields" : "id,name"},
            function(response){
              console.log('Success login for : '+response.name);
              console.log(response.id);
              console.log(sessionStorage.getItem("sessionId"));
              axios.post('http://localhost:9090/member',{
                  'email' : response.id,
                  'name' : response.name,
                  'location' : '2',
              })
              .then(response=>{
                console.log(response.data);
              })
              .catch(error=>{
                console.log("에러발생");
              })

          });


        }else{
          console.log("실패");
        }

      }),{scope: 'public_profile,name'};
    },

  }//end of methods

}//end
</script>
