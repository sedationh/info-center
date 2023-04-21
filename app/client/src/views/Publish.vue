<template>
  <div>
    <div class="mb-20 flex justify-between">
      <div @click="() => router.push('/admin/blog')">返回</div>
      <div @click="publish()">发布</div>
    </div>
    <div class="mb-20">
      <span>设置标题</span>
      <input class="outline-none border-1 border-gray-400 ml-20" type="text" v-model="title" />
    </div>

    <div class="mb-20">
      <span>设置简介</span>
      <input class="outline-none border-1 border-gray-400 ml-20" type="text" v-model="intro" />
    </div>
    <div class="flex items-center">
      <span class="mr-20">选择标签</span>
      <TagSelect @tags="getTags1"></TagSelect>
    </div>
    <div class="mt-20">
      <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>
    </div>
    {{ markdeonContent }}
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor.vue'
import TagSelect from '@/components/TagSelect/TagSelect.vue'
import { createArticle, getArticleDeatil } from '@/api'

const router = useRouter()
let markdeonContent = ref('')
const title = ref('')
const intro = ref('')

const route = useRoute()
onMounted(() => {
  if(route.query.id) {
    console.log(route.query.id, 'route.query.id')
    getDetail(route.query.id)
  }
})

const getDetail =  async (id) => {
  const res = getArticleDeatil(route.query.id)
  console.log(res, 'ressssss')
}
const tagIdList = ref([])
const publish = () => {
  createArticle({
    title: title.value,
    content: markdeonContent.value,
    tagIds: tagIdList.value
  })
}

const getTags1 = (data: Object[]) => {
  for (const value: Object of data) {
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
