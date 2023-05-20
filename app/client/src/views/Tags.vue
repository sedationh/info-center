<template>
  <div>
    <div class="flex items-center">
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10 whitespace-nowrap">名称</span>
        <el-input v-model="title" placeholder="请输入名称"></el-input>
      </div>
      <div class="flex justify-center items-center nowrap mr-20">
        <span class="nowrap mr-10">id</span>
        <el-input v-model="status" placeholder="请输入id"></el-input>
      </div>
      <el-button plain type="primary" @click="search()">搜索</el-button>
    </div>
    <div class="mt-20">
      <el-button type="success" plain @click="open(2)">新增标签</el-button>
    </div>

    <div class="mt-20" style="height: 480px;overflow-y: auto;">
      <el-table :data="tagsList" style="width: 100%">
        <el-table-column prop="name" label="名称" width="300"> </el-table-column>
        <el-table-column prop="id" label="id" width="300"> </el-table-column>
        <el-table-column prop="color" label="颜色" width="500">
          <template #default="scope">
            <div class="text-white" :style="{ 'background-color': scope.row.color }">
              {{ scope.row.color }}
            </div>
          </template>
        </el-table-column>
        <!-- <el-table-column prop="id" label="相关文章id">
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
        </el-table-column> -->
        <el-table-column prop="operate" label="操作" width="500">
          <template #default="scope">
            <div>
              <span class="mr-10 hover:text-green-500 cursor-pointer" @click="open(3, scope.row)"
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
    <Create ref="createRef" @success="success"></Create>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, nextTick  } from 'vue'
import Create from '@/components/Create/Create.vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { aditTag, createTag, deleteTag, getTags } from '@/api'

const tagsList = ref([])
const list = ref([])
onMounted(() => {
  get()
})
const get = () => {
  getTags().then((res) => {
    if (res.code == 200) {
      list.value = res.data
      tagsList.value = list.value
    }
  })
}
const open = (num: number, row: number) => {
  createRef.value.open(num, row)
}
const delTag = (row) => {
  ElMessageBox.confirm('确定删除此标签？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    deleteTag(row.id).then((res) => {
      if (res.code == 200) {
        tagsList.value = list.value.filter((item) => item.id != row.id)
        ElMessage({
          type: 'success',
          message: `删除成功`
        })
      }
    })
  })
}
const success = (data) => {
  if (!data.name || !data.color) {
    ElMessage({
      message: '请填写正确的名称和颜色',
      type: 'warning'
    })
  } else {
    if (data.id) {
      aditTag1(data)
    } else {
      newTag(data)
    }
    get()
    tagsList.value.push(data)
    createRef.value.close()
  }
}
const newTag = (data) => {
  createTag({
    name: data.name,
    color: data.color
  }).then((res) => {
    if (res.code == 200) {
      ElMessage({
        message: '创建标签成功',
        type: 'success'
      })
    }
  })
}
const aditTag1 = (data) => {
  aditTag({
    name: data.name,
    color: data.color,
    id: data.id
  }).then((res) => {
    if (res.code == 200) {
      ElMessage({
        message: '修改标签成功',
        type: 'success'
      })
    }
  })
}

const title = ref('')
const status = ref('')

const search = () => {
  if(!title.value.trim() && !status.value.trim()) {
    tagsList.value = list.value
  }
  tagsList.value = list.value.filter(item => item.name.includes(title.value) && (item.id == status.value || !status.value))
}
const createRef = ref()
</script>
<style></style>
