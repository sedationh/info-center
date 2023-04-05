<template>
  <div>
    <div class="flex">
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">昵称</span>

        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">手机号</span>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <el-button class="bg-blue-500" type="primary">搜索</el-button>
      <el-button type="success" plain @click="createMemeber()">新增成员</el-button>
    </div>
    <div class="mt-20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="昵称" width="180"> </el-table-column>
        <el-table-column prop="avatar" label="头像" width="180"> </el-table-column>
        <el-table-column prop="phone" label="手机号" width="180"> </el-table-column>
        <el-table-column prop="role" label="角色"> </el-table-column>
        <el-table-column prop="state" label="状态"> </el-table-column>
        <el-table-column prop="time" label="时间"> </el-table-column>
        <el-table-column prop="handle" label="操作"> </el-table-column>
      </el-table>
    </div>
    <div class="mt-20 flex-x-y">
      <el-pagination background layout="prev, pager, next" :total="3"> </el-pagination>
    </div>
    <create :type="4" ref="createRef"></create>
  </div>
</template>
<script setup>
import { ref } from 'vue'

const createRef = ref()
function createMemeber() {
  createRef.value.open()
}
const tableData = [
  {
    name: 'hihi',
    phone: '10101010',
    avatar: '',
    role: 'admin',
    state: 'deded',
    time: '2001',
    handle: 'hhhhhhh'
  },
  {
    name: 'hihi',
    phone: '10101010',
    avatar: '',
    role: 'admin',
    state: 'deded',
    time: '2001',
    handle: 'hhhhhhh'
  },
  {
    name: 'hihi',
    phone: '10101010',
    avatar: '',
    role: 'admin',
    state: 'deded',
    time: '2001',
    handle: 'hhhhhhh'
  }
]
</script>
<style></style>
