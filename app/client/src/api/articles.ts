import request from '@/utils/request'

const path = '/articles'

type ArticlesInfo = {
  id?:string,
  title?:string,
  content?: string,
  tagIds?:string[]
}
const getArticles = () =>
  request({
    url: path
})

const createArticle = ({title,content, tagIds}: ArticlesInfo) => {
  request({
    url: path,
    method:'post',
    data: {
      title,
      content,
      tagIds
    }
  })
}

const getArticleDeatil = (id: String) => {
  request({
    url: path + '/' + id,
  }).then((res) => {
    return res
  })
}

const aditArticle = ({id}: ArticlesInfo) => {
  request({
    url: path,
    method:'put',
    data: {
      id
    }
  })
}

const deleteArticle = ({id}: ArticlesInfo) => {
  request({
    url: path,
    method:'delete',
    data: {
      id
    }
  })
}


export {getArticles, createArticle, getArticleDeatil, aditArticle, deleteArticle}