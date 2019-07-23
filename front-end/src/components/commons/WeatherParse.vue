<template>
  <div class="text-xs-center">
    <meta charset="utf-8">
    <img :src="weatherIcon"/>
    <span>{{temperature}}</span>
  </div>
</template>


<script>
import WeatherService from '../../service/WeatherService'

export default {
  name: 'WeatherParse',
  
  created (){
    this.getWeatherData()
  },

  methods : {
    async getWeatherData() {
      const result = await WeatherService.parseWeather()

      this.temperature = result.temperature
      this.weatherIcon = result.iconImage

    }
  },

  data() {
    return {
      temperature: '',
      weatherIcon: '',
    }
  }

}
</script>
