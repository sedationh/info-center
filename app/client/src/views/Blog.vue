<template>
  <div>
    <div class="flex">
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">标题</span>
        <el-input v-model="title" placeholder="请输入内容"></el-input>
      </div>
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">状态</span>
        <el-input v-model="status" placeholder="请输入内容"></el-input>
      </div>
      <!-- <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">分类</span>
        <el-input v-model="type" placeholder="请输入内容"></el-input>
      </div> -->
      <el-button plain type="primary">搜索</el-button>
    </div>
    <div class="mt-20">
      <el-button type="success" plain @click="() => router.push('/admin/publish')">新建文章</el-button>
    </div>

    <div class="mt-20">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="title" label="文章标题" width="180"> </el-table-column>
        <el-table-column prop="id" label="id" width="180"> </el-table-column>
        
        <el-table-column prop="intro" label="文章简介" width="380"> 
          <template #default="scope">
            <div class="truncate">
              {{ scope.row.intro }}迪斯科解放和上雕刻技法和四道口附近建设顽皮的散热架上次上课仍可见
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="type" label="标签">
          <template #default="scope">
            <div class="my-10">
              <span v-for="item in scope.row.tags" :key="item.id" class="mr-5 mb-5">
                <Tag :tag="item"></Tag>
              </span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <div>
              {{ scope.row.status == 0 ? '草稿' : scope.row.status == 1 ? '已发布' : '已删除' }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="updatedAt" label="时间"> </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template #default="scope">
            <div>
              <span @click.stop="adit(scope.row)"> 编辑 </span>
              <span class="ml-20" @click.stop="del(scope.row)"> 删除 </span>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mt-20 flex-x-y">
      <el-pagination background layout="prev, pager, next" :total="3"> </el-pagination>
    </div>

  </div>
</template>
<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import Tag from '@/components/Tag/Tag.vue'
import { getArticles } from '@/api'

const list = ref([])

onMounted(() => {
  getList()
})

const getList = ()=> {
  getArticles().then((res) => {
    if(res.code == 200) {
      list.value = res.data
    }
  }).catch((err) => {
    console.log(err, 'err')
  })
}

const router = useRouter()
const title = ref('')
const status = ref('')
const adit = (data) => {
  router.push({
    path:'/admin/publish',
    query: {
      id: data.id
    }
  })
}

const del = (data) => {
  console.log(data, 'del')
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
