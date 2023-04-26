<template>
  <div class="bg-white bg-opacity-40 mt-20 p-20 rounded-md shadow-dark-50" style="min-height: 700px">
    <div>
      <div class="w-28 h-28 ml-20 mt-20">      
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
    <!-- <div class="mt-40" @click.stop="toLogin()">
        登录
    </div> -->
    <div class="mt-40 ml-40">
      <span class="mr-20">昵称</span>
      <span class=" text-xl">一只可爱</span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">联系方式</span>
      <span class=" text-xl">1196342044@qq.com</span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">爱好</span>
      <span class=" text-xl">摸鱼 </span>
    </div>
    <div class="mt-40 ml-40">
      <span class="mr-20">相册</span>
      <div class="mt-20 flex flex-wrap">
        <div v-for="item in 5" class="w-30% mr-10 mb-10">
          <el-image
          style="width: 100%;"
          :src="imgUrl"
          :zoom-rate="1.2"
          :preview-src-list="imgUrlList"
          :initial-index="0"
          fit="cover"
        />
        </div>
      </div>
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
