<template>
  <el-dialog custom-class="signup-dialog" title="회원가입" v-model="state.dialogVisible" @close="handleClose">
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
        <el-radio-group v-model="state.form.default_page">
          <el-radio label="B">책</el-radio>
          <el-radio label="M">영화</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" :disabled="state.signupButton" @click="clickSignup">가입하기</el-button>
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
    }
  },
  setup(props, { emit }) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)
    const state = reactive({
      signupButton: true,
      form: {
        email: '',
        password: '',
        passwordConfirmation: '',
        nickname: '',
        age: '',
        gender: '',
        default_page: '',
        align: 'left',
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
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(signupForm.value)
    })

    const checkButton = function () {
      signupForm.value.validate((valid) => {
        if (valid) {
          state.signupButton = false
        } else {
          state.signupButton = true
        }})
    }

    const clickSignup = function () {
      // 회원가입 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestSignup',
            { email: state.form.email,
              password: state.form.password,
              nickname: state.form.nickname,
              age: state.form.age,
              gender: state.form.gender,
              default_page: state.form.default_page,
          })
          .then(function ( ) {
            handleClose()
            console.log('회원 가입이 완료되었습니다.')
            store.dispatch('root/requestLogin', { email: state.form.email, password: state.form.password })
            .then(function (result) {
              localStorage.setItem('token', result.data.accessToken)
              router.push({ name: 'Main' })
            })
            .catch(function (err) {
              alert(err)
            })
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
      state.form.email = ''
      state.form.password = ''
      state.form.passwordConfirmation = ''
      state.form.nickname = ''
      state.form.age = ''
      state.form.gender = ''
      state.form.default_page = 'B'
      emit('closeSignupDialog')
    }

    return { signupForm, state, clickSignup, handleClose, checkButton }
  }
}
</script>

<style>
.signup-dialog {
  width: 430px !important;
  height: 575;
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
</style>
