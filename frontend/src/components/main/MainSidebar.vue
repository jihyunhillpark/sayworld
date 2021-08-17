<template>
  <div class="main-sidebar">
    <el-menu class="main-sidebar-container">
      <el-menu-item index="1" @click="homeSelect">
        <i class="el-icon-s-home"></i>
        <span>홈</span>
      </el-menu-item>
      <el-submenu index="2">
        <template #title>
          <i class="el-icon-mic"></i>
          <span>방 목록</span>
        </template>
        <div v-for="(room, i) in state.createdRoomHistory" :key="i">
          <el-menu-item :index="2+'-' +i">
            <div class="el-menu-item-text">{{room.roomTitle}}</div>
            <div style="float:right">
              <i class="el-icon-circle-close" @click="deleted(room.roomId)"></i>
            </div>
          </el-menu-item>
        </div>
        <!-- <el-menu-item-group title="Group One">
          <el-menu-item index="1-1" @click="deleted">
            item one
            <span><i class="el-icon-circle-close"></i></span></el-menu-item>
          <el-menu-item index="1-2">
            item one
            <span><i class="el-icon-circle-close"></i></span>
          </el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="Group Two">
          <el-menu-item index="1-3">
            item three
            <span><i class="el-icon-circle-close"></i></span>
          </el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template #title>item four</template>
          <el-menu-item index="1-4-1">item one</el-menu-item>
        </el-submenu> -->
      </el-submenu>
      <el-menu-item index="3" @click="blogSelect">
        <i class="el-icon-document"></i>
        <span>블로그</span>
      </el-menu-item>
      <el-menu-item index="4" @click="boardSelect">
        <i class="el-icon-edit-outline"></i>
        <span>게시판</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { useRouter } from 'vue-router';
import { defineComponent, onMounted, reactive } from "vue";
import { ElMessageBox,ElMessage } from 'element-plus';
import { useStore } from "vuex";

export default {
  name: "MainSidebar",
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      createdRoomHistory: undefined
    })
    const homeSelect = function () {
      router.push({ name: 'Home' })
    }
    const deleted = (roomId) => {
      ElMessageBox.confirm('OK버튼을 누르면 방이 영원히 삭제됩니다. 진행하시겠습니까?', '경고', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        // type: 'warning',
      }).then(() => {
        store.dispatch('root/deleteRoom', roomId)
        .then((res) => {
          store.dispatch('root/requestHistory')
          .then(res=>{ state.createdRoomHistory = res.data })
          ElMessage({
          type: 'success',
          message: '방이 정상적으로 삭제되었습니다!',
          })
        })
        .catch((error) => {
          ElMessage({
          type: 'error',
          message: '방 삭제에 실패했습니다...ㅠㅠ\n 새로고침 후 시도해주세요.',
          })
        })
      }).catch(() => {
        ElMessage({
          type: 'info',
          message: '방 삭제를 취소했습니다.',
        });
      });
    }

    onMounted(() => {
      store.dispatch('root/requestHistory')
      .then(res=>{
        console.log( res.data);
        state.createdRoomHistory = res.data
        console.log(state.createdRoomHistory)
        // store.commit('root/SET_HISTORY', res.data)
      })
    })


    return { state, homeSelect, deleted }
  }
}
</script>

<style scoped>
.main-sidebar {
  height: 100%;
  width: 100%;
}

.main-sidebar .main-sidebar-container {
  height: 100%;
}
.el-menu-item-text{
  overflow: hidden;
  width: 80%;
  display: inline-block;
  text-overflow: ellipsis;
}
.el-submenu .el-menu-item {
  padding: 0 15px;
}
</style>
