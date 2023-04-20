<template>
  <div class=" bg-white mt-20 p-20 rounded-md shadow-dark-50 " style="min-height: 700px;">
    <h1 v-if="info.title" class="text-3xl "> {{ info.title }}</h1>
    <div v-if="info.intro" class="mt-20"> {{ info.intro }}</div>
    <div class="mt-20 mb-20">
      <span v-if="info.time" class="mr-20 text-gray-400 text-sm"> 时间： {{ info.time }}</span>
      <span v-for="item in info.tags" :key="item.id" class="mr-10">
        <Tag :tag="item"></Tag>
      </span>
    </div>
    <div v-html="content" class="markdown-body w-full mt-20"></div>
    <!-- <v-md-editor :model-value="content" mode="preview"></v-md-editor> -->
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
import hljs from 'highlight.js'
import 'highlight.js/styles/foundation.css'

const render = new marked.Renderer()
marked.setOptions({
  renderer: render, // 这是必填项
  gfm: true,	// 启动类似于Github样式的Markdown语法
  pedantic: false, // 只解析符合Markdwon定义的，不修正Markdown的错误
  sanitize: false, // 原始输出，忽略HTML标签（关闭后，可直接渲染HTML标签）

	// 高亮的语法规范
  highlight: (code, lang) => hljs.highlight(code, { language: lang }).value,
})

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
const aa = ref('')
const getInfo = (id: String) => {
    info.value = blogList.find(value => value.id == id)
    content.value = marked(info.value.content[0])
}
</script>

<style>

</style>