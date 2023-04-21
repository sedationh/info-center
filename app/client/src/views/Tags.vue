<template>
  <div>
    <div>
      <el-button type="success" plain @click="createClassify()">新增标签</el-button>
    </div>

    <div class="mt-20">
      <el-table :data="tagsList" style="width: 100%">
        <el-table-column prop="name" label="名称" width="180"> </el-table-column>
        <el-table-column prop="id" label="id" width="180"> </el-table-column>
        <el-table-column prop="color" label="颜色" width="180">
          <template #default="scope">
            <div class="text-white" :style="{ 'background-color': scope.row.color }">
              {{ scope.row.color }}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="id" label="相关文章id">
          <template #default="scope">
            <div>
              <el-tooltip
                class="box-item border-none"
                effect="light"
                content="某某文章文章某某文章"
                placement="top"
              >
                <span>{{ scope.row.id }}</span>
              </el-tooltip>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="operate" label="操作">
          <template #default="scope">
            <div>
              <span class="mr-10 hover:text-green-500 cursor-pointer" @click="editTag(scope.row)"
                >编辑</span
              >
              <span class="hover:text-green-500 cursor-pointer" @click="delTag(scope.row)"
                >删除</span
              >
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
<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue'
import Create from '@/components/Create/Create.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { createTag, deleteTag, getOneTag, getTags } from '@/api'

const tagsList = ref([])
const list = ref([])
onMounted(() => {
  get()
})
const get = () => {
  getTags().then((res) => {
    if (res.code == 200) {
      tagsList.value = res.data
    }
  })
}
function createClassify() {
  console.log('新建一个文章')
  createRef.value.open({
    name: '名称',
    color: '#22C55E',
    right: '创建',
    success: ()=> {
      createTag({
        name: name,
        color: color
      }).then(res => {
        if(res.code == 200) {
          console.log(res)
        }
      })
    }
  })
}
function editTag(row) {
  console.log(row)
}
const delTag = (row) => {
  ElMessageBox.confirm('确定删除此标签？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteTag(row.id).then((res) => {
      console.log(res, 'res')
      if (res.code == 200) {
        tagsList.value = tagsList.value.filter((item) => item.id != row.id)
        ElMessage({
          type: 'success',
          message: `删除成功`
        })
      }
    })
  })
}
const createRef = ref()
</script>
<style></style>
