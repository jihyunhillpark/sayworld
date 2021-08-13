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
    @closeSignupDialog="onCloseSignupDialog"
  />
</template>

<script>
import Login from "@/components/start/Login"
import SignupDialog from "@/components/start/SignupDialog"
import ServiceCarousel from "@/components/start/ServiceCarousel"

export default {
  name: "Start",
  components: {
    Login,
    SignupDialog,
    ServiceCarousel,
  },
  data() {
    return {
      signupDialogOpen: false
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
    localStorage.clear()
  },


  setup() {

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
