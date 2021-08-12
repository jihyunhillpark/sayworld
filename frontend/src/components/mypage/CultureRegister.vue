<template>
  <a><input type="radio" v-model="culture_check" value="책">책</a>
  <a><input type="radio" v-model="culture_check" value="영화">영화</a>

  <a>
    <form id="myForm" @submit.prevent="sendPost">
    <input type="text" name="title" v-model="title" placeholder="검색어를 입력하세요">
    <button size="md" variant="danger" type="submit">검색</button>
    </form>
  </a>

  <p>나의 리스트 </p>
  <a v-for="list in info" v-bind:key="list">{{list}}</a> <br>

  <row align-h="center">
    <button size="md" variant="danger" type="submit"  v-on:click="cultureRegister">등록</button>
  </row>

</template>

<script>
import {useRouter} from "vue-router";
import axios from "axios";
import {reactive} from "vue";
import {useStore} from "vuex";
export default {
  name: "CultureRegister",
  el: '#myForm',
  data() {
    return {
      title: ''
    }
  },
  methods: {
    sendPost: function () {
      alert(this.title)
      axios.post('books/check', {
        title: this.title,
      })
        .then(function(res) {
          console.log(res.data)
        }, function() {
          console.log('failed')
        })
    }
  }
}
</script>

<style scoped>

</style>
