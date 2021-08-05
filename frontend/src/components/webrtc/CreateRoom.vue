<template>
  <div>
    <el-button type="primary" icon="el-icon-folder-add" @click="dialogFormVisible=true" circle></el-button>
    <el-dialog title="화상채팅방 생성" v-model="dialogFormVisible">
      <el-form :model="form">
      <el-form-item prop="roomName" label="방 이름" :label-width="formLabelWidth">
        <el-input class="room-name" v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item prop="keyword" label="키워드" :label-width="formLabelWidth">
          <el-tag
          :key="tag"
          v-for="tag in dynamicTags"
          closable
          :disable-transitions="false"
          @close="handleClose(tag)">
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
      <el-form-item prop="roomName" label="인원" :label-width="formLabelWidth">
          <el-input-number v-model="num" controls-position="right" @change="handleChange" :min="1" :max="10"></el-input-number>
      </el-form-item>
      <el-form-item prop="roomTheme" label="테마" :label-width="formLabelWidth">
          <el-radio v-model="radio" label="book">Book</el-radio>
          <el-radio v-model="radio" label="movie">Movie</el-radio>
      </el-form-item>
      <el-form-item prop="roomLock" label="방" :label-width="formLabelWidth">
          <el-row>
          <el-checkbox v-model="checked" @change="handleCheckbox">잠금</el-checkbox>
          <el-input v-show="isLocked" placeholder="비밀번호를 입력하세요." onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요.'"></el-input>
          </el-row>
      </el-form-item>
      <el-form-item>
          <!--<input type="file" id="file" ref="files" @change="imageUpload" multiple />-->
      </el-form-item>
      <div class="image-box">
          <label for="file">채팅방 사진 등록</label>
          <input type="file" id="file" ref="files" @change="imageUpload" multiple />
      </div>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
          <el-button type="primary" @click="dialogFormVisible = false">생성</el-button>
          <el-button @click="dialogFormVisible = false">취소</el-button>
      </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'CreateRoom',
  data() {
    return {
      dynamicTags: ['키워드를', '입력하세요'],
      inputVisible: false,
      inputValue: '',
      checked: false,
      radio: 'book',
      num: 1,
      dialogFormVisible: false,
      form: {
          name: ''
      },
      isLocked: false,
      formLabelWidth: '120px',
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0 // 이미지 업로드를 위한 변수
    }
  },
  methods: {
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
      this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
          this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
    },
    handleChange(value) {
      console.log(value)
    },
    handleCheckbox(value) {
      this.isLocked= !this.isLocked;
    },
  }
}
</script>
<style>
.room-name {
  width: 80%;
}

.el-tag + .el-tag {
    margin-left: 10px;
}
.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}
.image-box {
    margin-top: 10px;
    padding-bottom: 5px;
    text-align: center;
}

.image-box input[type='file'] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}

.image-box label {
    display: inline-block;
    padding: 10px 20px;
    background-color: #4F7178;
    color: #fff;
    vertical-align: middle;
    font-size: 15px;
    cursor: pointer;
    border-radius: 5px;
}
</style>

