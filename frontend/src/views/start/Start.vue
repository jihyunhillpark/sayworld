<template>
  <el-container>
    <el-header>Header</el-header>
    <el-main>
      <el-row>
        <el-col :span="14">카드</el-col>
        <el-col :span="10"><Login @openSignupDialog="onOpenSignupDialog"/></el-col>
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
import SignupDialog from "@/components/start/SignupDialog"
import Login from "@/components/start/Login"

export default {
  name: "Start",
  components: {
    SignupDialog,
    Login,
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

<style></style>
