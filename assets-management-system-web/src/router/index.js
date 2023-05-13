import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from "../views/HomeView.vue"
import UserLogin from "../views/UserLoginView.vue"
import FunctionalView from "../views/FunctionalView.vue"
import EditView from "../views/EditView.vue"
import FormView from "../views/FormView.vue"
import NewView from "../views/NewView.vue"
import DetailsView from "../views/DetailsView.vue"
import NewAssetsInventoryView from "../views/AssetsInventory/NewAssetsInventoryView.vue"
import AssetsInventoryResultEntryView from "../views/AssetsInventory/AssetsInventoryResultEntryView.vue"
import AssetsInventoryResultView from "../views/AssetsInventory/AssetsInventoryResultView.vue"
import PersonalInfoView from "../views/PersonalInfoView.vue"

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
          path: 'my',
          component: PersonalInfoView,      // 个人信息
        },
        {
          path: ':view',
          component: FunctionalView,
        },
        {
          path: ':view/new',
          component: NewView,
        },
        {
          path: 'assets-inventory/new-inventory',   // 新增资产盘点
          component: NewAssetsInventoryView,
        },
        {
          path: 'assets-inventory/result-entry/:id',  // 盘点结果录入
          component: AssetsInventoryResultEntryView,
        },
        {
          path: 'assets-inventory/result/:id',
          component: AssetsInventoryResultView,       // 查看盘点结果
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
    },

  ]
})

export default router
