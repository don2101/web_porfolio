import Vue from 'vue'
import Router from 'vue-router'

import HomePage from './views/HomePage.vue'
import AboutUs from './views/AboutUs.vue'
import PostsPage from './views/PostsPage.vue'
import PostWrite from './views/posts/PostWrite.vue'
import PostDetail from './views/posts/PostDetail.vue'
import PostUpdate from './views/posts/PostUpdate.vue'

import PortfoliosPage from './views/PortfoliosPage.vue'
import PortfolioWrite from './views/portfolios/PortfolioWrite.vue'
import PortfolioDetail from './views/portfolios/PortfolioDetail.vue'
import PortfolioUpdate from './views/portfolios/PortfolioUpdate.vue'

import AdminPage from './views/AdminPage.vue'


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,

  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },

    {
      path: '/aboutus',
      name: 'aboutus',
      component: AboutUs,
    },

    // post
    {
      path: '/posts',
      name: 'posts',
      component: PostsPage,
    },

    {
      path: '/posts/write',
      name: 'postWrite',
      component: PostWrite,
    },

    {
      path: '/posts/detail',
      name: 'postDetail',
      component: PostDetail,
    },

    {
      path: '/posts/update',
      name: 'postUpdate',
      component: PostUpdate,
    },

    // portfolios
    {
      path: '/portfolios',
      name: 'portfolios',
      component: PortfoliosPage,
    },

    {
      path: '/portfolios/write',
      name: 'portfolioWrite',
      component: PortfolioWrite,
    },

    {
      path: '/portfolios/detail',
      name: 'portfolioDetail',
      component: PortfolioDetail,
    },

    {
      path: '/portfolios/update',
      name: 'portfolioUpdate',
      component: PortfolioUpdate,
    },

    // admin
    {
      path: '/admin',
      name: 'adminPage',
      component: AdminPage,
    },
  ]
})
