<template>
  <el-page-header @back="goBack" content="마이 페이지">
  </el-page-header>
  <el-divider></el-divider>

  <el-card>
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

    <div style="text-align: left; padding: 14px;">
      <span class="title">{{ title }}</span>
      <div class="bottom">
        <span>{{ desc }}</span>
      </div>
    </div>

    <el-divider content-position="left">관심사와 문화력</el-divider>

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
        <a>{{getMovieCount}}</a><br><br>
        <a><button size="md" variant="danger" type="submit" v-on:click="goBookHistory">독서</button></a>
        <a>{{getBookCount}}</a>
      </section>
      <section>
        <h2>친구관리</h2>
        <p>Content...</p>
      </section>
    </article>
  </el-card>

  <el-button class="last" type="danger" plain v-on:click="getOut">회원 탈퇴</el-button>
</template>
<script>
import axios from "axios";
import {useRouter} from "vue-router";

export default {
  name: "MyPage",
  data(){
    return{
      info: [{}],
      culture_check: [],
      movieCnt: 0,
      bookCnt: 0,
      ResultMovie: '',
      ResultBook: '',
      myInfo: [],
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

  computed: {
    getMovieCount() {
    const t = localStorage.getItem('email');
    let myMovieCnt = this.movieCnt
    axios.get('users/cultureInfo/' + t)
      .then(res => {
        //alert("이건 문화력정보")
        this.myInfo = res.data
        console.log(res.data)
        //console.log(this.myInfo)
        for(let i=0; i<this.myInfo.length; i++) {
          if(this.myInfo[i].cultureCategory == 0) {
            myMovieCnt++;
          }
        }
        //console.log(myMovieCnt)
        //ResultMovie변수에 이 값을 저장해두고
        this.ResultMovie = myMovieCnt
      })
      //console.log(myMovieCnt)
      //이걸 리턴하자!
      return this.ResultMovie
    },

    getBookCount() {
      const t = localStorage.getItem('email');
      let myBookCnt = this.bookCnt
      axios.get('users/cultureInfo/' + t)
        .then(res => {
          //alert("이건 문화력정보")
          this.myInfo = res.data
          console.log(res.data)
          //console.log(this.myInfo)
          for(let i=0; i<this.myInfo.length; i++) {
            if(this.myInfo[i].cultureCategory == 1) {
              myBookCnt++;
            }
          }
          //ResultBook변수에 이 값을 저장해두고
          this.ResultBook = myBookCnt
        })
      //이걸 리턴하자!
      return this.ResultBook
    }
  },

  methods: {
    getOut() {
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
</style>
