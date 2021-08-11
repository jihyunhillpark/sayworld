<template>
  <div>
    <!-- 세부 위치 수정 추후에 -->
    <CreateRoom />
    <p>{{ $store.state.root.userInfo.nickname }}</p>
    <div>
    </div>
    <div>
      <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
        <li
          v-for="i in state.count"
          @click="clickConference(i)"
          class="infinite-list-item"
          :key="i"
        >
          <conference />
        </li>
      </ul>
    </div>
  </div>

</template>
<style>
.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}

@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
</style>
<script>
import Conference from "@/components/home/conference"
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
      count: 12
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
