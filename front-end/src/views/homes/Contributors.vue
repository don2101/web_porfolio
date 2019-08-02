<template>
  <div>
    <h1 class="main-title">Contributors</h1>
    <GitlabChart></GitlabChart>
  </div>
</template>


<script>
/**
 * Contributors gitlab chart 출력을 담당하는 component
 * CitlabChart.vue를 사용하여 chart 출력
 */
import GitlabChart from '../../components/charts/GitlabChart'

export default {
  name: "Contributors",

  components: {
    GitlabChart,
  }
}
</script>


