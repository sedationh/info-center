<template>
  <div class="login-bg relative">
    <div class="flex justify-center">
      <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" class="form">
        <div class="login">login</div>
        <el-form-item label="账号" prop="id">
          <el-input v-model="ruleForm.id" type="text" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" prop="password" class="text-pink-500">
          <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
        </el-form-item>
        <div class="flex items-center justify-around ">
          <!-- <button class="px-20 py-5 rounded-lg border  text-white hover:bg-pink-400 hover:border-pink-400 " @click="login()">登录</button>
          <button @click="signup()" class="px-20 py-5 rounded-lg border  text-white hover:bg-pink-400 hover:border-pink-400 ">注册</button> -->
          <el-button class="text-white" plain @click="login()">登录</el-button>
          <el-button class="text-white" plain @click="signup()">注册</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { signin, register, hasUserRegistered } from '@/api'
const router = useRouter()
const ruleForm = reactive({
  id: '',
  password: ''
})

const rules = reactive({
  id: [{ required: true, message: '请输入' }],
  password: [{ required: true, message: '请输入' }]
})

const hasUser = async () => {
  const isHasUser = await hasUserRegistered()
  return isHasUser.data
}
const login = async () => {
  if (!ruleForm.id || !ruleForm.password) {
    ElMessage.error('账号或密码不正确')
    return
  }
  signin({
    username: ruleForm.id,
    password: ruleForm.password
  })
    .then(() => {
      router.push('/admin')
    })
    .catch((err) => {
      ElMessage.error('账号或密码不正确')
    })
}

const signup = async () => {
  if (await hasUser()) {
    ElMessage({
      message: '已有账号注册， 请登录',
      type: 'warning'
    })
    return
  }
  if (!ruleForm.id || !ruleForm.password) {
    ElMessage.error('账号或密码不正确')
    return
  }
  const res = await register({
    username: ruleForm.id,
    password: ruleForm.password
  })
  if (res.data) {
    ElMessage({
      message: '注册成功， 即将跳转',
      type: 'success'
    })
    setTimeout(() => {
      router.push('/admin')
    }, 2000)
  }
}
</script>

<style>
body {
  height: 100vh;
}
.form {
  width: 300px;
  padding: 40px 20px;
  border-radius: 20px;
  color: #e61065;
  position: absolute;
  right: 5%;
  top: 15%;
  background: rgba(255, 255, 255, 0.3);
  box-shadow: inset -13px 13px 92px #fbb1cf, inset 13px -13px 92px #fff1ff;
}

.login {
  width: 100%;
  text-align: center;
  margin-bottom: 10px;
}
.login-bg {
  background: url('@/static/11.jpg') no-repeat center/100% 100%;
  width: 100%;
  height: 100vh;
}
</style>
