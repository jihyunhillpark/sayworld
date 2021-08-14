<template>
  <div>
    <el-row :gutter="12">
    <el-col v-for="(category, idx) in state.categories" :span="6" :key="idx">
      <el-button class="categoryBtn" type="info" @click="clickCategory(category.bookCategory, category.bookCategoryId)" plain>{{ category.bookCategory }}</el-button>
    </el-col>
    </el-row>
  </div>
</template>

<script>
import { onMounted, reactive } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'RoomCategory',
  setup() {
    const store = useStore()
    const state = reactive({
      categories: [],
    })

    const clickCategory = (category, categoryId) => {
      // axios 해당 카테고리 검색
      // router -> 해당 방으로 ---- 그러면 거기서 회의 방 쭉 보여주자
    }

    onMounted(() => {
      store.dispatch('root/requestCategory', { category: 'book' })
      .then((res) => {
        console.log(res.data)
        state.categories = res.data
      })
    })
    return { state, clickCategory }
  }
}
</script>

<style>
.categoryBtn {
  width: 100%;
  margin-top: 10px;
  height: 8vw;
  background-color: #f4f4f5;
  text-align: center;
}
</style>
