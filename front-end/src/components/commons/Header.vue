<template>
  <div>
    <v-container>
    <v-toolbar class="mt-2 ml-2" color="transparent" flat fixed>

      <v-btn icon dark to="/"><v-icon dark x-large>home</v-icon></v-btn>

      <TranslateAll></TranslateAll>
    </v-toolbar>
    </v-container>
  </div>
</template>


<script>
import TranslateAll from './translate/TranslateAll'

export default {
  name: 'Header',

  data () {
    return {
      //
    }
  },

  components: {
    TranslateAll
  },
}
</script>
