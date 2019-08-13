<template>
  <div>
    <v-layout row justify-center>
      <v-dialog v-model="dialog" persistent max-width="600px">

        <template v-slot:activator="{ on }">
          <div v-on="on" :class="{ 'red-color': loginPicked }" class="small-menu-title mr-5">
            <div @mouseleave="loginUnPick" @mouseover="loginPick">
              Login
            </div>
          </div>
        </template>

        <v-card @keyup.esc="dialog=false" @keyup.enter="loginSubmit">
          <v-card-title>
            <span class="headline">Login</span>
          </v-card-title>

          <!-- input window -->
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>

                <v-flex xs12>
                  <v-text-field
                    color="#E53935" :rules="emailRules"
                    label="Email" type="email"
                    v-if="dialog" autofocus
                    required v-model="uid">
                  </v-text-field>
                </v-flex>

                <v-flex xs12>
                  <v-text-field
                    color="#E53935" :rules="passwordRules"
                    label="Password" type="password"
                    required v-model="password">
                  </v-text-field>
                </v-flex>

              </v-layout>
            </v-container>
          </v-card-text>

          <!-- sns login components -->
          <div class="text-xs-center">
            <KakaoLogin/>
            <FacebookLogin/>
          </div>

          <v-card-actions>
            <v-spacer></v-spacer>

            <!-- save button -->
            <v-btn
              :class="{'red-color': savePicked}" color="#1a1c33"
              depressed flat outline @click="loginSubmit">
              <div @mouseleave="savePick" @mouseover="savePick">
                save
              </div>
            </v-btn>

            <!-- cancle button -->
            <v-btn
              :class="{'red-color': canclePicked}" color="#1a1c33"
              depressed flat outline
              @click="dialog=false">
              <div @mouseleave="canclePick" @mouseover="canclePick">
                cancel
              </div>
            </v-btn>
          </v-card-actions>

        </v-card>
      </v-dialog>
    </v-layout>

  </div>
</template>


<script>
/**
 * 로그인 화면을 modal창으로 띄우는 component
 */
import AccountService from '../../service/AccountService'
import KakaoLogin from './KakaoLogin'
import FacebookLogin from './FacebookLogin'
import cookieService from '../../service/CookieService'

export default {
  name: 'LoginModal',

  components: {
    KakaoLogin,
    FacebookLogin,
  },

  data() {
    return {
      uid: '',
      password: '',
      dialog: false,
      loginPicked: false,
      savePicked: false,
      canclePicked: false,

      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid'
      ],
      passwordRules: [
        v => !!v || 'password is required',
      ],
    }
  },

  methods:{
    loginSubmit(){
      const loginForm = {
        email: this.uid,
        pw: this.password,
      }

      const response = AccountService.loginSubmit(loginForm);
      response.then(result => {
        if (result.isLoggedIn == true) {
          this.$store.state.isLoggedIn = true;
          this.$store.state.mid = result.id;
          sessionStorage.setItem("mid", result.id);
          sessionStorage.setItem("jwt", result.jwt);
          window.location.href='/'
        } else {
          alert(`등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못입력하셨습니다.`)
        }
      })
    },

    loginPick() {
      document.body.style.cursor = 'pointer';
      this.loginPicked = !this.loginPicked
    },

    loginUnPick() {
      document.body.style.cursor = 'initial';
      this.loginPicked = !this.loginPicked
    },

    savePick() { this.savePicked = !this.savePicked },
    canclePick() { this.canclePicked = !this.canclePicked },

  },
}
</script>
