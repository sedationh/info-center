<template>
  <div>
    <div
      @click.stop="toDetail(item)"
      v-for="(item, index) in list"
      :key="index"
      class="pb-20 pointer"
      :class="[index == 0 ? '' : 'mt-20', index < list.length - 1 ? 'border-b-2' : '']"
    >
      <div class="font-bold">{{ item.title }}</div>
      <div class="mt-20 text-gray-500 overflow-ellipsis overflow-hidden truncate" v-html="item.content" style="max-width: 80%;"></div>
      <div class="text-xs mt-20 flex items-center">
        <span class="mr-20 text-gray-400 ">{{transfromTime(item.updatedAt)  }}</span>
        <span class="mr-20" v-if="item.name"> 作者： </span>
        <span class="mr-10" v-for="tag in item.tags" :key="tag.id" @click.stop="selectTag(tag)">
          <Tag :tag="tag"></Tag>
        </span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import Tag from '@/components/Tag/Tag.vue'
import {transfromTime} from '@/utils/time'

defineProps({
  list: {
    type: Array,
    default: () => []
  }
})

const router = useRouter()

function toDetail(item: object) {
  router.push('/index/detail?id=' + item.id)
}

const emits = defineEmits(["selectTag"])



const selectTag = (item: object) => {
    emits("selectTag", item)
}

</script>

<style></style>
