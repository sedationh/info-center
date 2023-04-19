<template>
  <div class="mt-20 bg-white p-20 rounded-md shadow-dark-50">
    <ArticleList :list="list" @selectTag="selectTag"></ArticleList>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import {onMounted, ref} from 'vue'
import Tag from "@/components/Tag/Tag.vue";
import { getArticles } from "@/api";
import ArticleList from "@/components/ArticleList/ArticleList.vue";
import {data as blogList} from '@/data/blogList'
const list = ref(blogList)
const router = useRouter()

// function toDetail(item: object) {
//     router.push('/index/detail?id=11')
// }

onMounted(() => {
    getArticles().then((res)=> {
        console.log(res, 'res')
    })
})

// type tagType = {
//     id: String,
//     name: String,
//     color: String
// }

const selectTag = (data) => {
  console.log(data, 'data000')
  const query  = data
  router.push({
    path: '/index/tagList',
    query
})
}
</script>

<style></style>
