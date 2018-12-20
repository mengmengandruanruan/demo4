import Vue from 'vue'

// import VueCookie from 'vue-cookie'
import Router from 'vue-router'

import Login from  '@/components/Login'
import Main from  '@/components/Main'
import Register from  '@/components/Register'
import Publish from  '@/components/Publish'
import Goodslist from  '@/components/Goodslist'
import GoodsDetial from  '@/components/GoodsDetial'
import Mypublish from  '@/components/Mypublish'
import Myfrend from  '@/components/Myfrend'
import UpdateGoods from  '@/components/UpdateGoods'
// 后台页面
import Index from  '@/components/backstage/main/index'







  Vue.use(Router)



//传参如果要隐藏参数用params，如果强制刷新不被清除用query

const routes =
  [
    {path: '/', name: 'Main', component: Main,meta: {  keepAlive: true } },
    {path: 'Login', name: 'Login', component: Login,meta: { keepAlive: true }},
    {path:'Register',name:'Register',component:Register,meta: {keepAlive: true }},
    {path:'Publish',name:'Publish',component:Publish,meta: { keepAlive: true }},
    {path:'Goodslist',name:'Goodslist',component:Goodslist,meta: { keepAlive: true }},
    {path:'GoodsDetial',name:'GoodsDetial',component:GoodsDetial,meta: { keepAlive: true }},
    {path:"Mypublish",name:'Mypublish',component:Mypublish,meta: { keepAlive: true }},
    {path:"Mypfrend",name:'Myfrend',component:Myfrend,meta: { keepAlive: true }},
    {path:"UpdateGoods",name:'UpdateGoods',component:UpdateGoods,meta: { keepAlive: true }}
  ]

routes.push(
  {
    path: 'Index',
    name: 'Index',
    component: Index,
    meta: { title: '主入口整体布局', loginState: 0 },
    children:[
      // {path:'BackHome',component:BackHome,name:"BackHome" },
      // {path:'BackHome',component:BackHome,name:"BackHome" }
    ],
    beforeEnter (to, from, next) {

      next()
    }
  }
)



const router = new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  isAddDynamicMenuRoutes: false,
  routes: routes
})


router.beforeEach((to, from, next) => {
  next()
})



export default router
