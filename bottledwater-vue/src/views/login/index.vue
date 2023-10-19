<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header"></el-header>
      <el-main>
        <div class="login-reg-panel">
        <div class="login-info-box">
          <h2>已有账号？</h2>
          <h3>欢迎登录账号！</h3>
          <button @click="() => (currentAction = 'login')">去登录</button>
        </div>
        <div class="register-info-box">
          <h2>没有账号？</h2>
          <h3>欢迎注册账号！</h3>
          <button @click="() => (currentAction = 'register')">去注册</button>
        </div>
          <div
              class="white-panel"
              :class="{ 'white-panel-left': currentAction === 'register' }"
          >
            <div class="login-show" v-if="currentAction === 'login'">
              <h1 class="title">登录</h1>
            <el-form ref="ruleFormRef"  :rules="rules" name="loginform" :model="user"  label-width="75px" class="fff" action="">
              <el-form-item label="用户" prop="username">
                <el-input v-model="user.username" placeholder="username" />
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="user.password" placeholder="password"  />
              </el-form-item>
              <el-form-item>
                <el-button name="loginbutton" @click="loginone" type="primary" >登入</el-button>
              </el-form-item>
            </el-form>
            </div>
            <div class="register-show" v-else><h1 class="title">注册</h1>
              <el-form   :model="user" :rules="rules1"  label-width="70px">
                <el-form-item label="用户名" prop="username">
                  <el-input  v-model="user.username"   placeholder="username" />
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input v-model="user.password" placeholder="userpassword"  />
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                  <el-input placeholder="phone" v-model="user.phone"  />
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                  <el-input placeholder="mail" v-model="user.email" />
                </el-form-item>
                <el-form-item>
                  <el-button @click="registerone" type="primary" >注册</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>

        </div>

      </el-main>
    </el-container>
  </div>
</template>
<script  lang="ts"  setup>
import {isEmail} from '@/utils/validate'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { Action } from 'element-plus'
import { fetchLogin,adminRegister} from '@/service/index'
import { reactive, ref } from 'vue'
import type { FormInstance,FormRules} from 'element-plus'
interface User {
  username: string
  password: string
  phone: string
  email: string
}
const formSize = ref('default')
const ruleFormRef = ref<FormInstance>()
const user = reactive<User>({
  username:"root",
  password:"123456",
  phone: '',
  email: ''
})
//扩展效验邮箱
let validateEmail = (rule, value, callback) => {
  if (!isEmail(value)) {
    callback(new Error('邮箱格式错误'))
  } else {
    callback()
  }
}

const currentAction=ref("login")
const rules= reactive<FormRules<User>>({
  username:[
    {required:true,message:'未填写用户名',trigger:'blur'}
  ],
  password:[
    {required:true,message:'未填写密码',trigger:'blur'}
  ],


})
const rules1= reactive<FormRules<User>>({
  username:[
    {required:true,message:'未填写用户名',trigger:'blur'}
  ],
  password:[
    {required:true,message:'未填写密码',trigger:'blur'}
  ],
  phone:[
    {required:true,message:'未填写手机号',trigger:'blur'},
    { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }

  ],
  email:[
    {required:true,message:'未填写邮箱',trigger:'blur'},
    {validator: validateEmail, trigger: 'blur'}
  ]
})
function loginone(formEl: FormInstance | undefined) {
  fetchLogin(user)
      .then((res)=>{

        if(!res.success){
          if (res.code==3) {ElMessageBox.alert('', res.message, {
            confirmButtonText: 'OK',
            callback: (action: Action) => {
              ElMessage({
                type: 'info',
                message: `action: ${action}`,
              })
            },
          })}
          if (res.code==2) {ElMessageBox.alert('', res.message, {
            confirmButtonText: 'OK',
            callback: (action: Action) => {
              ElMessage({
                type: 'info',
                message: `action: ${action}`,
              })
            },
          })}
          if (res.code==0) {ElMessageBox.alert('请联系管理员', res.message, {
            // if you want to disable its autofocus
            // autofocus: false,
            confirmButtonText: 'OK',
            callback: (action: Action) => {
              ElMessage({
                type: 'info',
                message: `action: ${action}`,
              })
            },
          })}
        }
        else  {
          location.href = '/home';
        }
      }).catch((err)=>{

        console.log(err)
      })
}
function registerone(formEl: FormInstance | undefined) {
  adminRegister(user).then((res)=>{
    console.log(res)
    if(!res.success){
        if (res.code==2) {ElMessageBox.alert('请更换用户名', res.message, {
          confirmButtonText: 'OK',
          callback: (action: Action) => {
            ElMessage({
              type: 'info',
              message: `action: ${action}`,
            })
          },
        })}
    }
    else{
location.href='home';
    }
  }).catch((err)=>
  console.log(err))
}



</script>
<style>
.title {
  font-size: 24px;
  font-weight: bolder;
  padding: 20px 0;
  color: #202020;
}
.register-info-box {
  width: 30%;
  padding: 0 50px;
  top: 20%;
  right: 0;
  position: absolute;
  text-align: left;
  font-family: 'Mukta', sans-serif;
  color: #b8b8b8;
}

.login-show,
.register-show {
  height: 100%;
  display: flex;
  flex-direction: column;

  transition: 0.3s ease-in-out;
  color: #242424;
  text-align: left;
  padding: 30px;}
.white-panel {
  background-color: rgba(255, 255, 255, 1);
  height: 600px;
  position: absolute;
  width: 50%;
  right: calc(50% - 50px);
  transition: 0.3s ease-in-out;
  z-index: 0;
  box-sizing: border-box;}
.white-panel-left {
  transition: 0.3s ease-in-out;
  right: calc(0px + 50px);
}
h2 {
  font-size: 24px;
  color: #b8b8b8;
  font-weight: bolder;
  font-weight: bolder;
  margin-bottom: 40px;
}
h3 {
  font-size: 20px;
  color: #b8b8b8;
  margin-bottom: 40px;
}
button {
  cursor: pointer;
  width: 100%;
  background-color: transparent;
  box-shadow: none;
  border: 1px solid #b8b8b8;
  border-radius: 2px;
  height: 25px;
}
.login-info-box {
  display: flex;
  flex-direction: column;
  width: 30%;
  padding: 0 50px;
  top: 20%;
  left: 0;
  position: absolute;
  text-align: left;
  justify-content: center;
  font-family: 'Mukta', sans-serif;
  color: #b8b8b8;

}
.header{
  background-color: #c7c1c1;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}



.box-card {
  margin: auto;
  width: 350px;
  background-color: #409EFF;
}
.login-reg-panel {
  position: relative;
  top: 50%;
  transform: translateY(0%);
  text-align: center;
  width: 40%;
  right: 0;
  left: 20%;
  margin: auto;
  min-width: 800px;
  height: 600px;
  background-color: rgba(30, 30, 30, 0.9);}
</style>