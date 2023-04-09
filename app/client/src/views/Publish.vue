<template>
  <div>
    <div @click="back()" class="mb-20">返回</div>
    <MarkdownEditor v-model="markdeonContent" :height="500"></MarkdownEditor>

    <!-- <HTMLEditor v-model="htmlContent"></HTMLEditor> -->
    <div class="bg-pink-300 h-30 w-50%">{{ htmlContent }}</div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
let htmlContent = ref('')
let markdeonContent = ref('')

console.log(markdeonContent)
const back = () => {
  router.push('/Blog')
}
</script>

<style></style>
