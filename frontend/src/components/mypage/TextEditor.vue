<template>
  <div>
    <p>{{blogId}}</p>
    <input type="text" placeholder="제목을 입력하세요" v-model="blogTitle"/>
    <QuillEditor theme="snow" v-model:content="editor._content" @update:content="onEditorChange($event)"/>
  </div>
  <el-button v-on:click="blogPost">저장</el-button>
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
        default: "제목을 입력하세요"
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
      const store = useStore()
      const blogs = reactive({
        blogs: [],
      })
      const editor = reactive({
        content:"props.blogId",
        _content: {
          ops: [
            { insert: props.blogContent }
          ]
        }
      })

      const router = useRouter()



      function blogPost(){
        if(props.blogId != 0){
          blogs.blogs.blogTitle = props.blogTitle
          blogs.blogs.blogContent = editor.content
          blogs.blogs.blogCategory = 1
          blogs.blogs.blogLock = 0
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
          blogs.blogs.blogCategory = 1
          blogs.blogs.blogLock = 0

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

      return {blogPost, editor, onEditorChange, onEditorFocus}
    },

    components: {
      QuillEditor
    },

  }
</script>
<style scoped>
  @import "~@vueup/vue-quill/dist/vue-quill.snow.css";

</style>
