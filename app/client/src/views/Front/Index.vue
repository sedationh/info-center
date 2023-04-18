<template>
  <div class=" ">
    <div class="bg-white py-40">
      <div class="main-w m-auto flex justify-between">
        <div class="flex">
          <div @click="open('blogList')" class="mr-20 pointer">logo</div>
          <div @click="open('blogList')" class="mr-20 pointer">博客</div>
          <div @click="open('tagList')" class="pointer">标签分类</div>
        </div>
        <div>成员</div>
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
