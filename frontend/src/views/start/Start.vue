<template>
  <el-container>
    <el-header>Header</el-header>
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
</style>
