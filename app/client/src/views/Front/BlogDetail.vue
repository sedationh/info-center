<template>
  <div class=" bg-white mt-20 p-20 rounded-md shadow-dark-50 flex justify-start flex-col items-center" style="min-height: 700px;">
    <h1 v-if="info.title" class="text-3xl text-center"> {{ info.title }}</h1>
    <div v-if="info.intro" class="mt-20"> {{ info.intro }}</div>
    <div class="mt-20">
      <span v-if="info.time" class="mr-20"> 时间： {{ info.time }}</span>
      <span v-for="item in info.tags" :key="item.id" class="mr-10">
        <Tag :tag="item"></Tag>
      </span>
    </div>
    <div v-html="content"  class="markdown-body">

    </div>
    <v-md-editor :model-value="content" mode="preview"></v-md-editor>
  </div>
</template>

<script setup lang="ts">
import { getArticleDeatil } from '@/api';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import {data as blogList} from '@/data/blogList'
import Tag from '@/components/Tag/Tag.vue';
import { marked } from 'marked'
import "github-markdown-css"
import { computed } from '@vue/reactivity';


import VMdEditor from '@kangc/v-md-editor'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/theme/style/github.css'
const route = useRoute();

let id = ''
onMounted(() => {
    // 打印
    console.log('route:', route.query)
    if(route.query) {
      id = route.query.id
      getInfo(id)
    }

})
const info = ref({})
const content = ref('')
const getInfo = (id) => {
    info.value = blogList.find(value => value.id == id)
    console.log(info.value.content, 'info.value.content')
    content.value = marked(info.value.content)
}
console.log(info.value)
// const content = computed(() => {
//   return marked(info.value.content)
// })

</script>

<style>

</style>