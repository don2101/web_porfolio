<template>
  <div>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">

      <template v-slot:activator="{ on }">
        <div v-on="on" :class="{ 'red-color': loginPicked }" class="small-menu-title mr-5">
          <div @mouseleave="loginUnPick" @mouseover="loginPick">
            Signup
          </div>
        </div>
      </template>

      <v-card>
        <v-card-title>
          <span class="headline">Signup</span>
        </v-card-title>

        <!-- input window -->
        <form @submit.prevent="loginSubmit">
        <v-card-text>
          <v-container grid-list-md>
            <v-layout wrap>
              <v-flex xs12>
                <v-text-field color="#E53935"
                  :rules="nameRules" label="Name" name="name" type="text"
                  required v-model="name">
                </v-text-field>
              </v-flex>

              <v-flex xs12>
                <v-text-field color="#E53935"
                  :rules="emailRules" label="Email" name="email" type="email"
                  required v-model="email">
                </v-text-field>
              </v-flex>

              <v-flex xs12>
                <v-text-field color="#E53935"
                  :rules="passwordRules" label="Password" name="password" type="password"
                  required v-model="password">
                </v-text-field>
              </v-flex>

              <v-flex xs12>
                <v-text-field color="#E53935"
                  :rules="passwordCheckRules" label="PasswordCheck" name="passwordCheck" type="password"
                  required v-model="passwordCheck">
                </v-text-field>
              </v-flex>

            </v-layout>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>
          <!-- save button -->
          <v-btn :class="{'red-color': savePicked}" color="#1a1c33"
            depressed flat outline type="submit">
            <div @mouseleave="savePick" @mouseover="savePick">
              save
            </div>
          </v-btn>

          <!-- cancle button -->
          <v-btn :class="{'red-color': canclePicked}" color="#1a1c33"
            depressed flat outline @keyup.esc="dialog=false" @click="dialog=false">
            <div @mouseleave="canclePick" @mouseover="canclePick">
              cancel
            </div>
          </v-btn>
        </v-card-actions>
        </form>
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

export default {
  name: 'SignupModal',

  data() {
    return {
      name: '',
      email: '',
      password: '',
      passwordCheck: '',

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

      passwordCheckRules: [
        v => !!v || 'password is required',
      ],

      nameRules: [ v => !!v || 'name is required', ],
    }
  },

  methods: {
    async loginSubmit(){
      if(this.isPasswordSame()) {
        // axios로 로그인 요청
        const signupForm = {
          email: this.email,
          pw: this.password,
          name: this.name,
          location: '1',
        }

        await AccountService.signUpSubmit(signupForm)

      }
      else {
        alert("Password is unmatched");
      }
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

    isPasswordSame() { return this.password === this.passwordCheck }
  },
}
</script>
