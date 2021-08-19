<template>
  <el-row :gutter="12">
  <el-col v-for="(category, idx) in state.categories" :span="6" :key="idx">
    <el-button v-if="curPage" class="bookcategoryBtn" type="success" @click="clickCategory(category.bookCategory, category.bookCategoryId)" plain>{{ category.bookCategory }}</el-button>
    <el-button v-else class="moviecategoryBtn" type="danger" @click="clickCategory(category.movieCategory, category.movieCategoryId)" plain>{{ category.movieCategory }}</el-button>
  </el-col>
  </el-row>
</template>

<script>
import { reactive, computed, watch } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from "vue-router"

export default {
  name: 'RoomCategory',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      categories: [],
    })

    const curPage = computed({
      get: () => store.state.root.curPage,
    })

    const clickCategory = (category, categoryId) => {
      store.dispatch('root/searchRoom', {searchType: 'category', searchValue: String(categoryId)})
      router.push({ name: 'CategoryRoom', params: { categoryName: category} })
    }

    watch (curPage, (curValue, oldValue) => {
      if (curValue) {
        store.dispatch('root/requestCategory', { category: 'book' })
        .then((res) => {
          state.categories = res.data
        })
      } else {
        store.dispatch('root/requestCategory', { category: 'movie' })
        .then((res) => {
          state.categories = res.data
        })
      }
    }, { immediate: true })

    return { state, curPage, clickCategory }
  }
}
</script>

<style scoped>
.bookcategoryBtn {
  width: 100%;
  margin-top: 10px;
  height: 100%;
  text-align: center;
  font-size: 1.5rem;
}
.moviecategoryBtn {
  width: 100%;
  margin-top: 10px;
  height: 100%;
  text-align: center;
  font-size: 1.5rem;
}
.el-col {
  margin-top: 10px !important;
}
.el-row {
  height: 90%;
  width: 100%;
  margin-left: 10px;
}
</style>
<style lang="scss" scoped>
.el-button--success.is-plain {
  background-color: #dcefe6;
  color: darkgreen;

  &:hover,
  &.active,
  &:focus {
    background: #96cfb4;
  };
}
.el-button--danger.is-plain {
  background-color: #fef0f0;
  color: #f56c6c;

  &:hover,
  &.active,
  &:focus {
    background: #f6bcbc;
  };
}
</style>
