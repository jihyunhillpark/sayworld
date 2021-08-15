<template>
  <div class="pinning-header">
    <div class="pinning-header-container">
      <div class="main-header">
        <div class="ic ic-logo" @click="$router.push({ name: 'Home' })"/>
        <!-- <el-switch v-model="state.toggle" active-color="#13ce66" inactive-color="#ff4949" @click="changeToggle"></el-switch> -->
        <input class="tgl tgl-flip" id="cb5" type="checkbox" v-model="checked" @click="changeToggle"/>
        <label class="tgl-btn" data-tg-off="MOVIE" data-tg-on="BOOK" for="cb5"></label>

        <div class="secondary-navigation">
          <div class="nav-element">
            <div class="search-box">
              <el-input
                placeholder="검색"
                prefix-icon="el-icon-search"
                v-model="state.searchValue"
                @keypress.enter="searchRoom"
              ></el-input>
            </div>
          </div>
          <!-- 공지사항 알림 기능 추후 수정?! -->
          <!-- <div class="nav-element"><i class="custom-icon el-icon-message-solid"></i></div> -->
          <div class="nav-element">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-avatar class="custom-avatar" :size="medium" :src="state.circleUrl"></el-avatar>
              </span>
              <template #dropdown >
                <el-dropdown-menu >
                  <el-dropdown-item icon="el-icon-user-solid" @click="clickMyPage">마이페이지</el-dropdown-item>
                  <el-dropdown-item icon="el-icon-moon" @click="clickLogout">로그아웃</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, computed, watch } from "vue"
import { useStore } from "vuex"
import { useRouter } from "vue-router"

export default {
  name: 'MainHeader',
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      searchValue: null,
      isCollapse: true,
      title: 'title',
      keyword: 'keyword',
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
    })

    const checked = computed({
      get: () => Boolean(store.state.root.curPage),
      set (val) {
        store.commit('root/SET_CURRENT_PAGE', Number(val))
      }
    })

    const changeToggle = () => {
      if (checked) {
        store.commit('root/SET_CURRENT_PAGE', 0)
      } else {
        store.commit('root/SET_CURRENT_PAGE', 1)
      }
    }

    const clickMyPage = () => {
      router.push({ name: "MyPage" })
    }

    const clickLogout = () => {
      if (confirm("로그아웃 하시겠습니까?")) {
        localStorage.clear()
        window.location.reload()
      }
    }

    const searchRoom = () => {
      if (state.searchValue) {
        store.dispatch('root/searchRoom', {searchType: 'title', searchValue: state.searchValue})
        store.dispatch('root/searchRoom', {searchType: 'keyword', searchValue: state.searchValue})
      } else {
        // 검색어 없을 때도 페이지 이동하는게 나은지 아니면 alert 띄우는게 나은지?
        alert("검색어를 입력하세요.")
      }
    }
    return { state, checked, clickLogout, clickMyPage, searchRoom, changeToggle }
  },




  }



</script>

<style lang="scss">
.tgl {
  display: none;

  // add default box-sizing for this scope
  &,
  &:after,
  &:before,
  & *,
  & *:after,
  & *:before,
  & + .tgl-btn {
    box-sizing: border-box;
    &::selection {
      background: none;
    }
  }

  + .tgl-btn {
    outline: 0;
    display: block;
    width: 4em;
    height: 2em;
    position: relative;
    cursor: pointer;
    user-select: none;
    &:after,
    &:before {
      position: relative;
      display: block;
      content: "";
      width: 50%;
      height: 100%;
    }

    &:after {
      left: 0;
    }

    &:before {
      display: none;
    }
  }

  &:checked + .tgl-btn:after {
    left: 50%;
  }
}
.tgl-flip {
  + .tgl-btn {
    padding: 2px;
    transition: all 0.2s ease;
    font-family: sans-serif;
    perspective: 100px;
    &:after,
    &:before {
      display: inline-block;
      transition: all 0.4s ease;
      width: 100%;
      text-align: center;
      position: absolute;
      line-height: 2em;
      font-weight: bold;
      color: #fff;
      position: absolute;
      top: 0;
      left: 0;
      backface-visibility: hidden;
      border-radius: 4px;
    }

    &:after {
      content: attr(data-tg-on);
      background: #02c66f;
      transform: rotateY(-180deg);
    }

    &:before {
      background: #ff3a19;
      content: attr(data-tg-off);
    }

    &:active:before {
      transform: rotateY(-20deg);
    }
  }

  &:checked + .tgl-btn {
    &:before {
      transform: rotateY(180deg);
    }

    &:after {
      transform: rotateY(0);
      left: 0;
      background: #7fc6a6;
    }

    &:active:after {
      transform: rotateY(20deg);
    }
  }
}
</style>
<style>
.pinning-header {
  height: 70px;
}
.pinning-header .pinning-header-container {
  position: relative;
  background: transparent;
  top: 0px;
  left: 0;
  right: 0;
  z-index: 1;
}
.pinning-header .pinning-header-container .main-header {
  z-index: 2;
  height: 70px;
}
.pinning-header .main-header {
  position: relative;
  display: flex;
  align-items: center;
  padding: 0 4%;
}
.main-header .ic.ic-logo {
  width: 200px;
  height: 50px;
  background-size: contain;
  background-repeat: no-repeat;
  background-image: url("../../assets/images/sayworld-logo.png");
  display: inline-block;
  vertical-align: middle;
  cursor: pointer;
  margin-right: 15px;
}
.pinning-header .secondary-navigation {
  display: flex;
  align-items: center;
  flex-grow: 1;
  justify-content: flex-end;
  position: absolute;
  right: 4%;
  height: 100%;
}
.nav-element {
  align-items: center;
  vertical-align: middle;
}
.pinning-header .secondary-navigation .nav-element:not(:last-child) {
  margin-right: 15px;
}
.custom-icon {
  font-size: 40px;
}
.el-dropdown-link {
  cursor: pointer;
}
</style>
