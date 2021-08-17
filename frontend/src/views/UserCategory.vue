<template>
  <div>
    <h3>관심있는 장르 3가지를 골라주세요</h3>
    <!-- progress bar 추가 -->
    <!-- 책, 영화 카테고리 DB에 default로 저장 해야함 -->
    <div>
      <el-row :gutter="12">
      <!-- 클릭했을 때 색상 변경 + 완료 버튼 디자인 수정 -->
      <el-col v-for="(genre, idx) in state.bookCategory" :span="6" :key="idx">
        <el-button :id="idx+1" class="categoryBtn" type="info" @click="clickCategory(idx+1)" plain>{{ genre }}</el-button>
        <!-- <el-button :class="{categoryBtn: !state.isClick, categoryBtnClick: state.isClick}" type="info" @click="clickCategory(idx+1)" plain>{{ genre }}</el-button> -->
        <!-- <el-card :class="{categoryBtn: !state.isClick, categoryBtnClick: state.isClick}" @click="clickCategory(idx+1)" shadow="hover">{{ genre }}</el-card> -->
      </el-col>
      </el-row>
    </div>
    <div>
      <el-button @click="sendCategory" type="primary" :disabled="state.selected.length < 3">완료</el-button>
    </div>
  </div>

</template>

<script>
import { reactive } from "vue"
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'UserCategory',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      bookCategory: ['시', '철학', '로맨스 소설', '판타지 소설', '추리 소설', '고전문학',
      '에세이', '교육', '자기계발', '역사', '예술', '경제/경영'],
      movieCategory: ['액션', '코미디', '모험', '애니메이션', '다큐멘터리', '판타지',
      '범죄/스릴러', '전쟁', '로맨스', '음악', '공포', '역사'],
      selected: [],
      isClick: false,
      curPage: 1,
    })

    const clickCategory = function (idx) {
      var btn = document.getElementById(idx)

      if (state.selected.indexOf(idx) === -1) {
        state.selected.push(idx)
        state.isClick = true
        btn.style.backgroundColor = "#909399"
        btn.style.color = "white"

      } else {
        state.selected.splice(state.selected.indexOf(idx), 1)
        state.isClick = false
        btn.style.backgroundColor = "#f4f4f5"
        btn.style.color = "#909399"
      }
    }

    const sendCategory = function () {
      const payload = []
      for (var category of state.selected) {
        payload.push({ categoryId: category, movieOrBook: state.curPage })
      }
      store.dispatch('root/sendCategory', payload)
      .then((res) => {
        router.push({ name: 'Home' })
        console.log(res)
      })
      .catch((err) => {
        console.log(err)
      })
    }
    return { state, clickCategory, sendCategory }
  }
}
</script>

<style scoped>
.categoryBtn {
  width: 100%;
  margin-top: 10px;
  height: 8vw;
  background-color: #f4f4f5;
  text-align: center;
}
.categoryBtnClick {
  width: 100%;
  height: 8vw;
  margin-top: 10px;
  background-color: #909399;
}
</style>
