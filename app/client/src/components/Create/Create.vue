<template>
  <el-dialog v-model="show"
             :title="title[type - 1]"
             width="30%"
             :before-close="handleClose">
    <template #footer>
      <div class="flex justify-center items-center w-90% mb-20 mt-20">
        <el-form :model="config"
                 class="w-100% ml-50">
          <el-form-item label="标题">
            <el-input v-model="info.name" />
          </el-form-item>
          <el-form-item :label="type==4?'头像':'封面'">
            <el-upload class="avatar-uploader bg-gray-100 "
                       action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
                       :show-file-list="false"
                       :on-success="handleAvatarSuccess"
                       :before-upload="beforeAvatarUpload">
              <img v-if="info.img"
                   width="178"
                   height="178"
                   :src="info.img"
                   class="avatar" />
              <el-icon v-else
                       class="avatar-uploader-icon">
                <Plus />
              </el-icon>
            </el-upload>
          </el-form-item>
          <el-form-item label="简介">
            <el-input v-model="info.name"
                      type="textarea" />
          </el-form-item>
          <div class="flex">
            <el-button class="bg-green-500 flex justify-end"
                       type="success">{{type==1?'发布':'创建'}}</el-button>
            <el-button class="bg-green-500 flex justify-end"
                       type="success"
                       @click="show = false">取消</el-button>
          </div>
        </el-form>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { Plus } from "@element-plus/icons-vue";
const props = defineProps({
  type: {
    type: [Number, String],
    default: 1   //   1: 新增文章 2：新增分类 3: 新建专题 4：新增成员
  }
})
const info = reactive({
  title: '',
  name: '',
  img: '',
  intro: ''
})
let show = ref(false)

const title = ref(['新增文章', '新增分类', '新增专题', '新增成员'])

function open() {
  show.value = true
}
const handleAvatarSuccess = (
  response,
  uploadFile
) => {
  info.img = URL.createObjectURL(uploadFile.raw);
};

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("Avatar picture must be JPG format!");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("Avatar picture size can not exceed 2MB!");
    return false;
  }
  return true;
};
defineExpose({
  open,
  show
})



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