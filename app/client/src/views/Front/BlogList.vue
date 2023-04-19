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
const list = ref([
    {
        id:9998,
        title:'嗨嗨嗨嗨',
        content:'介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍',
        time:'时间时间时间',
        name:'作者作者作者',
        tags: [
            {
                id:"11",
                name:'知识11',
                color:'rgb(20,230, 59)',
            },
            {
                id:"22",
                name:'知22',
                color:'rgb(130,120, 59)',
            }
        ]
    },
    {
        id:222,
        title:'嗨嗨嗨嗨',
        content:'介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍',
        time:'时间时间时间',
        name:'作者作者作者',
        tags: [
            {
                id:"1221",
                name:'知识1221',
                color:'rgb(194,102, 123)',
            },
            {
                id:"22222",
                name:'知22222',
                color:'rgb(122,36, 123)',
            }
        ]
    }
])
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
