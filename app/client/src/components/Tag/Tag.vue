<template>
  <span class="px-10 py-5 text-xs rounded-md border-1 mr-5" :style="colors(props.tag.color)">
    <span>{{ props.tag.name }}</span>
    <span></span>
    <span v-if="props.remove" @click.stop="removeTag()" class="iconfont icon-cuo ml-5"></span>
  </span>
</template>

<script setup lang="ts">
const props = defineProps({
  tag: {
    type: Object,
    default: () => {}
  },
  remove: {
    type: Boolean,
    default: false
  }
})

const emits = defineEmits(['remove'])
const removeTag = () => {
  emits('remove', props.tag)
}
const colors = (color: string) => {
  if (color) {
    return {
      'background-color': color.substring(0, color.length - 1) + ',0.1)',
      'border-color': color.substring(0, color.length - 1) + ',0.2)',
      color: color
    }
  }
}
</script>
<style>
.border-1 {
  border-width: 1px;
}
</style>
