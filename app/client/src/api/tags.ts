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
  return request({
    url: path,
    method:'post',
    data: {
      name,
      color
    }
  })
}

const getOneTag = (id: string|number) => {
  return request({
    url: path + '/' + id,

  })
}

const aditTag = ({id, name, color}: TagsInfo) => {
  return request({
    url: path,
    method:'put',
    data: {
     id,
     name,
     color
    }
  })
}

const deleteTag = (id: string) => {
  return request({
    url: path + '/' + id,
    method:'delete',
  })
}


export {getTags, createTag, getOneTag, aditTag, deleteTag}
