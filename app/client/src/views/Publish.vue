<template>
  <div>
    <div class="mb-20 flex justify-between ">
      <div @click="back()">返回</div>
      <div @click="publish()">发布</div>
      
    </div>
    <div class="mb-20">
      <span>设置标题</span>
      <input class="outline-none border-1 border-gray-400 ml-20" type="text" v-model="title">
    </div>
    
    <div class="mb-20">
      <span>设置简介</span>
      <input class="outline-none border-1 border-gray-400 ml-20" type="text" v-model="intro">
    </div>
    <div class="flex items-center ">
      <span class="mr-20">选择标签</span>
      <TagSelect :tags="tags" @tags="getTags"></TagSelect>  
    </div>
    <div class="mt-20">
      <!-- <div>Markdown</div> -->
      <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>
    </div>
    {{ markdeonContent.split('/n') }}
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor.vue'
import HTMLEditor from '@/components/HTMLEditor/HTMLEditor.vue'
import TagSelect from '@/components/TagSelect/TagSelect.vue'
import { tags } from '@/data/tagsList'
import { createArticle } from '@/api'


const router = useRouter()
let htmlContent = ref('')
let markdeonContent = ref('')

const showMd = ref(true)

const back = () => {
  router.push('/Blog')
}


const title = ref('')
const intro = ref('')
const tagIdList = ref([])
const publish = () => {
  createArticle({
    id:'',
    title:title.value,
    content:markdeonContent.value,
    tags:tagIdList.value
  })
}

const getTags = (data: Object[]) => {
  for(const value:Object of data) {
    tagIdList.value.push(value.id)
  }
}
</script>

<style>
.hi .el-select-dropdown__item div {
  /* background-color: pink; */
  text-align: center;
  padding: 0 5px;
  border: 1px solid #ccc;
  margin-top: 2px;
}
</style>
