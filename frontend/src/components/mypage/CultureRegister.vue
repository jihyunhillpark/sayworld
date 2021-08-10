<template>
  <a><input type="radio" v-model="culture_check" value="책">책</a>
  <a><input type="radio" v-model="culture_check" value="영화">영화</a>
  <a><form method=get action="https://naver.com" target="_blank" >
    <input type=text name=q size=25 maxlength=255 value="" /> <!-- 구글 검색 입력 창 -->
    <input type=button name=check value="문화력 검색" v-on:click="cultureFind" /> <!-- 검색 버튼 -->
  </form></a>
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

export default {
  name: "CultureRegister",
  data() {
    return {
      culture_check: [],
      info: [{}]
    }
  },

  methods: {
    cultureFind() {
      const router = useRouter()
      const t = localStorage.getItem('email')
      axios.get('/users/culture/' + t)
        .then(res => {
          this.info = res.data;
          console.log(this.info)


        })
        .catch(e => {
          console.log('error : ', e)
        })
    },
    
    cultureRegister() {
      alert('등록이 완료되었습니다.')
    }
  }
}

</script>

<style scoped>

</style>
