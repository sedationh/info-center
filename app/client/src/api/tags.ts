import request from '@/utils/request'

const path = '/tags'

type TagsInfo = {
  name?:string,
  id?: string,
  color?:string
}
const getTags = () =>
  request({
    url: path
})

const createTag = ({name, color}: TagsInfo) => {
  request({
    url: path,
    method:'post',
    data: {
      name,
      color
    }
  })
}

const getOneTag = ({name}: TagsInfo) => {
  request({
    url: path + '/1',
    data: {
      name
    }
  })
}

const aditTag = ({id, name, color}: TagsInfo) => {
  request({
    url: path,
    method:'put',
    data: {
     id,
     name,
     color
    }
  })
}

const deleteTag = ({id}: TagsInfo) => {
  request({
    url: path,
    method:'delete',
    data: {
      id
    }
  })
}


export {getTags, createTag, getOneTag, aditTag, deleteTag}