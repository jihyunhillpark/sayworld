<template>
<<<<<<< HEAD
  <el-card :body-style="{ padding: '0px' }">
=======
  <el-page-header @back="goBack" content="마이 페이지">
  </el-page-header>
  <el-divider></el-divider>

  <el-card>
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
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
<<<<<<< HEAD
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
=======
    <div>
<!--      <avataaars></avataaars>-->
    </div>

<!--    <button size="md" variant="danger" type="submit" v-on:click="goCulturePage">문화력 등록</button>-->
    <el-button type="text" v-on:click="goCulturePage">문화력 등록</el-button>
    <el-divider direction="vertical"></el-divider>
<!--    <button size="md" variant="danger" type="submit" v-on:click="goMyBlog">내 블로그 바로가기</button>-->
    <el-button type="text" v-on:click="goMyBlog">내 블로그 바로가기</el-button>


    <div class="text item">
      <el-row :gutter="20">
        <el-col :span="6" >
          <a>이메일 : </a>
        </el-col>
        <el-col :span="6">
          <a v-for="(email) in info.email" v-bind:key="email">{{email}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6" >
          <a>닉네임 :</a>
          </el-col>
        <el-col :span="6">
          <a v-for="(nickname) in info.nickname" v-bind:key="nickname">{{nickname}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6">
          <a>연령대 : </a>
        </el-col>
        <el-col :span="6">
          <a>{{info.age}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6" >
          <a>기본 페이지 : </a>
         </el-col>
        <el-col :span="6">
          <a v-for="(defaultPage) in info.defaultPage" v-bind:key="defaultPage">{{defaultPage}}</a> <br>
        </el-col>
      </el-row>
    </div>
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2

    <div style="text-align: left; padding: 14px;">
        <span class="title">{{ title }}</span>
        <div class="bottom">
          <span>{{ desc }}</span>
        </div>
      </div>

<<<<<<< HEAD
=======
    <el-divider content-position="left">관심사와 문화력</el-divider>

>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
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
<<<<<<< HEAD
        <a >{{sendmoviecnt}} 편</a><br><br>
        <a><button size="md" variant="danger" type="submit" v-on:click="goBookHistory">독서</button></a>
        <a>{{sendbookcnt}} 권</a>
=======
        <a>야{{moviecnt}}</a><br><br>
        <a><button size="md" variant="danger" type="submit" v-on:click="goBookHistory">독서</button></a>
        <a>호{{bookcnt}}</a>
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
      </section>
      <section>
        <h2>친구관리</h2>
        <p>Content...</p>
      </section>
    </article>
<<<<<<< HEAD

    </el-card>
  </template>
<script>
import axios from "axios";
import {useRouter} from "vue-router";


export default {
=======
    </el-card>

  <el-button class="last" type="danger" plain v-on:click="getout">회원 탈퇴</el-button>
  </template>
<script>
  import axios from "axios";
  import {useRouter} from "vue-router";
  import Avataaars from 'vuejs-avataaars'
  export default {
    components: {
      // Avataaars
    },

>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
    name: "MyPage",
    data(){
      return{
        info: [{}],
        culture_check: [],
<<<<<<< HEAD
        moviecnt: 0,
        bookcnt: 0,
=======
        moviecnt: '',
        bookcnt: '',

>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
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
<<<<<<< HEAD

    },

    computed: {
      sendmoviecnt: function() {
      const t = localStorage.getItem('email')
        let moviecnt = 0;
        let bookcnt = 0;
=======
    },

    mounted() {
      const t = localStorage.getItem('email')
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
      axios.get('users/cultureInfo/' + t)
        .then(function (res) {
          //alert("이건 문화력정보")
          console.log(res)
          console.log(res.data)

<<<<<<< HEAD
          for(var i=0; i<res.data.length; i++) {
            if(res.data[i].cultureCategory == 1) {
              bookcnt++
            } else {
              moviecnt++
=======
          let moviecnt = 0;
          let bookcnt = 0;
          for(var i=0; i<res.data.length; i++) {
            if(res.data[i].cultureCategory == 1) {
              bookcnt++;
            } else {
              moviecnt++;
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
            }
          }
          console.log(bookcnt)
          console.log(moviecnt)
<<<<<<< HEAD
        })
        return moviecnt
    },

      sendbookcnt: function() {
        const t = localStorage.getItem('email')
        let moviecnt = 0;
        let bookcnt = 0;
        axios.get('users/cultureInfo/' + t)
          .then(function (res) {
            //alert("이건 문화력정보")
            console.log(res)
            console.log(res.data)

            for(var i=0; i<res.data.length; i++) {
              if(res.data[i].cultureCategory == 1) {
                bookcnt++;
              } else {
                moviecnt++;
              }
            }
            //console.log(this.bookcnt)
            //console.log(this.moviecnt)

          })
        return this.bookcnt
      }

    },



=======


        })
    },


>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
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



<<<<<<< HEAD


=======
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
  },
};

</script>

  <style scoped>
<<<<<<< HEAD

=======
    .text {
      font-size: 20px;
      align-items: flex-end;
    }

    .item {
      padding: 18px 0;
    }

    .last {
      align-content: end;
    }
>>>>>>> 246211d9c6dc81e2bf906c0aae606a19062806a2
  </style>
