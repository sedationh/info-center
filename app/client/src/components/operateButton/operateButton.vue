<template>
  <div>
    <span class="mr-10 hover:text-green-500 cursor-pointer"
          @click="editor(data)">编辑</span>
    <span class="hover:text-green-500 cursor-pointer"
          @click="del(data)">删除</span>
  </div>
</template>

<script setup>
import {ref} from 'vue'

defineProps({
    data: {
        type: Object,
        default: {}
    },
    dataList: []
})

const emit = defineEmits(['delete_item'])

let list = ref(dataList)
function editor(data) {
  console.log('editor', data)
}

function del(data) {
  console.log('delate', data)
  list = list.value.filter(item => item != data)
  emit('delete_item', list)
  console.log(list, 'list')
}
</script>

<style>
</style>