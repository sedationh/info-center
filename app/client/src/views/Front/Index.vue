<template>
  <div class=" ">
    <div class="bg-white py-20 bg-opacity-40 ">
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
          :src="store.avatar"
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
import { useUserStore } from '@/stores/user'
import { useRouter } from 'vue-router';

const router = useRouter()
function open(name: string) {
    router.push('/index/'+name)
}
const store = useUserStore()
console.log(store, 'store')

</script>

<style>
body {
  background: linear-gradient(90deg, rgba(254,201,252,0.6306897759103641) 0%, rgba(168,250,255,1) 73%, rgba(57,245,186,0.7707457983193278) 100%);
}
.main-w {
  width: 80%;
}
.pointer {
  cursor: pointer
}
</style>
