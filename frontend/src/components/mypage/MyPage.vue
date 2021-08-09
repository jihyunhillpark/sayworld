<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-skeleton-item variant="image" style="width: 100%; height: 190px" />
        </template>
      </el-skeleton>
    </div>
    <div>
          <p>회원님의 정보입니다.</p>
      <!--   <p v-for="(value, name) in info" v-bind:key="value">{{ name }} : {{value}}</p> -->
      <a>이메일 : </a>
      <a v-for="(email) in info.email" v-bind:key="email">{{email}}</a> <br>
      <a>닉네임 : </a>
      <a v-for="(nickname) in info.nickname" v-bind:key="nickname">{{nickname}}</a> <br>
      <a>연령대 : </a>
      <a>{{info.age}}</a> <br>
      <a>기본 페이지 설정 : </a>
      <a v-for="(defaultPage) in info.defaultPage" v-bind:key="defaultPage">{{defaultPage}}</a> <br>
      <a>회원등급 : </a> <br>
  </div>
  <row align-h="center">
      <button size="md" variant="danger" type="submit"  v-on:click="getout">회원탈퇴</button>
  </row>

      <div style="text-align: left; padding: 14px;">
        <span class="title">{{ title }}</span>
        <div class="bottom">
          <span>{{ desc }}</span>
        </div>
      </div>
    </el-card>
  </template>
  <script>
  import axios from "axios";
  import {useRouter} from "vue-router";

  export default {
    name: "MyPage",
    data(){
      return{
        info: [{}]
      }
    },

    created() {
      const k = localStorage.getItem('email')
      //this.info.push(localStorage.getItem('email'));
      axios.get("/users/userInfo/" + k)
      .then(response => {
        this.info = response.data;
        console.log(this.info)
      })
      .catch(e => {
        console.log('error : ', e)
      })


    },

    methods: {
      getout() {
        const router = useRouter()
        const t = localStorage.getItem('email')
        alert("정말 탈퇴하시겠어요?")
        axios.delete('/users/userDelete/' + t)
        .then(res => {
          this.info = res.data;
          console.log(this.info)
          alert("이용해주셔서 감사합니다:)")
          this.$router.push({
            name: "Start"
        })

        })
        .catch(e => {
          console.log('error : ', e)
        })
      },




    },
  };

  </script>

  <style scoped>

  </style>
