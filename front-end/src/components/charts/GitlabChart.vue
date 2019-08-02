<template>
  <div class="my-5">

    <v-flex v-for="(value, key) in graphData" :key="key" class="display-res-sm display-res-md display-res-lg my-3" >
      <Chart
        :title="key"
        :graphData="value"
      >
      </Chart>  
    </v-flex>

  </div>
</template>


<script>
/**
 * Vuex에서 관리하는 Gitlab 관련 API 데이터를 불러와
 * Chart에 넘겨주는 component 
 */
import Chart from './Chart'


export default {
  name: "GitlabChart",

  components: {
    Chart
  },

  computed: {
    graphData() {
      return this.$store.state.chartData
    }
  },
  
}
</script>


<style>
@media (max-width: 600px) {
  .display-res-sm {
    display:none !important;
  }
}

@media (min-width: 600px) {
  .display-res-md {
    display:block !important;
  }
}

@media (min-width: 992px) {
  .display-res-lg {
    display:inline-block !important;
  }
}
</style>