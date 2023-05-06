<template>
  <div>
    <div class="mb-20 flex justify-between">
      <div @click="() => router.push('/admin/blog')">返回</div>
      <div @click="publish()" class="cursor-pointer">{{ route.query.id ? '编辑' : '发布' }}</div>
    </div>
    <div class="mb-20">
      <span>设置标题</span>
      <input class="outline-none border-1 border-gray-400 ml-20" type="text" v-model="title" />
    </div>
    <div class="flex items-center">
      <span class="mr-20">选择标签</span>
      <TagSelect @tags="getTags1" :list2="selectTag"></TagSelect>
    </div>
    <div class="mt-20">
      <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor.vue'
import TagSelect from '@/components/TagSelect/TagSelect.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { aditArticle, createArticle, getArticleDeatil, getOneTag } from '@/api'

const router = useRouter()
const markdeonContent = ref('')
const title = ref('')
const route = useRoute()
onMounted(() => {
  if (route.query.id) {
    getDetail(route.query.id)
  }
})

const selectTag = ref([])
const getDetail = async (id) => {
  await getArticleDeatil(id).then((res) => {
    if (res.code == 200) {
      markdeonContent.value = res.data.content
      title.value = res.data.title
      selectTag.value = res.data.tags
    }
  })
}
const tagIdList = ref([])
const publish = () => {
  if (!title.value) {
    ElMessage({
      message: '标题不能为空',
      type: 'error'
    })
    return
  }
  if (!markdeonContent.value) {
    ElMessage({
      message: '文章内容不能为空',
      type: 'error'
    })
    return
  }
  if (route.query.id) {
    aditArticle({
      id: route.query.id,
      title: title.value,
      content: markdeonContent.value,
      tagIds: tagIdList.value
    }).then((res) => {
      if (res.code !== 200) return
      ElMessage({
        message: '编辑成功',
        type: 'success'
      })
      setTimeout(() => {
        router.go(-1)
      }, 1000)
    })
  } else {
    createArticle({
      title: title.value,
      content: markdeonContent.value,
      tagIds: tagIdList.value
    }).then((res) => {
      if (res.code !== 200) return
      ElMessage({
        message: '发布成功',
        type: 'success'
      })
      setTimeout(() => {
        router.go(-1)
      }, 1000)
    })
  }
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
