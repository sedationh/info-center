import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/components/commen/iconfont.css'
import './index.css'
import { store } from './stores'
 
import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(store)
app.use(router)

app.use(ElementPlus)

app.mount('#app')
