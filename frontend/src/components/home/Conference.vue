<template>
  <el-row>
  <!-- <el-col :span="8" v-for="(o, index) in 2" :key="o" :offset="index > 0 ? 2 : 0"> -->
  <el-col :span="6" v-for="info in state.roomList" :key="info" cols="4" >
    <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-image :src="info.thumbnailUrl">
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
      <span class="title">{{ info.roomName }}</span>
      <span class="mid" v-for="keyW in info.keywords" :key="keyW">
      &nbsp; #{{keyW}}
      </span>
      <div class="bottom">
        <!-- 테마 다르게 나오게 -->
        <span v-if="curPage">{{ info.bookCategory }}</span>
        <span v-else>{{ info.movieCategory }}</span>
        <div>
        <i v-if="info.password==='Y'" class="el-icon-lock" ></i>&nbsp;
        <!-- <search v-if="info.password.length>0" style="width: 1em; height: 1em; margin-right: 8px;" /> -->
        <el-button v-if="info.password==='Y'" type="primary" class ="button" size="mini" @click="participate(info.roomName,0,info.keywords)">입장하기</el-button>
        <el-button v-else type="primary" class ="button" size="mini" @click="participate(info.roomName,1,info.keywords)">입장하기</el-button>
        </div>
      </div>
    </div>
  </el-card>
  </el-col>
  </el-row>
</template>
<script>
import axios from 'axios'
import _, { reject } from 'lodash'
import UserVideo from '../webrtc/UserVideo'
import { useStore } from 'vuex'
import { reactive, computed, watch } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'Conference',
  components: {
    UserVideo,
  },
  setup() {
    const store = useStore()
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

    const participate = (rName, lock, arrK) => {
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
              router.push({ name : 'MeetingRoom', params: { roomName: rName, keywords:arrK } })
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
        router.push({ name : 'MeetingRoom', params: { roomName: rName, keywords:arrK   } })
    }
    return { state, curPage, participate }
  }

}
</script>
<style>
.el-card {
  margin: 0 8px;
  margin-bottom: 40px;
}
.el-card .image-wrapper {
  height: 250px;
}
.el-card .title {
  font-weight: bold;
}
.el-image {
  width: 100%;
  height: 250px;
  object-fit: cover;
}
.el-image__inner {
  height: 100%;
  width: 100%;
  object-fit: cover;
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

</style>
