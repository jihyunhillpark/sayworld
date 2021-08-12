<template>
  <div>
    {{ categories }}
    <el-row :gutter="12">
    <el-col v-for="(category, idx) in categories" :span="6" :key="idx">
      <el-button type="info" plain>{{ category.bookCategory }}</el-button>
    </el-col>
    </el-row>
  </div>
</template>

<script>
import { onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'RoomCategory',
  setup() {
    const store = useStore()
    const categories = store.dispatch('root/requestCategory', { category: 'book' })
    // mounted() {
    // if (this.user) {
    //   this.nickName = this.user.name;
    //   this.roomName = `${this.nickName}의 방`;
    //   this.tags = [];
    // }
    // this.fetchAllTags();

    return { categories }
  }
}
</script>

<style>

</style>
