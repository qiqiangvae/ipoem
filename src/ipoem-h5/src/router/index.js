import Vue from 'vue'
import Router from 'vue-router'
import PoemDetails from '@/pages/PoemDetails'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'PoemDetails',
      component: PoemDetails
    }
  ]
})
