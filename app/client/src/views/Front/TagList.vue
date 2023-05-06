<template>
  <div
    class="bg-white bg-opacity-40 mt-20 p-20 rounded-md shadow-dark-50"
    style="min-height: 700px"
  >
    <div class="flex items-center justify-center w-full">
      <div class="flex bg-white items-center justify-start w-2/4 px-20 rounded-md py-1 input-bg">
        <div v-if="selected" class="my-2">
          <Tag :tag="currentTag" :remove="true" @remove="remove()"></Tag>
        </div>
        <input
          v-else
          v-model="searchValue"
          class="m-2 w-2/4 outline-none bg-opacity-0"
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
import { onMounted, ref } from 'vue'
import { getArticles, getOneTag, getTags } from '@/api'
import { useRoute } from 'vue-router'
const currentTag = ref({})

const searchValue = ref('')
const blogList = ref([])
const list = ref([])
const articleList = ref([])

onMounted(() => {
  get()
})

const route = useRoute()

const selectTag = (data) => {
  currentTag.value = data
  selected.value = true
  ininList(currentTag.value)
}

const searchTag = () => {
  if (searchValue.value) {
    list.value = list.value.filter((tag) => tag.name.includes(searchValue.value))
  } else {
    list.value = tags
  }
}

const selected = ref(false)

const ininList = (value) => {
  if (value) {
    articleList.value = blogList.value.filter((item) => {
      return item.tags.find((tag) => tag.id == value.id)
    })
  } else {
    articleList.value = blogList.value
  }
}

const remove = () => {
  selected.value = false
  ininList('')
}

const get = async () => {
  if (route.query.tagId) {
    console.log(list.value, 'list')
    const res = await getOneTag(route.query.tagId)
    if (res.code != 200) return
      currentTag.value = res.data
      articleList.value = blogList.value.filter((item) => {
        return item.tags.find((tag) => tag.id == currentTag.value.id)
      })
  }
  getTags().then((res) => {
    if (res.code == 200) {
      list.value = res.data
    }
  })
  getArticles().then((res) => {
    if (res.code == 200) {
      blogList.value = res.data.reverse()
      blogList.value.forEach((item) => {
        item.content = item.content
          .split('\n')
          .map((e) => e.replace(/#|```/g, ''))
          .toString()
          .replace(/,/g, '')
      })
      articleList.value = blogList.value
    }
  })
  
}
</script>

<style>
.input-bg {
  background-color: #fff;
}
</style>
