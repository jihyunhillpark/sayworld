<template>
  <a>
    <form id="myForm" @submit.prevent="sendPost">
      <a><input type="radio" v-model="state.culture_check" value="책">책 &nbsp;&nbsp;</a>
      <a><input type="radio" v-model="state.culture_check" value="영화">영화 &nbsp;&nbsp;</a>
      <a><input type="text" name="title" v-model="state.title" placeholder="검색어를 입력하세요"> &nbsp;&nbsp;</a>
      <button size="md" variant="danger" type="submit">검색</button>
    </form>

    </a>
  <a><button size="md" variant="danger" type="submit" style="float: right" @click="RegisterOK">등록하기</button></a>
  <p>목록</p>

  <div class="wrapper">
      <v-card-element v-for="(info, idx) in state.infos" :key="info" >
        <img :src="info.image">
        <p>{{info.title.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")}}</p>
        <p><input type="checkbox" v-model="state.cultureTitle" :value="info.title.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, '')"></p><br>
      </v-card-element>
  </div>
</template>

<script>
import {useRouter} from "vue-router";
import axios from "axios";
import {reactive} from "vue";
import {useStore} from "vuex";

export default {
  name: "CultureRegister",
  el: '#myForm',
  setup() {
    const store = useStore()
    const state = reactive({
      culture_check: '책',
      title: '',
      countDisplay: '',
      infos: '',
      cultureTitle: [],
      cultureCategory:''

    })

    const sendPost = function () {
      //책이 클릭되면 책검색
      console.log(state.culture_check)
      if(state.culture_check == '책') {
        console.log(state.title)

        axios.post('/books/search', {
          params: {
            title: this.state.title
          }
        }, {
          headers: {
            'Content-Type': 'application/json',
            'X-Naver-Client-Id': 'fj19qSeYZpP5xmRuUQ_l',
            'X-Naver-Client-Secret': 'ZeN6POhLUU'
          }
        })
          //통신이 끝났으면 네이버 검색api에서 가져온 정보들을 보여줘야지
          .then(function (res) {
            state.infos = res.data.items
            console.log(res.data)
          })
          .catch(function (err) {
            console.log(err)
          })
      //책 안눌렸으면 영화검색
      } else {
        axios.post('/movies/search', {
          params: {
            title: this.state.title
          }
        }, {
          headers: {
            'Content-Type': 'application/json',
            'X-Naver-Client-Id': 'fj19qSeYZpP5xmRuUQ_l',
            'X-Naver-Client-Secret': 'ZeN6POhLUU'
          }
        })
          //통신이 끝났으면 네이버 검색api에서 가져온 정보들을 보여줘야지
          .then(function (res) {
            state.infos = res.data.items
            console.log(res.data)
          })
          .catch(function (err) {
            console.log(err)
          })
      }
    }

    const RegisterOK = function() {
      console.log(state.cultureTitle.slice(0))
      //console.log(state.cultureTitle[1])
      //console.log(this.state.cultureTitle)
      //console.log(state.infos.title)
      //체크 박스 선택된 것들의 title과 category를 뽑아서 전달해줘야지
      const k = localStorage.getItem('email')

      //책이면 문화카테고리 1
      if(state.culture_check == '책') {
        axios.post("/users/culture/" + k, {
          cultureCategory: '1',
          cultureTitle: this.state.cultureTitle[0]
        })
          .then(response => {
            this.info = response.data;
            console.log(this.info)
            alert("내 문화력에 등록되었습니다!")
            window.location.reload()
          })
          .catch(e => {
            console.log('error : ', e)
            alert("등록에 실패하였습니다.")
          })
      }
      //영화면 컬쳐 카테고리는 0
      else {
        axios.post("/users/culture/" + k, {
          cultureCategory: '0',
          cultureTitle: this.state.cultureTitle[0]
        })
          .then(response => {
            this.info = response.data;
            console.log(this.info)
            alert("내 문화력에 등록되었습니다!")
            window.location.reload()
          })
          .catch(e => {
            console.log('error : ', e)
            alert("등록에 실패하였습니다.")

          })
        }
      }
    return {state, sendPost, RegisterOK}
  }

}
</script>

<style scoped>
h2 {
  text-align: center;
}

a {
  list-style: none;
  text-decoration: none;
}

li {
  list-style: none;
}

.wrap {
  max-width: 450px;
  width: 100%;
  margin: 0 auto;
}

.item {
  border-bottom: 1px solid #ebebeb;
  margin-bottom: 25px;
}

.tit {
  display: inline-block;
  font-size: 18px;
  font-weight: bold;
  color: #000;
  padding: 20px 15px;
}

img {
  width: 20%;
  background: #ebebeb;
  border-radius: 4px;
}

.wrapper {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

}
</style>
