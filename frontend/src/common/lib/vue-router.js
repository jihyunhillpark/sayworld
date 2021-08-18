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
import MyMovieHistory from "../../components/mypage/MyMovieHistory";
import MyBookHistory from "../../components/mypage/MyBookHistory";
import CultureRegister from "../../components/mypage/CultureRegister";
import MyBlog from "../../components/mypage/MyBlog";
import TextEditor from "../../components/mypage/TextEditor"
import RoomChat from "@/views/RoomChat";


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
    path: "/room/:roomName",
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
  },
  {
<<<<<<< HEAD
    path: "/myMovie",
    name: "MyMovieHistory",
    component: MyMovieHistory
  },
  {
    path: "/myBook",
    name: "MyBookHistory",
    component: MyBookHistory
=======
    path: "/editor",
    name: "TextEditor",
    component: TextEditor,
    props: true
>>>>>>> 17d0520d6b2eaa3f1034af64cbd5b15353ee02a5
  },
  {
    path: "/roomchat",
    name: "RoomChat",
    component: RoomChat
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
