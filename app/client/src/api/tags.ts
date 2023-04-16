import request from '@/utils/request'

const path = 'tags/'

export const getTags = () =>
  request({
    url: path
  })