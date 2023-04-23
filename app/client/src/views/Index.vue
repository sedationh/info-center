<template>
  <div class="bg-grey">
    <div class="common-layout">
      <el-container>
        <el-header class="flex justify-between bg-white items-center">
          <div @click="toIndex()">easyblog</div>
          <div>欢迎</div>
        </el-header>
        <el-container>
          <el-aside width="200px" class="mt-20">
            <el-menu :unique-opened="true" class="el-menu-vertical-demo bg-grey border-null">
              <el-sub-menu v-for="(item, index1) in list" :key="index1" :index="index1">
                <template #title>
                  <i class="el-icon-location"></i>
                  <span class="color-333">{{ item.name }}</span>
                </template>
                <div v-for="(item1, index) in item.group" :key="index" class="hover:bg-white">
                  <el-menu-item
                    @click="open(item1)"
                    class="color-333 menu-item"
                    :class="currentName == item1.name ? 'bg-white' : 'bg-grey'"
                    >{{ item1.name }}</el-menu-item
                  >
                </div>
              </el-sub-menu>
            </el-menu>
          </el-aside>
          <el-main style="background-color: #fff; margin: 20px; border-radius: 10px">
            <div style="min-height: 600px">
              <RouterView></RouterView>
            </div>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
let currentName = ref('')

interface GroupItem {
  name: string
  url: string
}

function open(item: GroupItem) {
  if (item.name) {
    currentName.value = item.name
    router.push(item.url)
  }
}
function toIndex() {
  router.push('/admin/blog')
}

const list = [
  {
    name: '博客',
    group: [
      {
        name: '博客管理',
        url: '/admin/blog'
      },
      {
        name: '标签管理',
        url: '/admin/tags'
      }
    ]
  },
  {
    name: '设置',
    group: [
      {
        name: '个人信息设置',
        url: '/admin/myInfo'
      },
      {
        name: '系统设置',
        url: '/admin/system'
      }
    ]
  },
  // {
  //   name: '回收站',
  //   group: [
  //     {
  //       name: '回收站',
  //       url: '/admin/retrieve'
  //     }
  //   ]
  // }
]
</script>

<style scoped>
.bg-grey {
  background-color: #f5f6f7;
}

.color-333 {
  color: #333;
}

.border-null {
  border: none;
}

:deep(.el-sub-menu__title:hover) {
  background-color: #fff;
}
</style>
