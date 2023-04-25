import request from '@/utils/request'

const path = '/articles'

type ArticlesInfo = {
  id?: string
  title?: string
  content?: string
  tagIds?: string[]
}
const getArticles = () =>
  request({
    url: path
  })

const createArticle = ({ title, content, tagIds }: ArticlesInfo) => {
  return request({
    url: path,
    method: 'post',
    data: {
      title,
      content,
      tagIds
    }
  })
}

const getArticleDeatil = (id: String) => {
  return request({
    url: path + '/' + id
  })
}

const aditArticle = ({ id, title, content, tagIds }: ArticlesInfo) => {
  return request({
    url: path,
    method: 'put',
    data: {
      id,
      title,
      content,
      tagIds
    }
  })
}

const deleteArticle = (id: string) => {
  return request({
    url: path + '/' + id,
    method: 'delete'
  })
}

export { getArticles, createArticle, getArticleDeatil, aditArticle, deleteArticle }
