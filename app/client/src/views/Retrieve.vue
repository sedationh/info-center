<template>
  <div>
    <div class="flex">
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">标题</span>

        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">状态</span>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">分类</span>
        <el-input v-model="input" placeholder="请输入内容"></el-input>
      </div>
      <el-button class="bg-blue-500" type="primary">搜索</el-button>
    </div>

    <div class="mt-20">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="date" label="封面" width="180"> </el-table-column>
        <el-table-column prop="name" label="文章" width="180"> </el-table-column>
        <el-table-column prop="address" label="编辑器"> </el-table-column>
        <el-table-column prop="address" label="评论"> </el-table-column>
        <el-table-column prop="address" label="状态"> </el-table-column>
        <el-table-column prop="address" label="时间"> </el-table-column>
        <el-table-column prop="address" label="操作">
          <template #default>
            <div>删除</div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mt-20 flex-x-y">
      <el-pagination background layout="prev, pager, next" :total="3"> </el-pagination>
    </div>
  </div>
</template>
<script setup>
function create() {
  console.log('新建一个文章')
}
const tableData = [
  {
    date: '11',
    name: 'hihi',
    address: '000'
  },
  {
    date: '11',
    name: 'hihi',
    address: '000'
  },
  {
    date: '11',
    name: 'hihi',
    address: '000'
  }
]
</script>
<style></style>
