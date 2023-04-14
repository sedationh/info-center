<template>
  <div>
    <div @click="back()" class="mb-20">返回</div>
    <div @click="showMd = !showMd" class="mb-20">切换</div>
    <div v-if="showMd">
      <div>Markdown</div>
      <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>
    </div>
    <div v-else>
      <div>HTML</div>
      <HTMLEditor v-model="htmlContent"></HTMLEditor>
    </div>

    <div class="bg-pink-300 h-30 w-50%">{{showMd?markdeonContent :htmlContent }}</div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import MarkdownEditor from '@/components/MarkdownEditor/MarkdownEditor.vue'
import HTMLEditor from '@/components/HTMLEditor/HTMLEditor.vue'
const router = useRouter()
let htmlContent = ref('')
let markdeonContent = ref('')

const showMd = ref(true)

console.log(markdeonContent)
const back = () => {
  router.push('/Blog')
}


</script>

<style></style>
