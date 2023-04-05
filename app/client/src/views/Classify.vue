<template>
  <div>
    <div>
      <el-button type="success" plain @click="createClassify()">新增分类</el-button>
    </div>

    <div class="mt-20">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="cover" label="封面" width="180">
          <template #default="scope">
            <img v-if="scope.row.cover" :src="scope.row.cover" class="cover-size" alt="" />
            <div v-else class="cover-size bg-green-600 text-white flex items-center justify-center">
              {{ scope.row.title.slice(0, 5) }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="名称" width="180"> </el-table-column>
        <el-table-column prop="intro" label="简介"> </el-table-column>
        <el-table-column prop="quantity" label="博客数"> </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template #default="scope">
            <div>
              <span class="mr-10 hover:text-green-500 cursor-pointer" @click="editor(scope.row)"
                >编辑</span
              >
              <span class="hover:text-green-500 cursor-pointer" @click="del(scope.row)">删除</span>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mt-20 flex-x-y">
      <el-pagination background layout="prev, pager, next" :total="3"> </el-pagination>
    </div>
    <Create :type="2" ref="createRef"></Create>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
import Create from '../components/Create/Create.vue'
import { data } from '../data/classifyList'

const list = ref(data)
function create() {
  console.log('新建一个文章')
}
function handleEdit(index, row) {
  console.log(index, row)
}
function handleDelete(index, row) {
  console.log(index, row)
}
const createRef = ref()
function createClassify() {
  createRef.value.open()
}

const tableData = [
  {
    titlePage: '11',
    name: 'hihi',
    intro: '000',
    numbers: '19',
    handle: 'ppp'
  },
  {
    titlePage: '11',
    name: 'hihi',
    intro: '000',
    numbers: '19',
    handle: 'ppp'
  },
  {
    titlePage: '11',
    name: 'hihi',
    intro: '000',
    numbers: '19',
    handle: 'ppp'
  }
]
</script>
<style></style>
