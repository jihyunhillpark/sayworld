<template>
  <div class="pinning-header">
    <div class="pinning-header-container">
      <div class="main-header">
        <div class="ic ic-logo" />
        <el-switch v-model="toggle" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
        <div class="secondary-navigation">
          <div class="nav-element">
            <div class="search-box">
              <el-input
                placeholder="검색"
                prefix-icon="el-icon-search"
                v-model="state.searchValue"
              ></el-input>
            </div>
          </div>
          <!-- 공지사항 알림 기능 추후 수정?! -->
          <!-- <div class="nav-element"><i class="custom-icon el-icon-message-solid"></i></div> -->
          <div class="nav-element">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-avatar class="custom-avatar" :size="medium" :src="circleUrl"></el-avatar>
              </span>
              <template #dropdown>
                <el-dropdown-menu>
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
import { reactive, computed } from "vue"
import { useStore } from "vuex"
import { useRouter } from "vue-router"

export default {
  name: 'MainHeader',
  data() {
    return {
      toggle: true,
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
    }
  },
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      searchValue: null,
      isCollapse: true,
      menuItems: computed(() => {
        const MenuItems = store.getters["root/getMenus"];
        let keys = Object.keys(MenuItems);
        let menuArray = []
        for (let i = 0; i < keys.length; ++i) {
          let menuObject = {}
          menuObject.icon = MenuItems[keys[i]].icon;
          menuObject.title = MenuItems[keys[i]].name;
          menuArray.push(menuObject)
        }
        return menuArray
      }),
      activeIndex: computed(() => store.getters["root/getActiveMenuIndex"])
    })

    const clickMyPage = () => {
      router.push({
        name: 'MyPage'
      })
    }


    const clickLogout = () => {
      if (confirm("로그아웃 하시겠습니까?")) {
        console.log('로그아웃')
        localStorage.removeItem('token')
        router.push({
          name: 'Start'
        })
      }
    }
    return { state, clickLogout }

  }


}
</script>

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
