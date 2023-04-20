<template>
  <div class="bg-white mt-20 p-20 rounded-md shadow-dark-50" style="min-height: 700px">
    <div>
      <div class="w-28 h-28 ">      
        <el-image
          style="width: 112px; height: 112px; border-radius: 50%"
          :src="imgUrl"
          :zoom-rate="1.2"
          :preview-src-list="imgUrlList"
          :initial-index="0"
          fit="cover"
        />
      </div>
    </div>
    <div class="mt-40" @click.stop="toLogin()">
        登录
    </div>

  </div>
</template>
<script setup lang="ts">
import { ElImage } from 'element-plus'
import { ref } from 'vue'
import { useRouter } from 'vue-router';

const imgUrl = "https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"
const imgUrlList = [imgUrl]

const router = useRouter()
const toLogin = ()=> {
    router.push('/login')
}
</script>
