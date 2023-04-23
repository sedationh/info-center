<template>
  <div>
    <div class="border-1 border-gray-400 relative p-5 flex items-center z-10">
      <Tag v-for="item in list2" :key="item.id" :tag="item" :remove="true" @remove="remove"></Tag>
      <input
        @change="filter()"
        type="text"
        v-model="value"
        class="outline-none"
        placeholder="选择或创建新的标签"
        @focus="
          () => {
            showOptions = true
          }
        "
      />

      <div v-if="showOptions" class="options">
        <div v-for="item in list" :key="item.id" @click.stop="selectTag(item)" class="mb-10">
          <Tag :tag="item"></Tag>
        </div>
        <div v-if="list.length == 0 && value != ''" class="flex justify-between items-center">
          <span @click.stop="addTag()">
            <Tag :tag="defaultTag"></Tag>
          </span>
          <span>
            <el-color-picker v-model="color" :predefine="predefineColors" />
          </span>
        </div>
      </div>
      <div v-if="showOptions" class="close-box" @click="close()"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import Tag from '@/components/Tag/Tag.vue'
import { createTag, getTags } from '@/api'

const tagsList = ref([])
const list = ref([])

const props = defineProps({
  list2: {
    type: Array,
    default: () => []
  }
})
const list2 = ref([])
onMounted(() => {
  get()
})
const get = () => {
  getTags().then((res) => {
    if (res.code == 200) {
      tagsList.value = res.data
      list.value = tagsList.value
    }
  })
}

const showOptions = ref(false)

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

const value = ref('')
const filter = () => {
  list.value = tagsList.value.filter((tag) => tag.name.includes(value.value))
}
const defaultTag = computed(() => {
  return {
    name: value.value,
    color: colorRgb.value,
    id: '11'
  }
})

const addTag = () => {
  tagsList.value.push(defaultTag.value)
  createTag({
    name: value.value,
    color: colorRgb.value
  })
  list.value = tagsList.value
  selectTag(defaultTag.value)
}
setTimeout(() => {
  list2.value = props.list2 || []
}, 200);
const selectTag = (item: Object) => {
  if (list2.value.find((tag) => tag.id === item.id)) {
    list2.value = list2.value.filter((tag) => tag.id !== item.id)
  } else {
    list2.value.push(item)
    value.value = ''
  }
}

const emit = defineEmits(['tags'])
const close = () => {
  showOptions.value = false
  emit('tags', list2.value)
}

const remove = (tag: Object) => {
  list2.value = list2.value.filter((item) => item.id !== tag.id)
}
</script>
<style>
.el-input__wrapper {
  width: 500px;
}
.options {
  position: absolute;
  background-color: #fff;
  padding: 20px;
  top: 40px;
  left: 0px;
  width: 200px;
  z-index: 3;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}
.close-box {
  position: fixed;
  width: 100%;
  height: 100%;
  left: 220px;
  top: 0;
  z-index: -1;
}
</style>
