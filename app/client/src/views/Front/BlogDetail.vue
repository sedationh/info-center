<template>
  <div class=" bg-white mt-20 p-20 rounded-md shadow-dark-50" style="min-height: 700px;">
    文章详情
  </div>
</template>

<script setup lang="ts">
import { getArticleDeatil } from '@/api';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();

const id = ref('')
onMounted(() => {
    // 打印
    console.log('router:', router.currentRoute.value.query)
    // id.value = router.currentRoute.value.query.id||''
    // getArticleDeatil({
    //   id: id.value
    // })
  })



</script>

<style>

</style>