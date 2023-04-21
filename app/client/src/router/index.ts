import { createRouter, createWebHistory } from 'vue-router'
const routes = [
  {
    path: '',
    redirect: '/index'
  },
  {
    path: '/1',
    component: () => import('@/views/1.vue'),
  },
  {
    name: 'index',
    path: '/index',
    redirect: '/index/tagList',
    component: () => import('@/views/Front/Index.vue'),
    children: [
      {
        name: 'blogList',
        path: '/index/blogList',
        component: () => import('@/views/Front/BlogList.vue')
      },
      {
        name: 'tagList',
        path: '/index/tagList',
        component: () => import('@/views/Front/TagList.vue')
      },
      {
        name: 'blogDetail',
        path: '/index/detail',
        component: () => import('@/views/Front/BlogDetail.vue')
      },
      {
        name: 'user',
        path: '/index/user',
        component: () => import('@/views/Front/User.vue')
      }
    ]
  },
  {
    name: '登录',
    path: '/login',
    component: () => import('@/views/Login.vue')
  },
  {
    name: '首页',
    path: '/admin',
    redirect: '/admin/blog',
    component: () => import('@/views/Index.vue'),
    children: [
      {
        name: '博客管理',
        path: '/admin/blog',
        component: () => import('@/views/Blog.vue')
      },
      {
        name: '创建文章',
        path: '/admin/publish',
        component: () => import('@/views/Publish.vue')
      },
      {
        name: '标签管理',
        path: '/admin/tags',
        component: () => import('@/views/Tags.vue')
      },
      {
        name: '专题管理',
        path: '/admin/specialTopic',
        component: () => import('@/views/SpecialTopic.vue')
      },
      {
        name: '个人信息设置',
        path: '/admin/myInfo',
        component: () => import('@/views/UserInfo.vue')
      },
      {
        name: '成员管理',
        path: '/admin/member',
        component: () => import('@/views/Member.vue')
      },
      {
        name: '系统设置',
        path: '/admin/system',
        component: () => import('@/views/System.vue')
      },
      {
        name: '回收站',
        path: '/admin/retrieve',
        component: () => import('@/views/Retrieve.vue')
      }
    ]
  }
]

const router = createRouter({
  routes,
  history: createWebHistory()
})

export default router
