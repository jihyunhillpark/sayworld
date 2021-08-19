import { createRouter, createWebHistory } from "vue-router";
<<<<<<< HEAD
import Home from "@/views/home/home";
import Main from "@/views/main/Main";
import Start from "@/views/start/Start";
import UserCategory from "@/views/user/UserCategory";
import ConferencesDetail from "@/views/conferences/conference-detail";
import History from "@/views/history/history";
=======
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
>>>>>>> develop


const routes = [
  {
    path: "/register",
    name: "UserCategory",
    component: UserCategory
  },
<<<<<<< HEAD
=======
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
    path: "/myMovie",
    name: "MyMovieHistory",
    component: MyMovieHistory
  },
  {
    path: "/myBook",
    name: "MyBookHistory",
    component: MyBookHistory
  },
  {
    path: "/editor",
    name: "TextEditor",
    component: TextEditor,
    props: true
  },
  {
    path: "/roomchat",
    name: "RoomChat",
    component: RoomChat
  }

>>>>>>> develop
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.afterEach(to => {
  console.log(to);
});

export default router;
