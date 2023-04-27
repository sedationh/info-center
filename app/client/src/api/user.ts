import request from '@/utils/request'

const path = '/user'

type UserInfo = {
  nickname: string
  avatar: string
  phone: string
  hobby: string
  album: string
}

const aditUserInfo = ({ nickname, avatar, phone, hobby, album  }: UserInfo) =>
  request({
    url: path + '/config',
    method: 'put',
    data: {
        nickname, avatar, phone, hobby, album
    }
  })

const getUserInfo = () =>
   request({
    url: path + '/config',
  })

const uploadImg = (file : File) => request({
    url: '/upload/images',
    method: 'post',
    data: file
})


export { aditUserInfo, getUserInfo, uploadImg }
