<template>
  <p><button size="md" variant="danger" type="submit" style="float: left" @click="ShowMyBook">나의 책 히스토리</button></p><br><br>
  <p></p>
  <table class='table table-dark'  style="width: 100%; height: 100%" border='2px' v-show="state.ok">
    <thead>
    <tr>
      <th scope='col'>책 이름</th>
      <th scope='col'>책 상세정보</th>
      <th scope='col'>삭제하기</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="info in state.infos" v-bind:key="info" v-show="info.cultureCategory == 1">
      <td>{{info.cultureTitle}}</td>
      <td>
        <button size="md" variant="danger" type="submit" v-on:click="openPopup($event)"  :value="info.cultureTitle">Click!</button>
      </td>
      <td>
        <button size="md" variant="danger" type="submit" v-on:click="DeleteBook($event)" :value="info.cultureTitle">삭제</button>
      </td>
    </tr>
    </tbody>
  </table>

</template>

<script>

import {useStore} from "vuex";
import {reactive} from "vue";
import { useRouter } from 'vue-router';
import axios from "axios";

export default {
  name: "MyBookHistory",
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      title: '',
      countDisplay: '',
      infos: '',
      cultureTitle: [],
      cultureCategory:'',
      a: '',
      ok: false,
      infoDetail: ''
    })

    const ShowMyBook = function () {
      const k = localStorage.getItem('email')
      //console.log(state.culture_check)

      axios.get('users/cultureInfo/' + k)
        .then(function (res) {
          console.log(res)
          console.log(res.data)
          state.infos = res.data
          console.log(state.infos)
          state.ok = true
        })
        .catch(function (err) {
          console.log(err)
        })
    }

    const openPopup = function (e) {
      const buttonValue = e.target.value;
      console.log(buttonValue);

      axios.post('/books/search', {
        params: {
          title: buttonValue
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
          state.infoDetail = res.data.items
          console.log(res.data)
          console.log(res.data.items)
          console.log(state.infoDetail)
          const imgsrc = res.data.items[0].image
          console.log(imgsrc)

          var win = window.open("", "PopupWin", "width=500,height=600");
          win.document.write("< " + res.data.items[0].title + " >")
          win.document.write("<br><br> <img src={{imgsrc}}/>")
          win.document.write("<br><br>작가 : " + res.data.items[0].author)
          win.document.write("<br><br>책 간단 요약 : " + res.data.items[0].description)
        })
        .catch(function (err) {
          console.log(err)
        })
    }


    const DeleteBook = function (e) {
      const buttonValue = e.target.value;
      const t = localStorage.getItem('email')
      axios.delete('/users/deleteCulture/' + t, {
        data: {
          cultureTitle : buttonValue,
          cultureCategory : 1
        }
      })
        .then(res => {
          this.info = res.data;
          console.log(this.info)
          alert("삭제가 완료되었습니다:)")
          window.location.reload()
        }).catch(ee => {
        console.log('error : ', ee)
      })
    }

    return {state, ShowMyBook, openPopup, DeleteBook}
  }

}

</script>

<style scoped>
table{
  background: #7fc6a6;
}

button{
  background: #ECF5FF;
}
</style>
