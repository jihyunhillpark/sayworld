<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper" align="left" style="float: left">
      <el-skeleton style="width: 100%" >
        <template #template>
          <el-skeleton-item variant="image" style="width: 100%; height: 190px" />
        </template>
      </el-skeleton>
    </div>

    <div id="profile">
        <p v-for = "item in items">
        <img :src="item.image">
        </p>
    </div>
    <div>
      <!-- <avataaars></avataaars> -->
    </div>

      <button size="md" variant="danger" type="submit" v-on:click="goCulturePage">문화력 등록</button>
      <button size="md" variant="danger" type="submit" v-on:click="goMyBlog">내 블로그 바로가기</button>

    <div>
      <!--   <p v-for="(value, name) in info" v-bind:key="value">{{ name }} : {{value}}</p> -->
      <a>이메일 : </a>
      <a v-for="(email) in info.email" v-bind:key="email">{{email}}</a> <br>
      <a>닉네임 : </a>
      <a v-for="(nickname) in info.nickname" v-bind:key="nickname">{{nickname}}</a> <br>
      <a>연령대 : </a>
      <a>{{info.age}}</a> <br>
      <a>기본 페이지 설정 : </a>
      <a v-for="(defaultPage) in info.defaultPage" v-bind:key="defaultPage">{{defaultPage}}</a> <br>
    </div>
      <button size="md" variant="danger" type="submit"  v-on:click="getout">회원탈퇴</button>

    <div style="text-align: left; padding: 14px;">
        <span class="title">{{ title }}</span>
        <div class="bottom">
          <span>{{ desc }}</span>
        </div>
      </div>

    <article style="text-align: left">
      <section>
        <h2>관심사</h2>
        <p>Content...</p>
      </section>
      <section>
        <h2>분석</h2>
        <a><input type="radio" v-model="culture_check" value="책">책</a>
        <a><input type="radio" v-model="culture_check" value="영화">영화</a>
        <p>그래프 보여주기...</p>
      </section>
      <section>
        <h2>문화력</h2>
        <a><button size="md" variant="danger" type="submit" v-on:click="goMovieHistory">영화</button></a>
        <a>야{{moviecnt}}</a><br><br>
        <a><button size="md" variant="danger" type="submit" v-on:click="goBookHistory">독서</button></a>
        <a>호{{bookcnt}}</a>
      </section>
      <section>
        <h2>친구관리</h2>
        <p>Content...</p>
      </section>
    </article>

    </el-card>
  </template>
<script>
  import axios from "axios";
  import {useRouter} from "vue-router";
  import Avataaars from 'vuejs-avataaars'
  export default {
    components: {
      Avataaars
    },

    name: "MyPage",
    data(){
      return{
        info: [{}],
        culture_check: [],
        moviecnt: '',
        bookcnt: '',

        items: [
          {
            id:1,
            image: "https://www.gravatar.com/avatar/${store.state.root.userInfo.userId}?d=retro&s=400"
          },
          {
            id:2,
            image: "https://www.gravatar.com/avatar/{userId}?d=identicon&s=400"
          },
          {
            id:3,
            image: "https://img1.daumcdn.net/thumb/S272x320/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FGUVuD%2FbtqB6Zdi5iH%2FDK96QNZL62nsdwro9vjLMk%2Fimg.jpg"
          }
        ]
      }
    },

    created() {
      const k = localStorage.getItem('email')
      //this.info.push(localStorage.getItem('email'));
      axios.get("/users/userInfo/" + k)
        .then(response => {
          //alert("이건 사용자정보")
          this.info = response.data;
          console.log(this.info)
        })
        .catch(e => {
          console.log('error : ', e)
        })
    },

    mounted() {
      const t = localStorage.getItem('email')
      axios.get('users/cultureInfo/' + t)
        .then(function (res) {
          //alert("이건 문화력정보")
          console.log(res)
          console.log(res.data)

          let moviecnt = 0;
          let bookcnt = 0;
          for(var i=0; i<res.data.length; i++) {
            if(res.data[i].cultureCategory == 1) {
              bookcnt++;
            } else {
              moviecnt++;
            }
          }
          console.log(bookcnt)
          console.log(moviecnt)


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

      goCulturePage() {
        this.$router.push({
          name: "CultureRegister"
        })
      },

      goMyBlog() {
        this.$router.push({
          name: "MyBlog"
        })
      },

    goMovieHistory() {
      this.$router.push({
        name: "MyMovieHistory"
      })
    },

    goBookHistory() {
      this.$router.push({
        name: "MyBookHistory"
      })
    },



  },
};

</script>

  <style scoped>

  </style>
