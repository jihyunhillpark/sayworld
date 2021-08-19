<template>
  <div>
    <el-page-header @back="goBack" content="글 작성">
    </el-page-header>
    <el-divider></el-divider>
    <el-input type="text" placeholder="제목을 입력하세요" v-model="blogTitle">
      <template #prepend>제목 : </template>
    </el-input>
    <QuillEditor :options="options" theme="snow" v-model:content="editor._content" @update:content="onEditorChange($event)"/>
  </div>
  <el-row class="bottom">
    <el-col :span="6">
<!--      <el-switch-->
<!--        v-model="lockButton.value"-->
<!--        active-text="잠금"-->
<!--        inactive-text="잠금해제"-->
<!--      ></el-switch>-->
      <el-switch
        style="display: block"
        v-model="cateButton.value"
        active-color="#7fc6a6"
        inactive-color="#f1a4a4"
        active-text="책"
        inactive-text="영화"
      ></el-switch>
    </el-col>
    <el-col :span="6" :offset="12">
      <el-button type="text" v-on:click="blogPost">저장</el-button>
    </el-col>

  </el-row>

</template>
<script>
  import {reactive} from 'vue'
  import {useStore} from 'vuex'
  import {useRouter} from "vue-router"
  import { QuillEditor } from '@vueup/vue-quill'

  export default {
    name: "TextEditor",
    props: {
      blogId: {
        type: Number,
        default: 0
      },
      blogTitle: {
        type: String,
        default: ""
      },
      blogContent: {
        type: String,
        default: ""
      },
      blogCategory: {
        type: String,
        default: 0
      },
      userEmail: {
        type: String,
        default: ""
      },
      blogCreatedAt: {
        type: String,
        default: ""
      },
      blogLock: {
        type: String,
        default: 0
      },
    },
    setup(props) {
      let lockTF
      if(props.blogLock == 1){
        lockTF = true
      }else {
        lockTF = false
      }
      let cateTF
      if(props.blogCategory == 1){
        cateTF = true
      }else {
        cateTF = false
      }
      const store = useStore()
      const blogs = reactive({
        blogs: [],
      })

      const editor = reactive({
        content:"",
        _content: {
          ops: [
            { insert: props.blogContent }
          ]
        }
      })

      const options = reactive({
        debug: 'info',
        placeholder: '내용을 작성하세요',
      })

      const lockButton = reactive({
        value: lockTF
      })
      const cateButton = reactive({
        value: cateTF
      })

      const router = useRouter()



      function blogPost(){
        if(props.blogId != 0){
          blogs.blogs.blogTitle = props.blogTitle
          blogs.blogs.blogContent = editor.content
          if(cateButton.value){
            blogs.blogs.blogCategory = 1
          }else {
            blogs.blogs.blogCategory = 0
          }
          if(lockButton.value){
            blogs.blogs.blogLock = 1
          }else {
            blogs.blogs.blogLock = 0
          }
          blogs.blogs.blogId = props.blogId

          store.dispatch('root/putBlog', blogs.blogs)
            .then(res => {
              alert("글이 수정됐습니다.")
              router.push({name: "MyBlog"})
            })
            .catch(err =>{
              alert("글 수정에서 오류발생.")
              router.push({name: "MyBlog"})
            })
        }
        else {
          blogs.blogs.blogTitle = props.blogTitle
          blogs.blogs.blogContent = editor.content
          if(cateButton.value){
            blogs.blogs.blogCategory = 1
          }else {
            blogs.blogs.blogCategory = 0
          }
          if(lockButton.value){
            blogs.blogs.blogLock = 1
          }else {
            blogs.blogs.blogLock = 0
          }

          store.dispatch('root/postBlog', blogs.blogs)
            .then(res => {
              alert("글이 등록됐습니다.")
              router.push({name: "MyBlog"})
            })
            .catch(err =>{
              alert("글 등록에서 오류발생.")
              router.push({name: "MyBlog"})
            })
        }
      }

      // const onEditorChange = ({ quill, html, text }) => {
      //   console.log("editor change!", quill, html, text);
      //   editor.content = html
      // };
      const onEditorChange = (delta) => {
        // console.log("editor change!", delta.ops[0].insert);
        editor.content = delta.ops[0].insert
      };

      const onEditorFocus = (quill) => {
        console.log("editor focus!", quill);
      };

      function goBack() {
        router.push({name: "MyBlog"})
      }

      return {goBack, options, blogPost, editor,cateButton,lockButton, onEditorChange, onEditorFocus}
    },

    components: {
      QuillEditor
    },

  }
</script>
<style scoped>
  @import "~@vueup/vue-quill/dist/vue-quill.snow.css";

  .bottom {

    align-items: center;
  }
</style>
