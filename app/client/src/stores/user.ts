import { defineStore } from 'pinia'

interface UserState {
  isUserSet: boolean
  id?: string
  nickname?: string
  avatar?: string
  phone?: string
  hobby?: string
  album?: string
  counter?: Number
}

export const useUserStore = defineStore({
  id: 'user',
  state: (): UserState => ({
    isUserSet: false,
    id: '',
    nickname: '',
    avatar: '',
    phone: '',
    hobby: '',
    album: '',
    counter: 3
  }),
  actions: {
    setStatus(status: boolean) {
      this.isUserSet = status
    },
    setCounter(num: number) {
      this.counter = num
    },
    setUserInfo({ id, nickname, avatar, phone, album, hobby }: UserState) {
      console.log('更改用户信息')
      this.id = id
      this.nickname = nickname
      this.avatar = avatar
      this.phone = phone
      this.album = album
      this.hobby = hobby
    }
  }
})
