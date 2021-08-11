import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home";
import SearchResult from "@/views/SearchResult";
import Start from "@/views/Start";
import UserCategory from "@/views/UserCategory";
import RoomCategory from "@/views/RoomCategory";
import CategoryRoom from "@/views/CategoryRoom";
import CreateRoom from "@/components/webrtc/CreateRoom";
import MyPage from "@/components/mypage/MyPage";

const routes = [
  {
    path: "/register",
    name: "UserCategory",
    component: UserCategory
  },
  {
    path: "/rooms",
    name: "rooms",
    component: CreateRoom
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage
  },
  {
    path: "/home",
    name: "Home",
    component: Home
  },
  {
    path: "/start",
    name: "Start",
    component: Start
  },
  {
    path: "/search/:searchValue",
    name: "SearchResult",
    component: SearchResult
  },
  {
    path: "/category",
    name: "RoomCategory",
    component: RoomCategory
  },
  {
    path: "/category/:categoryName",
    name: "CategoryRoom",
    component: CategoryRoom
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(to => {
  console.log(to);
});

export default router;
