<template>
  <h3>관심있는 장르 3가지를 골라주세요</h3>
  <el-row :gutter="12">
  <el-col v-for="(genre, idx) in state.bookCategory" :span="6">
    <el-card shadow="hover" @click="clickCategory(idx+1)">
      {{ genre }}
    </el-card>
  </el-col>
</el-row>
  <!-- <div>
    <div v-for="(genre, idx) in state.bookCategory">
      <p>{{ genre }} {{ idx }}</p>
    </div>
  </div> -->
</template>

<script>
import { reactive } from "vue"
import { useStore } from 'vuex'

export default {
  name: 'UserCategory',
  setup() {
    const state = reactive({
      movieCategory: ['액션', '코미디', '모험', '애니메이션', '다큐멘터리', '판타지',
      '범죄/스릴러', '전쟁', '로맨스', '음악', '공포', '역사'],
      bookCategory: ['시', '철학', '로맨스 소설', '판타지 소설', '추리 소설', '고전문학',
      '에세이', '교육', '자기계발', '역사', '예술', '경제/경영'],
      selected: []
    })

    const clickCategory = function (idx) {
      state.selected.push(idx)
    }

    const sendCategory = function () {
      store.dispatch('root/sendCategory', )
    }
    return { state, clickCategory}
  }
}
</script>

<style>

</style>
