<template>
  <div style="border-radius: 4px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <div class="flex" style="width: 100%">
      <div style="width: 50%">
        <Editor
          style="height: 500px; overflow-y: hidden"
          v-model="valueHtml"
          :defaultConfig="editorConfig"
          :mode="mode"
          @onCreated="handleCreated"
          @onChange="onChange"
        />
      </div>

      <div style="border-left: 1px solid #DDDDDD;" >{{ content }}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import '@wangeditor/editor/dist/css/style.css' // 引入 css

import { onBeforeUnmount, ref, shallowRef } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
// editorConfig.MENU_CONF['uploadImage'] = {
//      server: '/api/upload',
// }
// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('')

const mode = 'default'
const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }

const content = ref('')

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor: any) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

const emit = defineEmits<{
  (e: 'update:modelValue', editor: string): void
}>()
const onChange = (editor: any) => {
  console.log(editor.getHtml(), '---------')
  content.value = editor.getHtml()
  emit('update:modelValue', editor.getHtml())
}

const props = defineProps({
  modelValue: {
    type: String,
    default: ''
  }
})
</script>

<style></style>
