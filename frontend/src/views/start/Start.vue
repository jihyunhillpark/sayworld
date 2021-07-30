<template>
  <el-container class="main-wrapper">
    <button @click="goHome">메인으로 가자</button>
    <button @click="onOpenSignupDialog">회원가입</button>
  </el-container>
  <SignupDialog
    :open="signupDialogOpen"
    @closeSignupDialog="onCloseSignupDialog"
  />
</template>

<script>
import { useRouter } from "vue-router"
import SignupDialog from "@/components/start/SignupDialog"

export default {
  name: "Start",
  components: {
    SignupDialog
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
