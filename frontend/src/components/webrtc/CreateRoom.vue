<template>
<div>
  <el-button type="primary" icon="el-icon-folder-add" @click="dialogFormVisible = true" circle></el-button>
  <el-dialog title="화상채팅방 생성" v-model="dialogFormVisible">
    <el-form :model="form">
    <el-form-item prop="name" label="방 이름" :label-width="formLabelWidth"
      :rules="[
        { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        {
          pattern: /^[ㄱ-ㅎ가-힣a-zA-Z0-9_-]+$/g,
          message: '한글, 영어, 숫자, -, _ 이외에는 사용할 수 없습니다.',
          trigger: 'change'
        }
      ]"
    >
      <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item prop="keyword" label="키워드" id="kTag" :label-width="formLabelWidth">
        <el-tag
        :key="tag"
        v-for="tag in dynamicTags"
        closable
        :disable-transitions="false"
        @close="handleClose(tag)"
        id="kTag">
        {{tag}}
        </el-tag>
        <el-input
        class="input-new-tag"
        v-if="inputVisible"
        v-model="inputValue"
        ref="saveTagInput"
        size="mini"
        @keyup.enter="handleInputConfirm"
        @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+ New Tag</el-button>
    </el-form-item>
    <el-form-item prop="personNum" label="인원" :label-width="formLabelWidth">
      <el-input-number v-model="num" id = "pNum" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
    </el-form-item>
    <el-form-item prop="roomTheme" label="카테고리" :label-width="formLabelWidth">
      <el-select v-if="curPage" v-model="bValue" placeholder="Book">
        <el-option
          v-for="item in b_options"
          :key="item.bookCategoryId"
          :label="item.bookCategory"
          :value="item.bookCategoryId">
        </el-option>
      </el-select>
      <el-select v-else v-model="mValue" placeholder="Movie">
        <el-option
          v-for="item in m_options"
          :key="item.movieCategoryId"
          :label="item.movieCategory"
          :value="item.movieCategoryId">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item prop="roomLock" label="방 잠금" :label-width="formLabelWidth">
      <el-checkbox v-model="checkedLock" @change="handleCheckbox"></el-checkbox>
      <el-input v-model="form.pwd" v-show="isLocked" placeholder="비밀번호를 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요.'"></el-input>
    </el-form-item>
    <!--사진 업로드  -->
    <!-- <div class="room-file-upload-wrapper">
      <div v-if="!files.length" class="room-file-upload-example-container">
          <div class="room-file-notice-item room-file-upload-button">
            <div class="image-box">
              <label for="file">일반 사진 등록</label>
              <input type="file" id="file" ref="files" @change="imageUpload" multiple />
            </div>
          </div>
      </div>
      <div v-else class="file-preview-content-container">
        <div class="file-preview-container">
          <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
            <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div>
            <img :src="file.preview" />
          </div>
        </div>
      </div>
    </div> -->
    </el-form>
    <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="formRoom()" >생성</el-button>
      <el-button @click="formClose()">취소</el-button>
    </span>
    </template>
  </el-dialog>
</div>
</template>
<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { useStore } from 'vuex';
import { useRouter } from "vue-router"

export default {
  name: 'CreateRoom',
  data() {
    const store = useStore();
    return {
      dynamicTags: ['키워드를', '입력하세요'],
      inputVisible: false,
      inputValue: '',
      checkedLock: false,
      b_options:[],
      m_options:[],
      num: 1,
      bValue:'',
      mValue:'',
      dialogFormVisible: false,
      curPage: store.state.root.curPage,
      form: {
        name: '',
        pwd: ''
      },
      mySessionId : '',
      myUserName: store.state.root.userInfo.nickname,

      isLocked: false,
      formLabelWidth: '120px',
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수

      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      block : false,
      mute : false,
      hostId : store.state.root.userInfo.userId,
    };
  },
  mounted() {
    this.$nextTick(function () {
      axios.get('category/book')
      .then((response) =>  {
        this.b_options = response.data;
      })
      .catch((error) => {
        console.log(error)
      })

      axios.get('category/movie')
      .then((response) =>  {
        this.m_options = response.data;
      })
      .catch((error) => {
        console.log(error);
      })
    })
  },
  methods: {
    fromClose1(){
      this.dialogFormVisible = false;
    },
    formClose() {
      this.dialogFormVisible = false;

      this.dynamicTags = ['키워드를', '입력하세요'];
      this.inputVisible = false
      this.inputValue = ''
      this.checkedLock = false
      this.num=1
      this.mySessionId=''
      this.bValue=''
      this.mValue=''
      //this.form.name=''
      this.isLocked=false
      this.form.pwd=''

      this.files = []
      this.filesPreview=[]
      this.uploadImageIndex=0;
    },

    handleClose(tag) {
        this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
          this.dynamicTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = '';
    },
    handleChange(value) {
        console.log(value);
    },
    handleCheckbox() {
        this.isLocked= !this.isLocked;
    },
    imageUpload() {
      console.log(this.$refs.files.files);

      // this.files = [...this.files, this.$refs.files.files];
      //하나의 배열로 넣기
      let num = -1;
      for (let i = 0; i < this.$refs.files.files.length; i++) {
        this.files = [
          ...this.files,
          //이미지 업로드
          {
              //실제 파일
              file: this.$refs.files.files[i],
              //이미지 프리뷰
              preview: URL.createObjectURL(this.$refs.files.files[i]),
              //삭제및 관리를 위한 number
              number: i
          }
        ];
        num = i;
        //이미지 업로드용 프리뷰
        // this.filesPreview = [
        //   ...this.filesPreview,
        //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
        // ];
      }
      this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
      console.log(this.files);
    },
    fileDeleteButton(e) {
      const name = e.target.getAttribute('name');
      this.files = this.files.filter(data => data.number !== Number(name));
    },
    //방생성 API
    formRoom(){
      console.log(this.files[0]);
      axios({
        method:"POST",
        url: "rooms",
        data:{
          roomName : this.form.name,
          hostId: this.hostId,
          keywords : this.dynamicTags,
          limit: this.num,
          bookCategoryId:this.bValue,
          movieCategoryId: this.mValue,
          //"roomInviteCode": String,
          //password: String,
          thumbnailUrl: "idk",//window.URL.createObjectURL(this.files[0]),
          //"email": email.value,
          password: this.form.pwd,
          sessionId: this.form.name,
        }
      })
      .then(() => {
        this.$router.push({ name : 'MeetingRoom', params: { roomName: this.form.name }  })
        this.fromClose1()
      })
      .catch((err) => {
        if (err.response.status === 500) {
          alert('이미 존재하는 방 제목입니다. 다른 방 제목을 입력해주세요.')
        }
      });
    },
    //방삭제 API
    deleteRoom(){
        console.log("deleteRoom");
        // axios.delete("rooms").then(function(response){
        //     console.log(response);
        // }).catch(function(ex){
        //     throw new Error(ex)
        // });

        // axios.delete('/rooms/val') //val = 특정 값
        // .then(res => {
        //     console.log(res);
        // })

        // axios.delete(`rooms${rId}`)
        // .then((res)=>{
        //         console.log(res);
        //     }).catch(error=>{
        //         console.log(error);
        //         throw new Error(error);
        //     });
        //store.dispatch('root/requestRoomInfo', roomInfo)
    },

  }
}
</script>
<style>
@import "./CreateRoom.css";
</style>
