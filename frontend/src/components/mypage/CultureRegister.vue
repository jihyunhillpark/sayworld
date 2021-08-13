<template>
  <a>
    <form id="myForm" @submit.prevent="sendPost">
      <a><input type="radio" v-model="culture_check" value="책">책 &nbsp;&nbsp;</a>
      <a><input type="radio" v-model="culture_check" value="영화">영화 &nbsp;&nbsp;</a>
      <a><input type="text" name="title" v-model="state.title" placeholder="검색어를 입력하세요"> &nbsp;&nbsp;</a>
      <button size="md" variant="danger" type="submit">검색</button>
    </form>
    </a>
  <a><button size="md" variant="danger" type="submit" style="float: right">등록하기</button></a>
  <p>목록</p>

  <div class="wrapper">
      <v-card-element v-for="(info, idx) in state.infos" :key="info" >
        <img :src="info.image">
        <p>{{info.title.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "")}}</p>
        <p><input type="checkbox"></p><br>
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
      title: '',
      countDisplay: '',
      infos: '',
    })

    const sendPost = function () {
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
          //const infos = []
          //this.countDisplay = res.data.display
          //alert(res.data.display)
          //this.infos = res.data.items
          //이거다!!!!!
          //alert(res.data.items[0].title)
          //console.log(this.infos)
          // for(const key in res.data.items) {
          //   console.log(this.infos[key].title)
          //   //this.infos[key] = res.data.items[key].title
          //   console.log(res.data.items[key].author)
          //   console.log(res.data.items[key].image)
          //   //alert(infos[key])
          // }
          //alert("출력 다 된거다 이자식아")
        })
        .catch(function (err) {
          console.log(err)
        })
    }
    return {state, sendPost}
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
