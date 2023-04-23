<template>
  <el-dialog v-model="show" :title="title[type - 1]" width="30%" >
    <template #footer>
      <div class="flex justify-center items-center w-90% mb-20 mt-20">
        <el-form :model="info" class="w-100% ml-50">
          <el-form-item label="名称" width="180">
            <el-input v-model="info.name" />
          </el-form-item>
          <el-form-item v-if="info.id" label="id" width="180">
            <el-input v-model="info.id" disabled/>
          </el-form-item>
          <el-form-item v-if="type == 4" :label="type == 4 ? '头像' : '封面'">
            <el-upload
              class="avatar-uploader bg-gray-100"
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
            >
              <img v-if="info.img" width="178" height="178" :src="info.img" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item label="颜色">
            <el-color-picker v-model="info.color" :predefine="predefineColors" />
           <span class="ml-20 color-white" :style="{'background-color': info.color}"> {{ info.color }}</span>
          </el-form-item>
          <el-form-item v-if="type == 4" label="简介">
            <el-input v-model="info.name" type="textarea" />
          </el-form-item>
          <div class="flex">
            <el-button @click="success()" class="bg-green-500 flex justify-end" type="success">{{
              type == 2 ? '创建' : '编辑'
            }}</el-button>
            <el-button @click="close()" class="bg-green-500 flex justify-end" type="success"
              >取消</el-button
            >
          </div>
        </el-form>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const type = ref(1)

const info = reactive({
  name: '',
  color: '#22C55E',
})
const show = ref(false)

const title = ref(['新增文章', '新增标签', '编辑标签', '新增成员'])

const open = (data: number, row: Object) => {
  type.value = data
  Object.assign(info, row)
  console.log(info, 'info')
  show.value = true
}
const close = () => {
  console.log('close111111111')
  show.value = false
}
const handleAvatarSuccess = ( uploadFile: any) => {
  info.img = URL.createObjectURL(uploadFile.raw)
}

const beforeAvatarUpload = (rawFile: any) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}
defineExpose({
  open,
  show,
  close
})
const emits = defineEmits(["success", "adit"])
const success = () => {
  console.log()
  emits("success", info)
}


</script>

<style>
.el-dialog__body {
  padding: 0;
}

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
