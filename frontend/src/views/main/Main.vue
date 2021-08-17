<template>
  <el-container class="main-wrapper">
    <MainHeader />
    <el-container class="main-container">
      <el-aside class="hide-on-small" width="240px">
        <MainSidebar />
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <!-- <MainFooter :height="`110px`" /> -->
  </el-container>
</template>
<style>
@import "https://unpkg.com/element-plus/lib/theme-chalk/index.css";
@import "./main.css";
@import "../../common/css/common.css";
@import "../../common/css/element-plus.css";
</style>
<script>
import MainHeader from "@/components/main/MainHeader";
import MainSidebar from "@/components/main/MainSidebar";
import MainFooter from "@/components/main/MainFooter";

export default {
  name: "Main",
  components: {
    MainHeader,
    MainSidebar,
    MainFooter,
  },
  data() {
    return {
    };
  },
  methods: {

  }
};
</script>
