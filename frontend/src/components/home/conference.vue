<template>
  <el-row>
  <!-- <el-col :span="8" v-for="(o, index) in 2" :key="o" :offset="index > 0 ? 2 : 0"> -->
  <el-col :span="8" v-for="info in roomList" :key="info" cols="4" > 
    <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-image :src="src">
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
      <div class="bottom">
        <!-- 테마 다르게 나오게 -->
        <span>{{ info.movieCategory }}</span>
        <el-button type="primary" class ="button" size="mini" @click="participate(info.roomName)">입장하기</el-button>
      </div>
    </div>
  </el-card>
  </el-col>
  </el-row>
</template>
<script>
import axios from 'axios';
import _ from 'lodash';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '../webrtc/UserVideo';
import { useStore } from 'vuex';
import { useRouter } from "vue-router"

export default {
  name: 'Home',
  components: {
    UserVideo,
  },
  data(){
    const store = useStore();
    return{
      title : '',
      desc : "",
      roomList: [],
      src: 'https://ifh.cc/g/FieTKm.png'
    }
  },
  mounted(){
    const _ = require("lodash"); 
    axios({
        url: 'rooms',
        method: 'GET',
      }).then((res)=>{
        console.log("roomList start")
        this.roomList=_.sortBy(res.data,'roomId').reverse();
        //_.sortBy(this.roomList,'this.roomList.roomName');
        console.log(this.roomList)
        console.log("roomList end")
      });
      //_.sortBy(res.data,'roomId').reverse();
  },
  methods: {
    participate(rName){
      console.log(rName);
      this.$router.push({ name : 'MeetingRoom', params: { roomName: rName } })
    }
  }
}
</script>
<style>
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

</style>