<template>
  <el-card class="login" shadow="hover">
    <h2>로그인</h2>
    <el-form class="login-form" :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align" @change="checkButton">
      <el-form-item prop="email">
        <el-input v-model="state.form.email" placeholder="이메일을 입력하세요." prefix-icon="el-icon-message"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input v-model="state.form.password" placeholder="비밀번호를 입력하세요." type="password" prefix-icon="el-icon-lock"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="login-button" type="primary" :disabled="state.loginButton" @click="clickLogin">로그인</el-button>
      </el-form-item>
      <span>계정이 없으신가요? <a class="signup-link" @click="clickSignup">가입하기</a></span>
    </el-form>
  </el-card>
</template>

<script>
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'Login',
  setup(props, { emit }) {
    const store = useStore()
    const router = useRouter()
    const loginForm = ref(null)
    const state = reactive({
      loginButton: true,
      form: {
        email: '',
        password: '',
        nickname: '',
        align: 'left'
      },
      rules: {
        email: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          // { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'change' },
          {
            pattern: /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i,
            message: '올바른 형식으로 입력해주세요.',
            trigger: 'blur'
          }
        ],
        password: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9 글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16 글자까지 입력 가능합니다.', trigger: 'blur'},
          {
            pattern: /(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9])/,
            message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야합니다.',
            trigger: 'blur'
          }
        ]
      },
      formLabelWidth: '120px'
    })

    onMounted(() => {
      // console.log(loginForm.value)
    })

    const checkButton = function () {
      loginForm.value.validate((valid) => {
        if (valid) {
          state.loginButton = false
        } else {
          state.loginButton = true
        }})
    }

    const clickSignup = () => {
      emit('openSignupDialog')
    }

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.dispatch('root/requestLogin', { email: state.form.email, password: state.form.password })
          .then((result) => {
            store.commit('root/SET_TOKEN', result.data.accessToken)
            localStorage.setItem('token', result.data.accessToken)
            store.commit('root/SET_EMAIL', state.form.email)
            localStorage.setItem('email', state.form.email)
            store.dispatch('root/requestUserInfo')
            .then((res) => {
              console.log(res.data)
              store.commit('root/SET_USERINFO', res.data)
              store.commit('root/SET_CURRENT_PAGE', res.data.defaultPage)
              // 다시 로그인을 할 때 로그아웃 한 위치의 페이지로 보내짐...(어떻게 해결?)
              router.push({ name: 'Home' })
              router.go()
            })
          })
          .catch(function (err) {
            if (err.response.status === 401) {
              alert('비밀번호가 틀렸습니다. 다시 입력해주세요.')
            }
          })
        } else {
          alert('올바른 정보를 다시 입력해주세요.')
        }
      })
    }
    return { loginForm, state, clickSignup, clickLogin, checkButton }
  }
}
</script>

<style scoped>
.login {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
.login-button {
  width: 100%;
  margin-top: 40px;
}
.login-form {
  width: 290px;
}
.signup-link {
  margin-top: 10px;
}
.el-card {
  margin-bottom: 0;
}
.el-card__body > h2 {
  margin-bottom: 40px;
}

</style>
<style lang="scss">
$teal: rgb(0, 124, 137);
.el-button--primary {
  background: $teal;
  border-color: $teal;

  &:hover,
  &.active,
  &:focus {
    background: lighten($teal, 7);
    border-color: lighten($teal, 7);
  };
}
.login .el-input__inner:hover {
  border-color: $teal;
}
.login .el-input__prefix {
  background: rgb(238, 237, 234);
  left: 0;
  height: calc(100% - 2px);
  left: 1px;
  top: 1px;
  border-radius: 3px;
  .el-input__icon {
    width: 30px;
  }
}
.login .el-input input {
  padding-left: 35px;
}
.login .el-card {
  padding-top: 0;
  padding-bottom: 30px;
}
a {
  color: $teal;
  text-decoration: none;
  &:hover,
  &:active,
  &:focus {
    color: lighten($teal, 7);
  }
}
.login .el-card {
  width: 340px;
  display: flex;
  justify-content: center;
}
</style>
