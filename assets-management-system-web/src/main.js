import Vue from "vue"
import VueCompositionAPI, { createApp, h } from "@vue/composition-api"

import App from "./App.vue"
import router from "./router"
import store from "./store/store"
import { setupCache } from 'axios-cache-adapter'
import axios from "axios"

// Element UI
import ElementUI from "element-ui"
import "element-ui/lib/theme-chalk/index.css"

// Font Awesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { far } from '@fortawesome/free-regular-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon, FontAwesomeLayers, FontAwesomeLayersText }
 from '@fortawesome/vue-fontawesome'
 
library.add(fas, far, fab)
 
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('font-awesome-layers', FontAwesomeLayers)
Vue.component('font-awesome-layers-text', FontAwesomeLayersText)

import { Toasted } from "vue-toasted"
import Vuex from "vuex"
import Code from "./data/Code"


Vue.use(VueCompositionAPI)
Vue.use(ElementUI)
// Vue.use(Toasted)
// Vue.prototype.$http = axios
Vue.use(Vuex)
Vue.use(store)

// 检查登录
router.beforeEach((to, from, next) => {
  if (localStorage.getItem('token') /*&& window.localStorage.isLogin === "1"*/) {
    next()
  } else if (to.path !== "/login") {
    let token = localStorage.getItem('token')
    if (token === "null" || token === "" || token === undefined) {
      Vue.prototype.$message({
        message: "检测到您还未登录,请登录后操作！",
        type: "warning",
        duration: 2000
      })
      next({ path: "/login" })
    }
  } else {
    next()
  }
})

// 添加缓存插件
// const cache = setupCache({
//   maxAge: 15 * 60 * 1000 // 缓存有效期为 15 分钟
// })

const api = axios.create({
  // adapter: cache.adapter
})

// 配置公共url
api.defaults.baseURL = "/api"
//添加请求拦截器
api.interceptors.request.use(
  (config) => {
    if (localStorage.getItem('token')) {
      config.headers.common["token"] = localStorage.getItem('token')
    }
    return config;
  },
  (error) => {
    //对请求错误做什么
    return Promise.reject(error)
  }
);

Vue.prototype.$http = api
// http reponse响应拦截器
api.interceptors.response.use(
  (response) => {
    // 如果未登录
    if (response.data.code === Code.NOT_LOGGED_IN) {
      console.log(response.data.state)
      return Code.NOT_LOGGED_IN
    }
    return response
  },
  (error) => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          localStorage.removeItem("token");
          router.replace({
            path: "/login",
            query: { redirect: router.currentRoute.fullPath }, //登录成功后跳入浏览的当前页面
          })
      }
    }
  }
);

const EventBus = new Vue()
export { EventBus }

const app = createApp({
  router,
  render: () => h(App),
});

app.mount("#app")

export { app }
