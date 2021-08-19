<template>
  <div>
    <div class="room-btn">
      <CreateRoom />
      <el-button class="category-btn" type="info" round @click="$router.push({ name: 'RoomCategory' })">카테고리별</el-button>
    </div>
    <Conference />

  </div>
</template>

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

<style scoped>
.room-btn {
  display: flex;
  justify-content: flex-end;
  padding-right: 10px;
  padding-bottom: 10px;
}
.category-btn {
  margin-left: 5px !important;
}
</style>
