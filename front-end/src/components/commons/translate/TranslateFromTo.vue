<template>
  <div id="translator">
    <h1>Translator</h1>

    <label>Korea</label>
    <input>

    <label>English</label>
    <input>
    <input>

    <button>
      translate
    </button>
  </div>
</template>

<script>
export default {
  name: 'TranslateFromTo'
}
</script>

<style>
#translator {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
