<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose1">
    <div v-show="state.pageNum === 2">
      <el-form :model="state.form" :rules="state.rules" ref="signupForm" :label-position="state.form.align"  @change="checkButton">
        <el-form-item prop="email" label="이메일" :label-width="state.formLabelWidth" >
          <el-input v-model="state.form.email" autocomplete="off"></el-input>
          <!-- <el-button type="primary" size="small" @click="checkDuplicate">중복 확인</el-button> -->
        </el-form-item>
        <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="passwordConfirmation" label="비밀번호 확인" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.passwordConfirmation" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item prop="nickname" label="닉네임" :label-width="state.formLabelWidth">
          <el-input v-model="state.form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="나이" :label-width="state.formLabelWidth">
          <el-radio-group v-model="state.form.age">
            <el-radio label="10">10대</el-radio>
            <el-radio label="20">20대</el-radio>
            <el-radio label="30">30대</el-radio><br>
            <el-radio label="40">40대</el-radio>
            <el-radio label="50">50대</el-radio>
            <el-radio label="60">60대 이상</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="성별" :label-width="state.formLabelWidth">
          <el-radio-group v-model="state.form.gender">
            <el-radio label="M">남성</el-radio>
            <el-radio label="F">여성</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="첫 페이지 설정" :label-width="state.formLabelWidth">
          <el-radio-group v-model="state.form.defaultPage">
            <el-radio label="B">책</el-radio>
            <el-radio label="M">영화</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </div>
    <div v-show="state.pageNum === 1">
      <h3>관심있는 책 장르 3가지를 골라주세요.</h3>
      <!-- progress bar 추가 -->
      <div>
        <el-row :gutter="12">
        <!-- 클릭했을 때 색상 변경 + 완료 버튼 디자인 수정 -->
        <el-col v-for="(category, idx) in state.bookCategories" :span="6" :key="idx">
          <el-button class="categoryBtn" type="info" @click="clickCategory(category.bookCategoryId)" plain>{{ category.bookCategory }}</el-button>
        </el-col>
        </el-row>
      </div>
    </div>
    <div v-show="!state.pageNum">
      <h3>관심있는 영화 장르 3가지를 골라주세요.</h3>
      <!-- progress bar 추가 -->
      <div>
        <el-row :gutter="12">
        <!-- 클릭했을 때 색상 변경 + 완료 버튼 디자인 수정 -->
        <el-col v-for="(category, idx) in state.movieCategories" :span="6" :key="idx">
          <el-button class="categoryBtn" type="info" @click="clickCategory(category.movieCategoryId)" plain>{{ category.movieCategory }}</el-button>
        </el-col>
        </el-row>
      </div>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button v-show="state.pageNum === 2" type="primary" :disabled="state.signupButton" @click="clickSignup">다음으로</el-button>
        <el-button v-show="state.pageNum === 1" type="primary" :disabled="state.selected.length < 3" @click="clickNext">다음으로</el-button>
        <el-button v-show="!state.pageNum" type="primary" :disabled="state.selected.length < 3" @click="[clickNext(), handleClose()]">가입하기</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'

export default {
  name: 'SignupDialog',
  props: {
    open: {
      type: Boolean,
      default: false
    },
    bookCategories: {
      type: Array,
    },
    movieCategories: {
      type: Array,
    },
  },
  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)
    const state = reactive({
      signupButton: true,
      page: 2,
      selected: [],
      isClick: false,
      form: {
        email: '',
        password: '',
        passwordConfirmation: '',
        nickname: '',
        age: '',
        gender: '',
        defaultPage: '',
        align: 'left'
      },
      rules: {
        email: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'change' },
          // { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'change' },
          {
            pattern: /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i,
            message: '올바른 형식으로 입력해주세요.',
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'change' },
          { min: 9, message: '최소 9 글자를 입력해야 합니다.', trigger: 'change' },
          { max: 16, message: '최대 16 글자까지 입력 가능합니다.', trigger: 'change'},
          {
            pattern: /(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9])/,
            message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.',
            trigger: 'change'
          }
        ],
        passwordConfirmation: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'change' },
          { min: 9, message: '최소 9 글자를 입력해야 합니다.', trigger: 'change' },
          { max: 16, message: '최대 16 글자까지 입력 가능합니다.', trigger: 'change'},
          {
            pattern: /(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9])/,
            message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.',
            trigger: 'change'
          },
          { validator(rule, value, callback){
            return state.form.password === value
          }, message:'입력한 비밀번호와 일치하지 않습니다.', trigger: 'change' }
        ],
        nickname: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'change' },
          { min: 3, message: '최소 3글자를 입력해야 합니다.', trigger: 'change' },
          { max: 9, message: '최대 9자까지 입력 가능합니다.', trigger: 'change' },
        ],
      },
      dialogVisible: computed(() => props.open),
      bookCategories: computed(() => props.bookCategories),
      movieCategories: computed(() => props.movieCategories),
      pageNum: computed(() => state.page),
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // store.dispatch('root/requestCategory', { category: 'book' })
      // .then((res) => {
      //   state.bookCategories = res.data
      // })
      // store.dispatch('root/requestCategory', { category: 'movie' })
      // .then((res) => {
      //   state.movieCategories = res.data
      // })
    })

    const checkButton = function () {
      signupForm.value.validate((valid) => {
        if (valid) {
          state.signupButton = false
        } else {
          state.signupButton = true
        }})
    }

    const clickCategory = function (categoryId) {
      if (state.selected.indexOf(categoryId) === -1) {
        state.selected.push(categoryId)
        state.isClick = true
      } else {
        state.selected.splice(state.selected.indexOf(categoryId), 1)
        state.isClick = false
      }
    }

    const clickNext = function () {
      console.log(state.selected)
      console.log(state.pageNum)
      if (state.pageNum === 1 || !state.pageNum) {
        console.log('실행?')
        const payload = []
        for (var category of state.selected) {
          payload.push({ categoryId: category, movieOrBook: Number(!state.pageNum) })
        }
        store.dispatch('root/sendCategory', { category: payload, email: state.form.email })
        .then((res) => {
          state.selected = []
          state.page = state.page - 1
        })
        .catch((err) => {
          console.log(err)
        })
      }
    }

    const clickSignup = function () {
      state.page = state.page - 1
      // 회원가입 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestSignup',
            { email: state.form.email,
              password: state.form.password,
              nickname: state.form.nickname,
              age: Number(state.form.age),
              gender: state.form.gender,
              defaultPage: state.form.defaultPage
          })
          .then(function (res) {
            // handleClose()
            // alert('회원 가입이 완료되었습니다.')
          })
          .catch(function (err) {
            console.log(err)
            alert('회원 가입에 실패하였습니다.')
          })
        } else {
          alert('Validate error!')
        }
      })
    }

    const handleClose = function () {
      console.log('닫기')
      state.form.email = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.nickname = ''
      state.form.age = ''
      state.form.gender = ''
      state.form.defaultPage = ''
      state.page = 2
      state.selected = []
      emit('closeSignupDialog')
      alert('회원 가입이 완료되었습니다.')
    }

    const handleClose1 = function () {
      state.form.email = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.nickname = ''
      state.form.age = ''
      state.form.gender = ''
      state.form.defaultPage = ''
      state.page = 2
      state.selected = []
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, handleClose, handleClose1, checkButton, clickNext, clickCategory }
  }
}
</script>

<style>
.signup-dialog {
  /* width: 430px !important; */
  width: 60vw !important;
  /* height: 575px; */
  height: 80vh !important;
  margin-top: 10vh !important;
  margin-bottom: 5vh !important;
}
.signup-dialog .el-dialog__headerbtn {
  float: right;
}
.signup-dialog .el-form-item__content {
  margin-left: 0 !important;
  float: right;
  width: 200px;
  display: inline-block;
}
.signup-dialog .el-form-item {
  margin-bottom: 20px;
}
.signup-dialog .el-form-item__error {
  font-size: 12px;
  color: red;
}
.signup-dialog .el-input__suffix {
  display: none;
}
.signup-dialog .el-dialog__footer {
  margin: 0 calc(50% - 80px);
  padding-top: 0;
  display: inline-block;
}
.signup-dialog .dialog-footer .el-button {
  width: 120px;
}
.categoryBtn {
  width: 100%;
  margin-top: 10px;
  height: 8vw;
  background-color: #f4f4f5;
  text-align: center;
}
</style>
