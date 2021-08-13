<template>
  <el-container>
    <el-header class="main-header">
      <div class="logo"></div>
    </el-header>
    <el-main class="main-container">
      <el-row>
        <el-col :span="14"><ServiceCarousel /></el-col>
        <el-col :span="10"><Login @openSignupDialog="onOpenSignupDialog" /></el-col>
      </el-row>
    </el-main>
    <!-- <button @click="onOpenSignupDialog">회원가입</button> -->
  </el-container>
  <SignupDialog
    :open="signupDialogOpen"
    :bookCategories="bookCategories"
    :movieCategories="movieCategories"
    @closeSignupDialog="onCloseSignupDialog"
  />
</template>

<script>
import Login from "@/components/start/Login"
import SignupDialog from "@/components/start/SignupDialog"
import ServiceCarousel from "@/components/start/ServiceCarousel"

import { onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "Start",
  components: {
    Login,
    SignupDialog,
    ServiceCarousel,
  },
  data() {
    return {
      signupDialogOpen: false,
      bookCategories: [
        {
          "bookCategoryId": 1,
          "bookCategory": "시"
        },
        {
          "bookCategoryId": 2,
          "bookCategory": "철학"
        },
        {
          "bookCategoryId": 3,
          "bookCategory": "로맨스 소설"
        },
        {
          "bookCategoryId": 4,
          "bookCategory": "판타지 소설"
        },
        {
          "bookCategoryId": 5,
          "bookCategory": "추리 소설"
        },
        {
          "bookCategoryId": 6,
          "bookCategory": "고전문학"
        },
        {
          "bookCategoryId": 7,
          "bookCategory": "에세이"
        },
        {
          "bookCategoryId": 8,
          "bookCategory": "교육"
        },
        {
          "bookCategoryId": 9,
          "bookCategory": "자기계발"
        },
        {
          "bookCategoryId": 10,
          "bookCategory": "역사"
        },
        {
          "bookCategoryId": 11,
          "bookCategory": "예술"
        },
        {
          "bookCategoryId": 12,
          "bookCategory": "경제/경영"
        }
      ],
      movieCategories: [
        {
          "movieCategoryId": 1,
          "movieCategory": "액션"
        },
        {
          "movieCategoryId": 2,
          "movieCategory": "코미디"
        },
        {
          "movieCategoryId": 3,
          "movieCategory": "모험"
        },
        {
          "movieCategoryId": 4,
          "movieCategory": "애니메이션"
        },
        {
          "movieCategoryId": 5,
          "movieCategory": "다큐멘터리"
        },
        {
          "movieCategoryId": 6,
          "movieCategory": "판타지"
        },
        {
          "movieCategoryId": 7,
          "movieCategory": "범죄/스릴러"
        },
        {
          "movieCategoryId": 8,
          "movieCategory": "전쟁"
        },
        {
          "movieCategoryId": 9,
          "movieCategory": "로맨스"
        },
        {
          "movieCategoryId": 10,
          "movieCategory": "음악"
        },
        {
          "movieCategoryId": 11,
          "movieCategory": "공포"
        },
        {
          "movieCategoryId": 12,
          "movieCategory": "역사"
        }
      ],
    }
  },
  methods: {
    onOpenSignupDialog() {
      this.signupDialogOpen = true;
    },
    onCloseSignupDialog() {
      this.signupDialogOpen = false;
    }
  },

  //메인 페이지로 가면 로컬스토리지에 있는거 다 삭제
  created() {
    // localStorage.clear()
  },


  setup() {
    // const store = useStore()

    onMounted(() => {
      // 토큰 없이도...?!ㅜ
      // store.dispatch('root/requestCategory', { category: 'book' })
      // .then((res) => {
      //   console.log(res.data)
      //   this.bookCategories = res.data
      // })
      // store.dispatch('root/requestCategory', { category: 'movie' })
      // .then((res) => {
      //   this.movieCategories = res.data
      // })
    })
  }
};
</script>

<style>
.main-container {
  height: calc(100% - 100px);
  width: 100%;
  padding-left: 40px;
  padding-right: 40px;
}

.logo {
  display: inline-block;
  margin: 0 calc(50% - 51px);
  width: 200px;
  height: 50px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("../assets/images/sayworld-logo.png");
}
</style>
