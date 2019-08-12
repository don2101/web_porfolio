/**
 * Gitlab에 API 요청을 하는 Service
 */

import axios from 'axios'

const GITLAB_URL = "https://lab.ssafy.com/api/v4/projects/6033/repository/commits?&all=true&per_page=500"
const GITLAB_URL2 = "https://lab.ssafy.com/api/v4/projects/7498/repository/commits?&all=true&per_page=500"



export default {
/*jslint devel: true */
/* eslint-disable */

/**
 * Gitlab에서 API 데이터를 불러와
 * parsing을 담당하는 component
 */
  name: {
    "정송해": "cjsong",
    "배진호": "zino",
    "고민재": "SSAFY1_seoul10_gomj",
    "김현욱": "woogie91",
    "염희돈": "don2101",
    "don2101": "don2101",
    "cjsong": "cjsong",
    "zino": "zino",
    "woogie91": "woogie91",
    "SSAFY1_seoul10_gomj": "SSAFY1_seoul10_gomj",
    "rhalswo1234": "SSAFY1_seoul10_gomj",
  },

  // api데이터에서 committer_name, committed_date 추츨
  async apiRequest() {
    let parseData = {}
    let response = await axios.get(GITLAB_URL, {
        headers: {
          "PRIVATE-TOKEN": "G_sSKz7HmcoLts8nbC74"
        }
      })

    for(let i = 0; i < response.data.length; i++) {
      let committerId = response.data[i]['committer_name']
      let commiterName = this.name[committerId]
      let commitDate = response.data[i]['committed_date']

      if(this.isExist(parseData[commiterName])) {
        parseData[commiterName].push(
          this.parseDate(commitDate)[0]
        )
      } else {
        parseData[commiterName] = []
        parseData[commiterName].push(
          this.parseDate(commitDate)[0]
        )
      }
    }

    response = await axios.get(GITLAB_URL2, {
      headers: {
        "PRIVATE-TOKEN": "G_sSKz7HmcoLts8nbC74"
      }
    })

    for(let i = 0; i < response.data.length; i++) {
      let committerId = response.data[i]['committer_name']
      let commiterName = this.name[committerId]
      let commitDate = response.data[i]['committed_date']

      if(this.isExist(parseData[commiterName])) {
        parseData[commiterName].push(
          this.parseDate(commitDate)[0]
        )
      } else {
        parseData[commiterName] = []
        parseData[commiterName].push(
          this.parseDate(commitDate)[0]
        )
      }
    }

    return parseData
  },

  // parseData로 부터 chart를 그리는데 필요한 data set 생성
  async parseGraphData() {
    const parseData = await this.apiRequest()
    const graphData = {}

    for(let committerName in parseData) {
      graphData[committerName] = {}

      for(let idx = 0; idx < parseData[committerName].length; idx++) {
        let commitCount = parseData[committerName][idx]

        if(this.isExist(graphData[committerName][commitCount])) {
          graphData[committerName][commitCount] += 1
        } else {
          graphData[committerName][commitCount] = 1
        }
      }
    }

    return graphData
  },

  parseDate(date) {
    return date.split('T', 1)
  },

  isExist(data) {
    if(data) return true;
    else return false;
  }

}
