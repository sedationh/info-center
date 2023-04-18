import request from '@/utils/request'

const path = '/articles'

type ArticlesInfo = {
  id:string,
  title:string,
  content: string,
  tags:string[]
}
const getArticles = () =>
  request({
    url: path
})

const createArticle = ({title,content, tags}: ArticlesInfo) => {
  request({
    url: path,
    method:'post',
    data: {
      title,
      content,
      tags
    }
  })
}

const getOneArticle = ({id}: ArticlesInfo) => {
  request({
    url: path + '/3',
    data: {
      id
    }
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
    url: path + '/2',
    method:'delete',
    data: {
      id
    }
  })
}


export {getArticles, createArticle, getOneArticle, aditArticle, deleteArticle}