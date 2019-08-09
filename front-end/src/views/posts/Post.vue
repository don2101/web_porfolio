<template>
  <div>
    <v-layout class="post-title">
      <v-flex xs2>
        <p>{{ writer }}</p>
      </v-flex>
      <v-flex xs8>
        {{ title }}
      </v-flex>
      <v-flex xs2>
        {{ date }}
      </v-flex>
    </v-layout>
    
    <hr class="post-title-hr"/>
  </div>
</template>


<script>
export default {
  name: "Post",

  props: {
    title: {type: String},
    date: {type: String},
    writer: {type: String},
  },

  methods: {
    buttonPick() {
      this.buttonPicked = !this.buttonPicked
    }
  },

  data() {
    return {
      buttonPicked: false
    }
  }
}
</script>


<style>

</style>
