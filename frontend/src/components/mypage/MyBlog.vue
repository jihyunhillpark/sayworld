<template>
  <div>
    <a> 내 블로그 페이지</a>

  </div>

</template>

<script>
  import {onMounted, reactive} from 'vue'
  import {useStore} from 'vuex'

  export default {
    name: "MyBlog",

    setup(){
      const store = useStore()
      const blogList = reactive({
        blogs: [],
      })

      onMounted(() => {
        store.dispatch('root/blogList')
          .then()
      })
    }

  }
</script>

<style scoped>

</style>
