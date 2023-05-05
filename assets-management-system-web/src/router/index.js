import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "../views/HomeView.vue"
import UserLogin from "../views/UserLoginView.vue"
import FunctionalView from "../views/FunctionalView.vue"
import EditView from "../views/EditView.vue"
import FormView from "../views/FormView.vue"
import NewView from "../views/NewView.vue"
import DetailsView from "../views/DetailsView.vue"

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  base: import.meta.env.BASE_URL,
  routes: [
    {
      path: '/login',
      component: UserLogin,
    },
    {
      path: '/:identity/home',
      component: Home,
      children: [
        {
          path: ':view',
          component: FunctionalView,
        },
        {
          path: ':view/new',
          component: NewView,
        },
        {
          path: ':view/edit/:id',
          component: EditView,
        },
        {
          path: ':view/details/:id',
          component: DetailsView,
        },
      ]
    }
  ]
})

export default router
