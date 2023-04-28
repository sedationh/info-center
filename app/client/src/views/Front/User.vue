<template>
  <div
    class="bg-white bg-opacity-40 mt-20 p-20 rounded-md shadow-dark-50"
    style="min-height: 700px"
  >
    <div>
      <div class="w-28 h-28 ml-20 mt-20">
        <el-image
          style="width: 112px; height: 112px; border-radius: 50%"
          :src="info.avatar"
          :zoom-rate="1.2"
          :preview-src-list="[info.avatar]"
          :initial-index="0"
          fit="cover"
        />
      </div>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">昵称</span>
      <span class="text-xl">{{info.nickname}}</span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">联系方式</span>
      <span class="text-xl">{{info.phone}}</span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">爱好</span>
      <span class="text-xl">{{ info.hobby }} </span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">相册</span>
      <div class="mt-20 flex flex-wrap">
        <div v-for="(item, index) in info.album" :key="index" class="mr-10 mb-10">
          <el-image
            style="width: 180px; height: 180px;"
            :src="item"
            :zoom-rate="1.2"
            :preview-src-list="info.album"
            :initial-index="index"
            fit="cover"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { getUserInfo } from '@/api'
import { ElImage } from 'element-plus'
import { reactive, onMounted } from 'vue'

const info = reactive({})
onMounted(async () => {
  const res = await getUserInfo()
  if (res.code == 200) {
    res.data.album = res.data.album.split('&')
    Object.assign(info, res.data)
  }
})
</script>
