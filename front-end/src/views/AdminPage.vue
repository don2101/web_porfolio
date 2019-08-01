<template>
  <div>
    <h1 style="color: white;">회원 관리 페이지</h1>
    <MemberList :listArray="memberArray" />
  </div>
</template>

<script>
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
  async created () {
    let grade = await AdminService.getGrade(sessionStorage.getItem("mid"));
    if(grade === '0'){
      axios.get('http://localhost:9090/member')
      .then(response => {
        console.log(response);
        this.memberArray = response.data;
      })
      .catch(err => {
        console.log(err);
      });
    }else{
      alert("권한이 없습니다.")
      window.location.href='/'
    }

  }
}
</script>

<style>
h1 {
  color: #454545;
  text-align: center;
}
</style>
