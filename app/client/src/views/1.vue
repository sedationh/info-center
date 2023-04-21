<template>
  <div>
    <el-select-v2
      v-model="value1"
      :options="options"
      placeholder="Please select"
      style="width: 500px; margin-right: 16px; vertical-align: middle; background-color: pink"
      allow-create
      filterable
      multiple
      clearable
    >
      <template #prefix>
        <!-- <Tag :tag="tag"></Tag> -->
        <span>jjjjjj</span>
      </template>
    </el-select-v2>
  </div>
</template>
<script setup lang="ts">
import { ElSelectV2 } from 'element-plus'
import { ref } from 'vue'
import Tag from '@/components/Tag/Tag.vue'
const initials = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']

const value1 = ref([])
const value2 = ref()
const value3 = ref([])
const options = Array.from({ length: 1000 }).map((_, idx) => ({
  value: `Option ${idx + 1}`,
  label: `${initials[idx % 10]}${idx}`
}))

const tag = {
  color: 'reb(0, 0, 0)',
  name: 'jj',
  id: 'oooo'
}
</script>
