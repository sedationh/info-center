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
import { useUserStore } from '@/stores/user'
import { store } from '@/stores'

const form = reactive({
  nickname: '',
  phone: '',
  hobby: ''
})

const avatar = ref([])
const album = ref([])

onMounted(async () => {
  const res = await getUserInfo()
  form.nickname = res.data.nickname
  avatar.value.push(res.data.avatar)
  album.value.push(...res.data.album.split('&'))
  form.phone = res.data.phone
  form.hobby = res.data.hobby
})

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
    if(res.code === 200) {
      const store = useUserStore()
      store.setUserInfo(res.data)
      console.log(store, 'store.userInfo')
      ElMessage.success('修改成功')
    }
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
