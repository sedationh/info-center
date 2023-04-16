<script setup lang="ts">
import { getTags, signin } from './api';

await signin({
  username: "1",
  password: "2"
})
const tags = await getTags();

console.log("tags", { tags })


</script>

<template>
  <RouterView></RouterView>
</template>

<style scoped></style>
