<template>
  <div class=" ">
    <div class="bg-white py-30">
      <div class="main-w m-auto flex justify-between items-center">
        <div class="flex">
          <div
          
          
          
          
          
          
          class="mr-20 pointer">logo</div>
          <!-- <div @click="open('blogList')" class="mr-20 pointer">博客</div> -->
          <div @click="open('tagList')" class="pointer">标签分类</div>
        </div>
        <div @click="open('user')">
          <el-image
          style="width: 60px; height: 60px; border-radius: 50%"
          src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"
          fit="cover"
        />
        </div>
      </div>
    </div>

    <div class="main-w m-auto">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter()
function open(name: string) {
    router.push('/index/'+name)
}
</script>

<style>
body {
  background-color: #f4f4f4;
}
.main-w {
  width: 80%;
}
.pointer {
  cursor: pointer
}
</style>
