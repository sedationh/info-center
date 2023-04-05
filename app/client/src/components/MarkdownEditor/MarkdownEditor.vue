<template>
  <div>
    <v-md-editor v-model="text"
                 :height="height + 'px'"
                 @change="change"></v-md-editor>
  </div>
</template>

<script setup>
import VMdEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import { ref } from 'vue';
// highlightjs
import hljs from 'highlight.js';
// import { emit } from 'process';

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});

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

const emit = defineEmits()
const change = (markdeonContent, htmlContent) => {
    emit("update:modelValue", markdeonContent)
    emit("htmlContent", htmlContent)
}
console.log(props)
</script>

<style>
</style>