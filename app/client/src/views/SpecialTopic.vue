<template>
  <div>
    <div class="">
      <el-button class="bg-green-500" type="success" @click="createTopic()">新建</el-button>
    </div>
    <div class="flex mt-10">
      <el-card class="box-card mr-10 w-50%">
        <template #header>
          <div class="card-header">
            <span>专题</span>
          </div>
        </template>
        <div class="mt-20">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="date" label="封面" width="180"> </el-table-column>
            <el-table-column prop="name" label="名称" width="180"> </el-table-column>
            <el-table-column prop="intro" label="简介"> </el-table-column>
            <el-table-column prop="quantity" label="博客数"> </el-table-column>
            <el-table-column prop="handle" label="操作"> </el-table-column>
          </el-table>
        </div>
      </el-card>
      <el-card class="box-card w-50%">
        <template #header>
          <div class="card-header">
            <span>专题文章</span>
          </div>
        </template>
        <el-alert title="可拖动更改文章排序" type="info" show-icon />

        <div @click="open()" class="flex justify-between">
          <div>
            <span
              class="iconfont text-gray-400"
              :class="show ? 'icon-caret-down' : 'icon-caret-right'"
            ></span>
            专题名称
          </div>
          <div>新增文章</div>
        </div>
        <el-tree v-if="show" :data="treeDate" node-key="id" :expand-on-click-node="false" draggable>
          <template #default="{ node, data }">
            <span class="custom-tree-node">
              <span>{{ node.label }}</span>
              <span class="text-green-500">
                <a @click="append(data)"> 预览 </a>
                <a style="margin-left: 8px" @click="remove(node, data)"> 修改 </a>
                <a @click="append(data)"> 删除 </a>
                <a style="margin-left: 8px" @click="remove(node, data)"> 新增下级文章 </a>
              </span>
            </span>
          </template>
        </el-tree>
      </el-card>
    </div>
    <Create :type="3" ref="createRef"></Create>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import Create from '../components/Create/Create.vue'
let show = ref(false)
function open() {
  show.value = !show.value
}
function create() {
  console.log('新建一个文章')
}
const createRef = ref()
function createTopic() {
  createRef.value.open()
}

const tableData = [
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  },
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  },
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  },
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  },
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  },
  {
    date: '11',
    name: 'hihi',
    intro: '一个专题',
    quantity: '200',
    handle: '1'
  }
]
const tree = [
  {
    label: 'Level one 1',
    edit: true,
    id: '001'
  }
]
const treeDate = [
  {
    label: 'Level one 1',
    edit: true,
    id: '001',
    children: [
      {
        label: 'Level two 1-1',
        edit: false,
        id: '002',
        children: [
          {
            label: 'Level three 1-1-1',
            edit: true,
            id: '003'
          }
        ]
      }
    ]
  }
]
</script>

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>
