<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on" label-position="left">
      <div class="title-container">
        <h3 class="title">重置密码</h3>
      </div>
      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          placeholder="请输入注册的电话"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>
      <el-row>
        <el-col :span="16">
          <el-tooltip v-model="capsTooltip" style="width: 240px;" content="Caps lock is On" placement="right" manual>
            <el-form-item prop="code">
              <el-input
                ref="code"
                v-model="loginForm.code"
                placeholder="请输入验证码"
                name="code"
                tabindex="2"
                autocomplete="on"
                @blur="capsTooltip = false"
              />
            </el-form-item>
          </el-tooltip>
        </el-col>
        <el-col :span="8">
          <el-button type="primary" style="width: 125px;height: 47px;" :disabled="disabled" @click="bindforgetSendCode(loginForm.username)">{{ btnText }}</el-button>
        </el-col>
      </el-row>
      <el-button
        :loading="loading"
        type="primary"
        style="width:100%; margin-bottom:30px;"
        @click.native.prevent="doReset(loginForm.username,loginForm.code)"
      >重置密码</el-button>
    </el-form>

    <el-dialog title="Or connect with" :visible.sync="showDialog">
      Can not be simulated on local, so please combine you own business simulation! ! !
      <br>
      <br>
      <br>
    </el-dialog>
  </div>
</template>

<script>
import { selectUserByMobil, reset, sendMail, isvalidPhone } from '@/api/resetPwd'

export default {
  name: 'Login',
  data() {
    const validatecode = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('验证码不能小于4位'))
      } else {
        callback()
      }
    }

    const validateUser = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入电话号码'))
      } else if (!isvalidPhone(value)) {
        callback(new Error('请输入正确的11位手机号码'))
      } else {
        callback()
      }
    }
    return {
      btnText: '发送验证码',
      disabled: false,
      loginForm: {
        username: '',
        code: ''
      },
      loginRules: {
        code: [{ required: true, trigger: 'blur', validator: validatecode }],
        username: [{ required: true, trigger: 'blur', validator: validateUser }]
      },
      codeType: 'code',
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    // window.addEventListener('storage', this.afterQRScan)
  },
  mounted() {},
  destroyed() {
    // window.removeEventListener('storage', this.afterQRScan)
  },
  methods: {
    doReset(mobil, code) {
      reset(mobil, code)
      this.$router.push({ path: '/login' }).then(() => {
        this.msgSuccess('重置密码成功')
      })
    },
    bindforgetSendCode(mobil) {
      if (mobil !== '') {
        selectUserByMobil(mobil).then(res => {
          if (res.msg === '操作成功') {
            sendMail(mobil).then(res => {
              console.log('发送短信：' + res.data)
            })
            this.disabled = true
            this.btnText = '请稍候...'
            setTimeout(() => {
              this.doLoop(60)
            }, 500)
          } else {
            return false
          }
        })
      }
    },
    doLoop(seconds) {
      // seconds = seconds ? seconds : 60
      seconds = seconds || 60
      this.btnText = seconds + 's后获取'
      // this.code = code
      const countdown = setInterval(() => {
        if (seconds > 0) {
          this.btnText = seconds + 's后获取'
          --seconds
        } else {
          this.btnText = '获取验证码'
          this.disabled = false
          clearInterval(countdown)
        }
      }, 1000)
    },
    checkCapslock(e) {
      const { key } = e
      this.capsTooltip = key && key.length === 1 && key >= 'A' && key <= 'Z'
    },
    showPwd() {
      if (this.codeType === 'code') {
        this.codeType = ''
      } else {
        this.codeType = 'code'
      }
      this.$nextTick(() => {
        this.$refs.code.focus()
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.3);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  width: 100%;
  height: 100%;
  background-image: url('../../assets/login_images/login.png');
  background-size: cover;
  background-position: center;
  position: relative;

  .login-form {
    position: absolute;
    left: 0;
    right: 0;
    width: 450px;
    max-width: 100%;
    background: rgba(0, 0, 0, 0.5);
    padding: 35px 35px 15px 35px;
    margin: 180px auto;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
