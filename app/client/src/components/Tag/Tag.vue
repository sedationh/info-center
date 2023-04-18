<template>
  <span class="px-10 py-5 text-xs rounded-md border-1" :style="colors(tag.color)">
    {{ tag.name }}
  </span>
</template>

<script setup lang="ts">
defineProps({
  tag: {
    type: Object,
    default: () => {}
  }
})
const colors = (color: string) => {
  return {
    'background-color': color.substring(0, color.length - 1) + ',0.1)',
    'border-color': color.substring(0, color.length - 1) + ',0.2)',
    color: color
  }
}
</script>
<style>
.border-1 {
  border-width: 1px;
}
</style>
