<template>
  <div>
    <div v-if="searchKeyword.length || searchTitle.length">
      <h3>{{ $route.params.searchValue }} 검색 결과</h3>
      <el-tabs type="border-card">
        <el-tab-pane label="방 제목">
          <h3 v-show="!searchTitle">방 제목에 해당하는 결과가 없습니다.</h3>
          <el-row v-if="searchTitle">
            <el-col :span="6" v-for="(room, index) of searchTitle" :key="index">
              <el-card class="box-card">
                <p>{{ room.roomTitle }}</p>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="키워드">
          <h3 v-show="!searchKeyword">키워드에 해당하는 결과가 없습니다.</h3>
          <el-row>
            <el-col :span="6" v-for="(room, index) of searchKeyword" :key="index">
              <el-card class="box-card">
                <p>{{ room.roomTitle }}</p>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div v-else>
      <h3>검색 결과가 없습니다. 다시 검색해주세요.</h3>
    </div>
  </div>
</template>

<script>
import { useStore } from 'vuex'

export default {
  name: "SearchResult",
  setup() {
    const store = useStore()
    return {
      searchKeyword: store.state.root.searchKeyword,
      searchTitle: store.state.root.searchTitle,
    }
  }
}
</script>

<style>

</style>
