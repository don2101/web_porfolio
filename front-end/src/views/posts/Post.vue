<template>
  <div>
    <v-list-tile-title>{{ title }}</v-list-tile-title>
    <v-list-tile-sub-title class="text--primary">{{ date }}</v-list-tile-sub-title>
    <v-list-tile-sub-title>{{ writer }}</v-list-tile-sub-title>
    
    <v-divider>

    </v-divider>

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
