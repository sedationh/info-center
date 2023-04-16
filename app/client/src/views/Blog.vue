<template>
  <div>
    <div class="flex">
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">标题</span>
        <el-input v-model="title" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">状态</span>
        <el-input v-model="status" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex-x-y mr-20">
        <span class="nowrap mr-10">分类</span>
        <el-input v-model="type" placeholder="请输入内容"></el-input>
      </div>
      <el-button plain type="primary">搜索</el-button>
    </div>
    <div class="mt-20">
      <el-button type="success" plain @click="createTitle()">新建文章</el-button>
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
        <el-table-column prop="title" label="文章标题" width="180"> </el-table-column>
        <el-table-column prop="editor" label="编辑器">
          <template #default="scope">
            <div>{{ scope.row.editor == 0 ? '富文本' : 'Markdown' }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="类型">
          <template #default="scope">
            <div>{{ scope.row.type == 0 ? '原创' : '转发' }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="comment" label="可评论">
          <template #default="scope">
            <div>{{ scope.row.comment ? '可评论' : '不可评论' }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <div>
              {{ scope.row.status == 0 ? '草稿' : scope.row.status == 1 ? '已发布' : '已删除' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="时间"> </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template #default="scope">
            <operateButton :dataList="list" :data="scope.row" />
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mt-20 flex-x-y">
      <el-pagination background layout="prev, pager, next" :total="3"> </el-pagination>
    </div>
    <Create :config="obj" :type="1" ref="createRef"></Create>
  </div>
</template>
<script setup lang="ts">
import Create from '../components/Create/Create.vue'
import { reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useRouter } from 'vue-router'
import { data } from '../data/blogList.js'
const router = useRouter()
const obj = reactive({
  title: '新增文章',
  name: '',
  phone: ''
})
let title = ref('')
let status = ref('')
let type = ref('')


let list = ref(data)
console.log(list, 'data')
const createRef = ref()

const createTitle = () => {
  // createRef.value.open()
  router.push('/publish')
}
</script>
<style>
.cover-size {
  width: 156px;
  height: 88px;
}
</style>
