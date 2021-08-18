<template>
  <div>
    <!-- 세부 위치 수정 추후에 -->
    <CreateRoom />
    <el-button type="info" round @click="$router.push({ name: 'RoomCategory' })">카테고리별</el-button>
    <Conference />

  </div>
</template>
<style>

</style>
<script>
import Conference from "@/components/home/Conference"
import CreateRoom from "@/components/webrtc/CreateRoom"
import { reactive } from "vue"
import { useRouter } from "vue-router"

export default {
  name: "Home",
  components: {
    Conference,
    CreateRoom,
  },
  setup() {
    const router = useRouter()

    const state = reactive({
      count: 12,
    })

    const load = function() {
      state.count += 4;
    }

    const clickConference = function(id) {
      router.push({
        name: "conference-detail",
        params: {
          conferenceId: id
        }
      })
    }
    return { state, load, clickConference };
  }
};
</script>
