<template>
  <el-container class="main-wrapper">
    <button @click="goHome">메인으로 가자</button>
  </el-container>
</template>

<script>
import { useRouter } from "vue-router";
export default {
  name: "Start",
  components: {},
  setup() {
    const router = useRouter();
    const goHome = function() {
      router.push({
        name: "main"
      });
    };
    return { goHome };
  }
};
</script>

<style></style>
