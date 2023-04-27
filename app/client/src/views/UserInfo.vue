<template>
  <div class="w-50% mt-20">
    <el-form :model="form" label-width="120px">
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" />
      </el-form-item>
      <el-form-item label="头像">
        <ImageUpload :type="1" v-model="avatar"></ImageUpload>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="爱好">
        <el-input v-model="form.hobby" />
      </el-form-item>
      <el-form-item label="上传图片">
        <ImageUpload :type="2" v-model="album"></ImageUpload>
      </el-form-item>
    </el-form>
    <div class="ml-40 mt-20">
      <el-button @click="submitInfo()" class="bg-green-500" type="success" style="margin-left: 80px"
        >编辑</el-button
      >
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from 'vue'
import ImageUpload from '@/components/ImagsUpload/ImagsUpload.vue'
import { ElMessage } from 'element-plus'
import { getUserInfo, aditUserInfo } from '@/api'

const form = reactive({
  nickname: '',
  phone: '',
  hobby: ''
})

const avatar = ref(['blob:http://127.0.0.1:5173/283fe03c-11f5-4056-bf04-5ab11d47c3ca'])
const album = ref(['blob:http://127.0.0.1:5173/283fe03c-11f5-4056-bf04-5ab11d47c3ca'])
onMounted(async () => {
  const res = await getUserInfo()
  console.log(res, 'res')
  form.nickname = res.data.nickname
  form.phone = res.data.phone
  form.hobby = res.data.hobby
})
console.log(avatar, 'form.avatar')
const submitInfo = () => {
  if(!form.nickname) {
    return ElMessage.error('请输入昵称')
  }
  if(!form.phone) {
    return ElMessage.error('请输入联系方式')
  }
  if(!form.hobby) {
    return ElMessage.error('请输入爱好')
  }
  aditUserInfo({
    nickname: form.nickname,
    phone: form.phone,
    hobby: form.hobby,
    avatar: avatar.value[0],
    album: album.value.join('&')
  }).then(res => {
    console.log(res, 'res')
  })
}
</script>
<style scoped>
.avatar-uploader {
  width: 178px;
  height: 178px;
  display: block;
}

.avatar-uploader .avatar {
  /* width: 100%;
  height: 100%; */
  display: block;
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
