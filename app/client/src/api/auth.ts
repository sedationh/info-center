import request from '@/utils/request'

const path = 'auth/'

const hasUserRegistered = () =>
  request({
    url: path + 'hasUserRegistered'
  })

type AuthInfo = {
  username: string
  password: string
}

const register = ({ username, password }: AuthInfo) =>
  request({
    url: path + 'register',
    method: 'post',
    data: {
      username,
      password
    }
  })

const login = ({ username, password }: AuthInfo) =>
  request({
    url: path + 'login',
    method: 'post',
    data: {
      username,
      password
    }
  })

export { hasUserRegistered, register, login }
