<template>
  <div>
    <h1 class="main-title my-5">Admin Page</h1>
    <MemberList :listArray="memberArray" />
  </div>
</template>


<script>
/**
 * 사용자가 관리자 인지 확인하고,
 * 유저 리스트를 가져와 출력하는 컴포넌트
 */

import axios from 'axios'
import MemberList from './admin/MemberList.vue'
import AdminService from '../service/AdminService'

export default {
  name: 'simple-pagination',
  
  components: {
    MemberList
  },

  data () {
    return {
      memberArray: []
    }
  },

  async beforeCreate () {
  // 사용자를 확인
  // 페이지가 로드되기 전에(컨텐츠가 보이기 전에) 사용자 인지 확인
    let grade = await AdminService.getGrade(sessionStorage.getItem('jwt'));
    
    if(grade === '0'){
        const response = await AdminService.getMemberList()
        this.memberArray = response;
    } else {
      alert("권한이 없습니다.")
      window.location.href='/'
    }
  }
}
</script>
