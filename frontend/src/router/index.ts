import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Tabs.vue'
import Login from '../views/Login.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/tabs/',
    component: Tabs,
    children: [
      {
        path: '',
        redirect: '/tabs/tab1'
      },
      {
        path: 'overview',
        component: () => import('@/views/Overview.vue')
      },
      {
        path: 'overview/:id',
        component: () => import('@/views/ToDoDetail.vue'),
      },
      {
        path: 'allTodos',
        component: () => import('@/views/Tasks.vue')
      },
      {
        path: 'projects',
        component: () => import('@/views/Projects.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
