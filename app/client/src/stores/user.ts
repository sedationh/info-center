import { defineStore } from 'pinia'

interface UserState {
  isUserSet: boolean,
  id?: string,
  name?: string,
  avatar?:string,
  counter?: Number
}

export const useUserStore = defineStore({
  id: 'user',
  state: (): UserState => ({
    isUserSet: false,
    id: '',
    name:'',
    avatar:'',
    counter:3
  }),
  actions: {
    setStatus(status: boolean) {
      this.isUserSet = status
    },
    setCounter(num: number) {
      this.counter = num 
    }
  }
})

