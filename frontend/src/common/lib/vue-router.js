import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home";
import SearchResult from "@/views/SearchResult";
import Start from "@/views/Start";
import UserCategory from "@/views/UserCategory";
import RoomCategory from "@/views/RoomCategory";
import CategoryRoom from "@/views/CategoryRoom";
import MeetingRoom from "@/views/MeetingRoom";
import CreateRoom from "@/components/webrtc/CreateRoom";
import MyPage from "@/components/mypage/MyPage";
import CultureRegister from "../../components/mypage/CultureRegister";
import MyBlog from "../../components/mypage/MyBlog";

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
    path: "/",
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
  {
    path: "/room",
    name: "MeetingRoom",
    component: MeetingRoom
  },
  {
    path: "/culture",
    name: "CultureRegister",
    component: CultureRegister
  },
  {
    path: "/myblog",
    name: "MyBlog",
    component: MyBlog
  }


]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(to => {
  console.log(to);
});

export default router;
