<template>
  <div>
    <el-row :gutter="12">
    <el-col v-for="(category, idx) in categories" :span="6">
      <el-button type="info" plain>{{ category }}</el-button>
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


    return { categories }
  }
}
</script>

<style>

</style>
