<template>
  <div>
    <a> 내 블로그 페이지</a>
    <br>
    <el-row :gutter="10">
      <el-button v-on:click="goEditor(0)">+</el-button>
      <el-col v-for="(blog, idx) in blogs.blogs" :span="6" :key="idx">
        <div>
          <div>{{blog.blogTitle}}</div>
          <div>{{blog.blogContent}}</div>
          <button @click="goEditor(blog)">수정</button>
          <button @click="blogDelete(blog.blogId)">삭제</button>
        </div>
<!--        <el-button>{{blog.blogTitle}}, {{blog.blogContent}}</el-button>-->
      </el-col>
    </el-row>
  </div>

</template>

<script>
  import {onUpdated, onMounted, reactive} from 'vue'
  import {useStore} from 'vuex'
  import {useRouter} from "vue-router"

  export default {
    name: "MyBlog",

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

      return {blogs, blogDelete, goEditor}
    },

    components: {
    }

  }
</script>

<style scoped>

</style>
