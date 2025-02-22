import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'projects',
      component: () => import('../views/ProjectsView.vue'),
    },
    { 
      path: '/participations', 
      name: 'participations', 
      component: () => import('../views/ParticipationsView.vue'),
    },
  ],
})

export default router
