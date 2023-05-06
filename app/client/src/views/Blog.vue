<template>
  <div>
    <div class="flex">
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">标题</span>
        <el-input v-model="title" placeholder="请输入标题"></el-input>
      </div>
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">id</span>
        <el-input v-model="status" placeholder="请输入id"></el-input>
      </div>
      <el-button plain type="primary" @click="search()">搜索</el-button>
    </div>
    <div class="mt-20">
      <el-button type="success" plain @click="() => router.push('/admin/publish')" >新建文章</el-button>
    </div>

    <div class="mt-20">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="title" label="文章标题" width="180"> </el-table-column>
        <el-table-column prop="id" label="id" width="80"> </el-table-column>
        <el-table-column prop="content" label="内容" width="380">
          <template #default="scope">
            <p class=" truncate">{{ scope.row.content }}</p>
          </template>
          </el-table-column>
        <el-table-column prop="type" label="标签">
          <template #default="scope">
            <div class="my-10 flex  items-center">
              <span v-for="item in scope.row.tags" :key="item.id" class="mr-5 mb-5">
                <Tag :tag="item"></Tag>
              </span>
            </div>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="status" label="状态" width="80">
          <template #default="scope">
            <div>
              {{ scope.row.status == 0 ? '草稿' : scope.row.status == 1 ? '已发布' : '已删除' }}
            </div>
          </template>
        </el-table-column> -->
        <el-table-column prop="updatedAt" label="时间">
          <template #default="scope">
            <div>
              {{ transfromTime(scope.row.updatedAt) }}
            </div>
          </template>
           </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template #default="scope">
            <div>
              <span @click.stop="adit(scope.row)" class="cursor-pointer hover:text-green-500"> 编辑 </span>
              <span class="ml-20 cursor-pointer hover:text-green-500" @click.stop="del(scope.row)"> 删除 </span>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    
  </div>
</template>
<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Tag from '@/components/Tag/Tag.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getArticles, deleteArticle } from '@/api'
import {transfromTime} from '@/utils/time'

const list = ref([])
const blogList = ref([])
onMounted(() => {
  getList()
})

const getList = () => {
  getArticles()
    .then((res) => {
      if (res.code == 200) {
        blogList.value = res.data.reverse()
        blogList.value.forEach((item) => {
        item.content = item.content
          .split('\n')
          .map((e) => e.replace(/#|```/g, ''))
          .toString().replace(/,/g, '')
      })
      list.value = blogList.value
      }
    })
    .catch((err) => {
      console.log(err, 'err')
    })
}

const router = useRouter()
const title = ref('')
const status = ref('')
const adit = (data) => {
  router.push({
    path: '/admin/publish',
    query: {
      id: data.id
    }
  })
}

const del = (row) => {
  ElMessageBox.confirm('确定删除此文章？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteArticle(row.id).then((res) => {
      if (res.code == 200) {
        list.value = blogList.value.filter((item) => item.id != row.id)
        ElMessage({
          type: 'success',
          message: `删除成功`
        })
      }
    })
  })
}

const search = () => {
  if(!title.value.trim() && !status.value.trim()) {
    list.value = blogList.value
  }
  list.value = blogList.value.filter(item => item.title.includes(title.value) && (item.id == status.value || !status.value))
}
</script>
<style>
.cover-size {
  width: 156px;
  height: 88px;
}
.el-table .cell {
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal;
  height: 50px;
  word-break: break-all;
  display: flex;
  align-items: center;
  padding: 0 12px;
}
.nowrap {
  white-space: nowrap;
}
</style>
