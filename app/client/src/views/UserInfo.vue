<template>
  <div class="w-50% mt-20">
    <el-form :model="form" label-width="120px">
      <el-form-item label="昵称">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader bg-gray-100"
          action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="form.imageUrl" width="178" height="178" :src="form.imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon">
            <Plus />
          </el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="密码">
        <el-switch v-model="form.password" />
      </el-form-item>
      <el-form-item label="默认编辑器">
        <el-radio-group v-model="form.type" class="ml-4">
          <el-radio label="1" size="large">富文本编辑器</el-radio>
          <el-radio label="2" size="large">Markdown编辑器</el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <div class="ml-40 mt-20">
      <el-button class="bg-green-500" type="success">Success</el-button>
    </div>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
const handleAvatarSuccess = (response, uploadFile) => {
  form.imageUrl = URL.createObjectURL(uploadFile.raw)
}

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}

const form = reactive({
  name: '',
  phone: '',
  password: '',
  imageUrl: '',
  type: 1
})

const onSubmit = () => {
  console.log('submit!')
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
