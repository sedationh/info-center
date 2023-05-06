<script setup lang="ts">
import { useUserStore } from '@/stores/user'

const store = useUserStore()

console.log(store, 'store')
</script>

<template>
  <RouterView></RouterView>
</template>

<style>
.border-1 {
  border-width: 1px;
}
</style>
