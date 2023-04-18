<template>
  <div>
    <div @click="back()" class="mb-20">返回</div>
    <!-- <div @click="showMd = !showMd" class="mb-20">切换</div> -->

    <div>
      选择标签
      <TagSelect :tags="tags"></TagSelect>
      
      
      
    </div>
    <div v-if="showMd" class="mt-20">
      <!-- <div>Markdown</div> -->
      <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>
    </div>
    <div v-else>
      <div>HTML</div>
      <HTMLEditor v-model="htmlContent"></HTMLEditor>
    </div>

  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor.vue'
import HTMLEditor from '@/components/HTMLEditor/HTMLEditor.vue'
import TagSelect from '@/components/TagSelect/TagSelect.vue'
import { tags } from '@/data/tagsList'


const router = useRouter()
let htmlContent = ref('')
let markdeonContent = ref('')

const showMd = ref(true)

const back = () => {
  router.push('/Blog')
}

console.log('tags', tags)


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
