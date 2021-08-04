<template>
  <div class="main-sidebar">
    <el-menu class="main-sidebar-container">
      <el-menu-item index="1" @click="homeSelect">
        <i class="el-icon-s-home"></i>
        <span>홈</span>
      </el-menu-item>
      <el-submenu index="2">
        <template #title>
          <i class="el-icon-mic"></i>
          <span>방 목록</span>
        </template>
        <el-menu-item-group title="Group One">
          <el-menu-item index="1-1">item one</el-menu-item>
          <el-menu-item index="1-2">item one</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="Group Two">
          <el-menu-item index="1-3">item three</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template #title>item four</template>
          <el-menu-item index="1-4-1">item one</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="3" @click="blogSelect">
        <i class="el-icon-document"></i>
        <span>블로그</span>
      </el-menu-item>
      <el-menu-item index="4" @click="boardSelect">
        <i class="el-icon-edit-outline"></i>
        <span>게시판</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  name: "MainSidebar",
  setup() {
    const router = useRouter()

    const homeSelect = function () {
      router.push({ name: 'Home' })
    }

    return { homeSelect }
  }
}
</script>

<style>
.main-sidebar {
  height: 100%;
}

.main-sidebar .main-sidebar-container {
  height: 100%;
}
</style>
