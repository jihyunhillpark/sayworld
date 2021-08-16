<template>
  <p>{{ $route.params.categoryName }}</p>
  <el-row>
    <el-col :span="6" v-for="(room, index) of searchCategory" :key="index">
      <el-card class="box-card">
        <p>{{ room.roomName }}</p>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'

export default {
  name: "CategoryRoom",
  setup() {
    const store = useStore()
    const searchCategory = computed(() => store.state.root.searchCategory)

    return { searchCategory }
  }
}
</script>

<style>

</style>
