/**
 * 날씨관련 정보 API를 수집하는 component
 */

import axios from 'axios'


const BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=seoul,KR&appid=be56cc190c60d6cf403d6f2e619be86c";

export default {
  async parseWeather() {
    const result = await this.getWeatherData()

    let temp_max = (result["main"].temp_max - 273.15) + "º";
    let temp_min = (result["main"].temp_min - 273.15) + "º" ;

    let iconUrl = "http://openweathermap.org/img/wn/"+result["weather"][0].icon+".png";
    let temp = "temperature : " + temp_min + "  ~  " + temp_max;

    return {
      iconImage: iconUrl,
      temperature: temp,
    }
  },

  async getWeatherData() {
    const response = await axios.get(BASE_URL)

    return response.data
  }
}
