<template>
  <div class=" bg-white bg-opacity-40 mt-20 p-20 rounded-md shadow-dark-50 " style="min-height: 700px;">
    <h1 v-if="title" class="text-3xl "> {{ title }}</h1>
    <div class="mt-20 mb-20">
      <span v-if="time" class="mr-20 text-gray-400 text-sm"> 时间： {{ time }}</span>
      <span  v-for="item in tags" :key="item.id" @click="selectTag(item)" class="mr-10">
        <Tag :tag="item"></Tag>
      </span>
    </div>
    <div v-html="content" class="markdown-body w-full mt-20 " style="background-color: rgba(0, 0, 0, 0);"></div>
    <!-- <v-md-editor :model-value="content" mode="preview"></v-md-editor> -->
  </div>
</template>

<script setup lang="ts">
import { getArticleDeatil } from '@/api';
import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
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

const title = ref('')
const time = ref('')
const tags = ref([])
const content = ref('')
onMounted(() => {
    // 打印
    console.log('route:', route.query)
    if(route.query) {
      id = route.query.id
      getInfo(id)
    }

})
const getInfo = (id: String) => {
    getArticleDeatil(id).then((res) => {
      if(res.code == 200) {
        title.value = res.data.title
        time.value = res.data.updatedAt
        tags.value = res.data.tags
        content.value = marked(res.data.content)
      }
    })
}
const router = useRouter()
const selectTag = (tag) => {
  router.push('/index/tagList?tagId='+ tag.id)
}
</script>

<style>

</style>