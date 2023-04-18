<template>
  <div class="mt-20 bg-white p-20 rounded-md shadow-dark-50">
    <div @click="toDetail(item)" v-for="(item, index) in list" :key="index"  class="pb-20" :class="[index == 0?'':'mt-20', index < list.length - 1?'border-b-2':'']">
        <div class=" font-bold ">{{item.title}}</div>
        <div class="mt-20 text-gray-400">{{ item.content }}</div>
        <div class="text-xs mt-20 flex  items-center ">
            <span class="mr-20">{{ item.time }}</span>
            <span class="mr-20"> 作者： {{ item.name }}</span>
            <span class="mr-10"  v-for="tag in item.tags" :key="tag.id">
                <Tag :tag = "tag"></Tag>
            </span>
        </div>
    </div>

  </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import {ref} from 'vue'
import Tag from "@/components/Tag/Tag.vue";

const list = ref([
    {
        id:9998,
        title:'嗨嗨嗨嗨',
        content:'介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍介绍',
        time:'时间时间时间',
        name:'作者作者作者',
        tags: [
            {
                id:11,
                name:'知识',
                color:'rgb(20,230, 59)',
            },
            {
                id:22,
                name:'知99',
                color:'rgb(20,230, 59)',
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
                id:1221,
                name:'知识',
                color:'rgb(194,36, 123)',
            },
            {
                id:22222,
                name:'知99',
                color:'rgb(194,36, 123)',
            }
        ]
    }
])
const router = useRouter()

function toDetail(item: object) {
    router.push('/index/detail?id=11')
}
</script>

<style>

</style>
