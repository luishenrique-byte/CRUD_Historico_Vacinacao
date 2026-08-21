import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../features/home/HomePage.vue'
import AuthPage from '../features/auth/AuthPage.vue'

const routes = [
  { path: '/', component: HomePage },
  { path: '/auth/:tipo', component: AuthPage },
  { path: '/:pathMatch(.*)*', redirect: '/' },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
