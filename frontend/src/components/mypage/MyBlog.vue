<template>
  <div>
    <el-page-header @back="goBack" content="나의 블로그">
    </el-page-header>
    <el-divider></el-divider>
    <el-row :gutter="10" justify="space-around">
      <el-col :span="10">
<!--        <el-button v-on:click="goEditor(0)">+</el-button>-->
        <el-card @click="goEditor(0)" class="box-card" >

          <div class="text item">
            <el-button class="button" size="100px" icon="el-icon-edit" type="text" @click="goEditor(0)">글쓰기</el-button>
          </div>

        </el-card>
      </el-col>
      <el-col v-for="(blog, idx) in blogs.blogs" :span="10" :key="idx">
        <el-card class="box-card" >
          <template #header class="headd">
            <div class="card-header">
              <div class="icony">
                <div v-if="blog.blogCategory">
                    <collection />
                </div>
                <div v-else>
                    <video-camera-filled />
                </div>
              </div>
                  {{blog.blogTitle}}
              <div>
                <el-button class="button" icon="el-icon-edit" type="text" @click="goEditor(blog)">수정</el-button>
                <el-button class="button" icon="el-icon-delete" type="text" @click="blogDelete(blog.blogId)">삭제</el-button></div>
              </div>
          </template>
          <div class="text item">
            {{blog.blogContent}}
          </div>
          <div class="bottom">
            <time class="time">
              {{ moment(blog.blogCreatedAt).format("ddd MMM DD, YYYY [at] HH:mm a") }}
            </time>
<!--            <el-button type="text" class="button">Operating</el-button>-->
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>

</template>

<script>
  import {onUpdated, onMounted, reactive} from 'vue'
  import {useStore} from 'vuex'
  import {useRouter} from "vue-router"
  import { Edit,Aim,VideoCameraFilled,Collection } from '@element-plus/icons'
  import moment from 'moment'


  export default {
    name: "MyBlog",

    created: function () {
      this.moment = moment;
    },

    setup(){
      const store = useStore()
      const blogs = reactive({
        blogs: [],
      })
      const router = useRouter()
      const create = false

      onMounted(() => {
        store.dispatch('root/blogList')
          .then((res) => {
            blogs.blogs = res.data
          })
      })

      onUpdated(() => {
        store.dispatch('root/blogList')
          .then((res) => {
            blogs.blogs = res.data
          })
      })

      function blogDelete(blogId){
        console.log(blogId)
        store.dispatch('root/deleteBlog', {blogId: blogId})
          .then(res => {
            alert("글이 삭제됐습니다.")
          })
          .catch(err =>{
            alert("글삭제에 오류발생.")
          })
        const idx = blogs.blogs.findIndex(function(item) {return item.blogId === blogId})
        blogs.blogs.splice(idx,1)
      }

      function goEditor(blog) {
        router.push({name: "TextEditor", params: blog})
      }

      function goBack() {
        router.push({name: "MyPage"})
      }

      return {blogs, blogDelete, goEditor, goBack}
    },

    components: {
      // Full name
      // [Edit.name]: Edit,
      // or Shorthanded,
      Edit,
      Aim,
      VideoCameraFilled,
      Collection,
    }

  }
</script>

<style scoped>
  .bottom {
    margin-top: 13px;
    line-height: 12px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .box-card {
    width: 420px;
  }
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .icony {
    width: 50px;
  }
</style>
