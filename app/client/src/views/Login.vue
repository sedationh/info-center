<template>
  <div class="login-bg relative">
    <div class="flex justify-center items-center h-full">
      <el-form ref="ruleFormRef" :model="ruleForm" status-icon :rules="rules" class="form">
        <div class="login">login</div>
        <el-form-item label="账号" prop="id">
          <el-input v-model="ruleForm.id" type="text" autocomplete="off" />
        </el-form-item>
        <el-form-item label="密码" prop="password" class="text-pink-500">
          <el-input v-model="ruleForm.password" type="password" autocomplete="off" />
        </el-form-item>
        <div class="flex items-center justify-around mb-40 mt-40">
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
import { useUserStore } from '@/stores/user'
const router = useRouter()
const store = useUserStore()
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
      store.setStatus(true)
      localStorage.setItem('isLogin', 'true')
      //q: 怎么将登陆状态写入缓存

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
    store.setStatus(true)
    localStorage.setItem('isLogin', 'true')
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
  width: 500px;
  /* height: 300px; */
  padding: 40px 80px;
  border-radius: 20px;
  color: #e61065;
  margin: auto;
  background: rgba(255, 255, 255, 0.3);
  box-shadow: inset -13px 13px 92px #fbb1cf, inset 13px -13px 92px #fff1ff;
}

.login {
  width: 100%;
  text-align: center;
  margin-bottom: 40px;
  margin-top: 20px;
}
.login-bg {
  width: 100vw;
  height: 100vh;
  background: linear-gradient(
    90deg,
    rgba(254, 201, 252, 0.6306897759103641) 0%,
    rgba(168, 250, 255, 1) 73%,
    rgba(57, 245, 186, 0.7707457983193278) 100%
  );
}
</style>
