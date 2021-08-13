<template>
  <div>
    <a> 내 블로그 페이지</a>
    <el-button type="primary">Primary</el-button>
  </div>

</template>

<script>
  import {onMounted, reactive} from 'vue'
  import {useStore} from 'vuex'

  export default {
    name: "MyBlog",

    setup(){
      const store = useStore()
      const blogs = reactive({
        blogs: [],
      })

      onMounted(() => {
        alert("blog")
        store.dispatch('root/blogList')
          .then((res) => {
            alert("blogsss")
            console.log("here blog list!!!!!!!!")
            console.log(res.data)
            blogs.blogs = res.data
          })
      })


      return blogs
    }

  }
</script>

<style scoped>

</style>
