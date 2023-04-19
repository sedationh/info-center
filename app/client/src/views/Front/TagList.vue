<template>
  <div class="bg-white mt-20 p-20 rounded-md shadow-dark-50" style="min-height: 700px">
    <div class="flex items-center justify-center w-full">
      <div class="flex items-center justify-start border-1 border-gray-300 w-2/4 px-20 rounded-md py-1">
        <div v-if="selected" class="my-2">
          <Tag :tag="currentTag" :remove="true" @remove="remove()"></Tag>
        </div>
        <input v-else
        v-model="searchValue"
        class="m-2 w-2/4 outline-none"
        placeholder="搜索标签"
        @change="searchTag()"
      />
      
     </div>
      
    
    </div>
    <div class="flex item-center justify-center mt-20">
      <span v-for="item in list" :key="item.id" class="mr-5 mb-5" @click="selectTag(item)">
        <Tag :tag="item"></Tag>
      </span>
    </div>
    <div class="mt-20">
      <ArticleList :list="articleList" @selectTag="selectTag"></ArticleList>
    </div>
  </div>
</template>

<script setup lang="ts">
import Tag from '@/components/Tag/Tag.vue'
import ArticleList from '@/components/ArticleList/ArticleList.vue'
import { tags } from '@/data/tagsList'
import { onMounted, ref, reactive } from 'vue'
import { useRoute } from 'vue-router'
import {data as blogList} from '@/data/blogList'

const route = useRoute()
const currentTag = ref({})
onMounted(() => {
  if (route.query.color) {
    currentTag.value = route.query
    selected.value = true
    ininList(currentTag.value)
  }
})
const searchValue = ref('')
const list = ref(tags)

const articleList = ref(blogList)
const selectTag = (data) => {
  currentTag.value = data 
  selected.value = true
  ininList(currentTag.value)
}

const searchTag = () => {
  if(searchValue.value) {
    list.value = list.value.filter((tag) => tag.name.includes(searchValue.value))
  }
  else {
    list.value = tags
  }
}

const selected = ref(false)

const ininList = (value) => {
  if(value) {
    articleList.value = blogList.filter(item => {
    return item.tags.find(tag => tag.id == value.id) 
  })
  }
  else {
    articleList.value = blogList
  }
  
}

const remove = () => {
  selected.value = false
  ininList('')
}
</script>

<style></style>
