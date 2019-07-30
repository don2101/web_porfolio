<template>
  <div>
    <h1 style="color: white;">TABLE PAGINATION</h1>
    <MemberList :listArray="memberArray" />
  </div>
</template>

<script>
import axios from 'axios'
import MemberList from './admin/MemberList.vue'
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
  created () {
    axios.get('http://localhost:9090/member')
    .then(response => {
      console.log(response);
      this.memberArray = response.data;
    })
    .catch(err => {
      console.log(err);
    });
  }
}
</script>

<style>
h1 {
  color: #454545;
  text-align: center;
}
</style>
