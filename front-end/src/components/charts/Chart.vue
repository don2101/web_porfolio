<template>
  <div>
    <div class="chart-style chart-responsive">
      <canvas :id="title"></canvas>
    </div>
  </div>
</template>


<script>
/**
 * 상위 컴포넌트에서 데이터를 받아 그래프를 출력하는 component
 */
import Chart from 'chart.js'

export default {
  name: "Chart",

  props: {
    title: {type: String},
    graphData: {type: Object},
  },

  data() {
    return {
      // 차트에 들어갈 데이터, 라벨, 옵션을 정의하는 데이터
      chartData: {
        type: 'line',
        
        data: {
          // x축 label
          labels: [],
          datasets: [
            { 
              // 이름 추가
              label: '',

              // y축 data
              data: [],

              backgroundColor: [ '#E53935', ],
              borderColor: [ '#FAFAFA', ],
              borderWidth: 3
            }
          ]
        },

        options: {
          responsive: true,
          maintainAspectRatio: false,
          lineTension: 1,
          scales: {
            
            yAxes: [{
              gridLines: {
                display: false,
                color: "white"
              },
              ticks: {
                beginAtZero: true,
              }
            }],

            xAxes: [{
              gridLines: {
                display: false,
                color: "white"
              },
            }]
          },

          legend: {
            onClick: this.getGitlabUrl,
            labels: {
              fontColor: '#FAFAFA'
            }
          }
          
        }
      }

    }
  },

  mounted() {
    this.setChartData()
    this.createChart(this.title, this.chartData);
  },


  methods: {
    // 데이터와 옵션을 통해 차트를 출력하는 기능
    createChart(chartId, chartData) {
      const ctx = document.getElementById(chartId);
      new Chart(ctx, {
        type: chartData.type,
        data: chartData.data,
        options: chartData.options,
      });
    },

    // 상위 component에서 전달된 차트 데이터를 설정하는 기능
    setChartData() {
      this.chartData.data.datasets[0].label = this.title
      this.chartData.data.datasets[0].data = Object.values(this.graphData)
      this.chartData.data.labels = Object.keys(this.graphData)
    },

    getGitlabUrl(e, legendItem) {
      const BASE_URL = "https://lab.ssafy.com/"
      const gitlabId = legendItem["text"]
      location.href = BASE_URL + gitlabId
    }
  }

}
</script>

<style>
.chart-style {
  position: relative;
  height: 25vh;
  width: 35vw;
}

@media (max-width: 992px) {
  .chart-responsive {
    position: relative;
    height: 40vh;
    width: 60vw;
  }
}
</style>
