<template>
  <el-page-header @back="goBack" content="마이 페이지">
  </el-page-header>
  <el-divider content-position="left">회원정보</el-divider>

  <el-card>
    <div id="profile" >
      <p v-for = "item in items">
        <img :src="item.image">
      </p>
    </div>
    <!--    <button size="md" variant="danger" type="submit" v-on:click="goCulturePage">문화력 등록</button>-->
    <el-button type="text" v-on:click="goCulturePage">문화력 등록</el-button>
    <el-divider direction="vertical"></el-divider>
    <!--    <button size="md" variant="danger" type="submit" v-on:click="goMyBlog">내 블로그 바로가기</button>-->
    <el-button type="text" v-on:click="goMyBlog">내 블로그 바로가기</el-button>

    <div class="text item">
      <el-row :gutter="20">
        <el-col :span="6" >
          <a>이&nbsp;&nbsp;&nbsp;메&nbsp;&nbsp;&nbsp;일 </a>
        </el-col>
        <el-col :span="6">
          <a v-for="(email) in info.email" v-bind:key="email">{{email}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6" >
          <a>닉&nbsp;&nbsp;&nbsp;네&nbsp;&nbsp;&nbsp;임 </a>
        </el-col>
        <el-col :span="6">
          <a v-for="(nickname) in info.nickname" v-bind:key="nickname">{{nickname}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6">
          <a>연&nbsp;&nbsp;&nbsp;령&nbsp;&nbsp;&nbsp;대 </a>
        </el-col>
        <el-col :span="6">
          <a>{{info.age}}</a>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="6" >
          <a>관심 카테고리</a>
        </el-col>
        <el-col :span="6">
           <el-switch
            style="display: block"
            v-model="info.defaultPage"
            active-color="#7fc6a6"
            inactive-color="#f1a4a4"
            active-text="책"
            inactive-text="영화"
          ></el-switch> <br>
        </el-col>
      </el-row>
    </div>

    <div style="text-align: left; padding: 14px;">
      <span class="title">{{ title }}</span>
      <div class="bottom">
        <span>{{ desc }}</span>
      </div>
    </div>

    <el-divider content-position="left">관심사 & 문화력</el-divider>

    <article style="text-align: left">
      <section>
        <h2>관심사</h2>
        <a v-if="ResultMovie > ResultBook"> 책보다 영화를 {{ResultMovie-ResultBook}} 개 더 보셨군요!</a>
        <a v-else-if="ResultMovie < ResultBook"> 영화보다 책을 {{ResultBook-ResultMovie}} 개 더 보셨군요!</a>
        <a v-else-if="ResultMovie==0 && ResultBook==0"> 아직 등록된 문화력이 없네요. 문화력을 먼저 등록해주세요!</a>
        <a v-else> 최근에 회원님이 보신 영화와 책의 갯수가 {{ResultBook}}개로 똑같군요!</a><br>
      </section><br>

      <section>
        <h2>문화력</h2>
        <el-button type="text" v-on:click="goMovieHistory">영화&nbsp;</el-button>
        <a>{{getMovieCount}}</a>
        <a v-if="ResultMovie >= 7"> <img src="https://w.namu.la/s/51e9b4029ac1a490c24ea1718b11c895963a3c1b4d03e9d5938b083237f37eaeb52cf84bc5c57630cb71e41ae864b415728fcc9bd37530a87c750c3b565b1565259cd4d4d824cde6ce8c5ff1f2a89e64513e97ee940401469370f99c2f6131fb"> </a>
        <a v-else-if="ResultMovie >= 5"> <img src="https://ww.namu.la/s/52ec96c36593cb9a5207c62eae6643e5c61feddd26f0d839448289c9ecd639d90c2bc52ca84a49a728d36bee6d49b2527ec1edacdf394aff7f0e9e4d97a9e98fcf2859e93edcdd5094d1cb03ed43959b2304d4b9d7f5b19d1065eb0d9453744b"> </a>
        <a v-else-if="ResultMovie >= 3"> <img src="https://ww.namu.la/s/12972d264f271356f0628c24e6a7a3c51a7899873758ce82539541afc4ab5cf253371d5cae03104d1c8d9c6144bf19a2319f6d655cba4a37bf070bec127325510607a65ee28c8f9aa82377b92df8b301fd73f22518694cc3f6e06da9da15ff49"> </a>
        <a v-else> <img src="https://w.namu.la/s/11942d0ba53861ba149c2bce0c0f1410d9b20b27df1aa9f57ffc581a9803135b8a40550d538265aabf27e9c9af7c7551137bf916ee48e7b46e24ff12715a4c9fe7cec0596a29f4a9b3f417c897f5b128c5a7ce0d6147386808f3b21dbb8a2be8"> </a>

        <el-divider direction="vertical"></el-divider>

        <el-button type="text" v-on:click="goBookHistory">독서&nbsp;</el-button>
        <a>{{getBookCount}}</a>
        <a v-if="ResultBook >= 7"> <img src="https://w.namu.la/s/51e9b4029ac1a490c24ea1718b11c895963a3c1b4d03e9d5938b083237f37eaeb52cf84bc5c57630cb71e41ae864b415728fcc9bd37530a87c750c3b565b1565259cd4d4d824cde6ce8c5ff1f2a89e64513e97ee940401469370f99c2f6131fb"> </a>
        <a v-else-if="ResultBook >= 5"> <img src="https://ww.namu.la/s/52ec96c36593cb9a5207c62eae6643e5c61feddd26f0d839448289c9ecd639d90c2bc52ca84a49a728d36bee6d49b2527ec1edacdf394aff7f0e9e4d97a9e98fcf2859e93edcdd5094d1cb03ed43959b2304d4b9d7f5b19d1065eb0d9453744b"> </a>
        <a v-else-if="ResultBook >= 3"> <img src="https://ww.namu.la/s/12972d264f271356f0628c24e6a7a3c51a7899873758ce82539541afc4ab5cf253371d5cae03104d1c8d9c6144bf19a2319f6d655cba4a37bf070bec127325510607a65ee28c8f9aa82377b92df8b301fd73f22518694cc3f6e06da9da15ff49"> </a>
        <a v-else> <img src="https://w.namu.la/s/11942d0ba53861ba149c2bce0c0f1410d9b20b27df1aa9f57ffc581a9803135b8a40550d538265aabf27e9c9af7c7551137bf916ee48e7b46e24ff12715a4c9fe7cec0596a29f4a9b3f417c897f5b128c5a7ce0d6147386808f3b21dbb8a2be8"> </a>

      </section><br>
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
        {id:1, image: "https://www.gravatar.com/avatar/04b828795157ecfab7765d9cf84?s=200"}
      ],
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
  align-content: center;
}


</style>
