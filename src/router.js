import Vue from 'vue';
import Router from 'vue-router';
import Statistic from './views/Statistic.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/statistic',
      component: Statistic
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    
    {
      path: '/game',
      name: 'game',
      // lazy-loaded
      component: () => import('./views/Game.vue')
    }
  ]
});