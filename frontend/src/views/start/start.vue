<template>
  <h1>
    초기페이지
    <button @click="goHome">메인으로 가자</button>
    <router-link to="/test">Test</router-link>
  </h1>
</template>

<script>
import { useRouter } from "vue-router";

export default {
  name: "Start",

  setup() {
    const router = useRouter();
    const goHome = function() {
      router.push({
        name: "test"
      });
    };
    return { goHome };
  }
};
</script>

<style></style>
