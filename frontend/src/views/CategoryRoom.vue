<template>
  <div class="category-header">
    <div class="category-name">
      <h4>{{ $route.params.categoryName }}</h4>
    </div>
    <el-button class="category-btn" type="info" round @click="$router.push({ name: 'RoomCategory' })">카테고리별</el-button>
  </div>
  <div v-show="!searchCategory.length" class="no-search-wrapper">
    <h3>해당 카테고리의 방이 없습니다.</h3>
  </div>
  <el-row>
    <el-col :span="6" v-for="(room, index) of searchCategory" :key="index" cols="4" >
    <!-- <el-col :span="6" v-for="(room, index) of searchKeyword" :key="index"> -->
      <el-card :body-style="{ padding: '0px' }">
      <!-- <el-card class="box-card"> -->
        <div class="image-wrapper">
        <el-skeleton style="width: 100%">
          <template #template>
            <el-image :src="state.src">
              <template #placeholder>
                <div class="image-slot">
                  Loading<span class="dot">...</span>
                </div>
              </template>
            </el-image>
            <!-- <el-skeleton-item variant="image" style="width: 100%; height: 190px" /> -->
          </template>
        </el-skeleton>
        </div>
        <div style="text-align: left; padding: 14px;">
          <span class="title">{{ room.roomName }}</span>
          <span class="mid" v-for="keyW in room.keywords" :key="keyW">
            &nbsp; #{{keyW}}
          </span>
          <div class="bottom">
            <!-- 테마 다르게 나오게 -->
            <span v-if="curPage">{{ room.bookCategory }}</span>
            <span v-else>{{ room.movieCategory }}</span>
            <div>
            <i v-if="room.password==='Y'" class="el-icon-lock"></i>&nbsp;
            <!-- <search v-if="info.password.length>0" style="width: 1em; height: 1em; margin-right: 8px;" /> -->
            <el-button v-if="room.password==='Y'" type="primary" class ="button" size="mini" @click="participate(room.roomName,0)">입장하기</el-button>
            <el-button v-else type="primary" class ="button" size="mini" @click="participate(room.roomName,1)">입장하기</el-button>
            </div>
          </div>
        </div>
        <!-- <p>{{ room.roomName }}</p> -->
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import axios from 'axios'
import _, { reject } from 'lodash'
import UserVideo from '../components/webrtc/UserVideo'
import { useStore } from 'vuex'
import { reactive, computed, watch } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: "CategoryRoom",
  setup() {
    const store = useStore()
    const searchCategory = computed(() => store.state.root.searchCategory)
    const router = useRouter()
    const state = reactive({
      title : '',
      roomList: [],
      src: 'https://ifh.cc/g/FieTKm.png'

    })
    const curPage = computed({
      get: () => store.state.root.curPage,
    })
    watch (curPage, (curValue, oldValue) => {
      console.log(curValue)

      axios({
          url: `rooms/page/${curValue}`,
          method: 'GET',
        })
        .then((res) => {
          state.roomList = _.sortBy(res.data, 'roomId').reverse();
        });
    }, { immediate:true })

    const participate = (rName, lock) => {
      var allow = true;
      if (lock == 0){
        allow = false;
        var pwdInput = prompt("비밀번호를 입력하세요");
        //지현이가해준거ㅓ........맞으면 200, 틀리면 403
        axios({
          url: `rooms/check/${rName}`,
          method: 'POST',
          data:{
            "password" : pwdInput
          }
        })
        .then(response=>{
          if(response.status==200){
              console.log(response),
              router.push({ name : 'MeetingRoom', params: { roomName: rName } })
          }
        }
        )
        .catch(e =>{
          console.log('error : ', e)
          alert("틀렸습니다.")
          reject(response)
        })
      }
      if(allow)
        router.push({ name : 'MeetingRoom', params: { roomName: rName } })
    }

    return { searchCategory, state, curPage, participate }
  }
}
</script>

<style scoped>
.el-card {
  margin: 0 8px;
  margin-bottom: 40px;
}
.el-card .image-wrapper {
  width: 100%;
  height: 250px;
}
.el-card .title {
  font-weight: bold;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.button {
  padding: 0;
  min-height: auto;
}
.image {
  width: 100%;
  display: block;
}
.category-header {
  display: flex;
  /* justify-content: space-between; */
  justify-content: flex-end;
  align-items: center;
  text-align: center;
  margin-bottom: 15px;
}
h4 {
  margin-bottom: 0px !important;
}
.category-btn {
  margin-right: 20px !important;
}
.category-name {
  margin-left: 10px;
  display: flex;
  margin-right: 15px;
}
.no-search-wrapper {
  min-height: 50vh;
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
}
</style>

