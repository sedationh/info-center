<template>
  <div>
    <div>
      <el-select
        v-model="value"
        multiple
        filterable
        allow-create
        default-first-option
        :reserve-keyword="false"
        :remote-method="remoteMethod"
        placeholder="Choose tags for your article"
        popper-class="hi"
      >
        
        <el-option v-for="item in tags" :key="item.id" :label="item.name" :value="item.id">
          <template #>
            <Tag :tag="item"></Tag>
          </template>
        </el-option>
      </el-select>
    </div>
    <div>
      <el-color-picker v-model="color" :show-alpha="false" :predefine="predefineColors" />
    </div>
    <div :style="{ 'background-color': colorRgb }">
      {{ colorRgb }}
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import Tag from '@/components/Tag/Tag.vue'

defineProps({
  tags: {
    type: Array,
    default: () => []
  }
})

const color = ref('rgba(255, 69, 0)')
const predefineColors = ref([
  '#ff4500',
  '#ff8c00',
  '#ffd700',
  '#90ee90',
  '#00ced1',
  '#1e90ff',
  '#c71585',
  'rgb(255, 120, 0)',
  'hsv(51, 100, 98)',
  'hsl(181, 100%, 37%)',
  '#c7158577'
])

const colorToRgb = (color: string) => {
  if (color[0] === '#') {
    const number = color.substring(1)
    const rgbArr = []
    let i = 0
    while (i < number.length) {
      rgbArr.push(Number('0x' + number.substring(i, i + 2)))
      i += 2
    }
    return `rgb(${rgbArr[0]},${rgbArr[1]}, ${rgbArr[2]})`
  }

  return color
}

const colorRgb = computed(() => {
  return colorToRgb(color.value)
})

const value = ref<string[]>([])

const remoteMethod = (value: string) => {
    console.log('jjj', value)
}
</script>
<style>
.el-input__wrapper {
  width: 500px;
}
</style>
