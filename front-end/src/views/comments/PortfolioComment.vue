<template>

  <p>{{ content }}</p>


</template>


<script>
export default {
  name: 'PortfolioComment',

  props: {
    content: {type: String},
  },
}

</script>
