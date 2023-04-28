<template>
  <div>
    <div class="flex">
      <div
        v-for="(item, index) in imgList"
        :key="index"
        class="mr-10 relative"
        style="width: 180px; height: 180px"
      >
        <el-image
          v-if="item"
          class="mr-10 rounded-md"
          style="width: 180px; height: 180px; margin-right: 10px"
          :src="item"
          :preview-src-list="imgList"
          :initial-index="index"
          fit="cover"
        />
        <span @click="removeImg(item)" class="iconfont icon-cuo removeImg-icon"> </span>
      </div>
      <el-upload
        class="avatar-uploader"
        :action="uploadImgUrl"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <el-icon class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

import type { UploadProps } from 'element-plus'

const uploadImgUrl = 'http://127.0.0.1:5173/api/upload/images'

const props = defineProps({
  type: {
    type: Number,
    default: 1
  },
  modelValue: {
    type: Array,
    default: () => []
  }
})
const imgList = ref(props.modelValue)
const imageUrl = ref('')
const imgHead = "http://47.113.180.18:4000/images/"
const emits = defineEmits(['update:modelValue'])

const handleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  imageUrl.value = imgHead + response.data

  if (props.type === 1) {
    imgList.value.pop()
  }

  imgList.value.push(imageUrl.value)
  emits('update:modelValue', imgList.value)
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.size / 1024 / 1024 > 10) {
    ElMessage.error('图片大小不超过10MB!')
    return false
  }
  return true
}
const removeImg = (item) => {
  const index = imgList.value.indexOf(item)
  imgList.value.splice(index, 1)
  emits('update:modelValue', imgList.value)
}

</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  background-color: #eeeeee;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
.removeImg-icon {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 20px;
  color: #fff;
  cursor: pointer;
}
</style>
