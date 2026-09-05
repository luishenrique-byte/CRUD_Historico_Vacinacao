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

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    if (localStorage.getItem('tipo') !== to.meta.tipo) {
      next('/')
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
