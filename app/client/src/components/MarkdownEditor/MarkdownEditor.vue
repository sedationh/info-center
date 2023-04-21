<template>
  <div>
    <v-md-editor v-model="text" :height="height + 'px'" @change="change"></v-md-editor>
  </div>
</template>

<script setup lang="ts">
import VMdEditor from '@kangc/v-md-editor'
import '@kangc/v-md-editor/lib/style/base-editor.css'
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js'
import '@kangc/v-md-editor/lib/theme/style/github.css'
import { ref } from 'vue'
// highlightjs
import hljs from 'highlight.js'
// import { emit } from 'process';
import { defineEmits, defineProps } from 'vue'

VMdEditor.use(githubTheme, {
  Hljs: hljs
})

const text = ref('')
const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  },
  height: {
    type: Number,
    default: 500
  }
})

const emit = defineEmits<{
  (e: "update:modelValue", markdeonContent: string): void
  (e: "htmlContent", htmlContent: string): void
}>()
const change = (markdeonContent: string, htmlContent:string) => {
  emit('update:modelValue', markdeonContent)
  emit('htmlContent', htmlContent)
}
</script>

<style></style>
