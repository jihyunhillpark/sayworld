<template>
  <a><button size="md" variant="danger" type="submit" style="float: right" @click="ShowMyMovie">나의 책 히스토리 보기!</button></a>
  <div id="wrapper">
    <p v-for="info in state.infos" :key="info">{{info.cultureTitle}}</p>
  </div>
</template>
<script>

import {useStore} from "vuex";
import {reactive} from "vue";
import axios from "axios";

export default {
  name: "MyBookHistory",
  el: '#wrapper',
  setup() {
    const store = useStore()
    const state = reactive({
      title: '',
      countDisplay: '',
      infos: '',
      cultureTitle: [],
      cultureCategory:'',
      a: ''

    })

    const ShowMyMovie = function () {
      const k = localStorage.getItem('email')
      //console.log(state.culture_check)
      axios.get('users/cultureInfo/' + k)
        .then(function (res) {
          state.infos = res.data.items
          console.log(res.data)
        })
        .catch(function (err) {
          console.log(err)
        })

    }
    return {state, ShowMyMovie}
  }




}

</script>

<style scoped>

</style>
