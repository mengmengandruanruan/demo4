// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import App from './App'
import router from './router'
import VueCookie from 'vue-cookie'
import ElementUI from 'element-ui'
// 图片查看器
import VuePicturePreview from 'vue-picture-preview'




import 'element-ui/lib/theme-chalk/index.css'


import  VueResource  from 'vue-resource'

Vue.config.productionTip = false


// 图片查看器
Vue.use(VuePicturePreview);
Vue.use(VueCookie)
//引用element ui
Vue.use(ElementUI)
//引用vue-resource
Vue.use(VueResource)






/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})


//
// var qiniu = require("qiniu");
// //需要填写你的 Access Key 和 Secret Key
// qiniu.conf.ACCESS_KEY = 'Access_Key';
// qiniu.conf.SECRET_KEY = 'Secret_Key';
// //要上传的空间
// bucket = 'Bucket_Name';
// //上传到七牛后保存的文件名
// key = 'my-nodejs-logo.png';
// //构建上传策略函数，设置回调的url以及需要回调给业务服务器的数据
// function uptoken(bucket, key) {
//   var putPolicy = new qiniu.rs.PutPolicy(bucket+":"+key);
//   putPolicy.callbackUrl = 'http://your.domain.com/callback';
//   putPolicy.callbackBody = 'filename=$(fname)&filesize=$(fsize)';
//   return putPolicy.token();
// }
// //生成上传 Token
// token = uptoken(bucket, key);
// //要上传文件的本地路径
// filePath = './nodejs-logo.png'
// //构造上传函数
// function uploadFile(uptoken, key, localFile) {
//   var extra = new qiniu.io.PutExtra();
//   qiniu.io.putFile(uptoken, key, localFile, extra, function(err, ret) {
//     if(!err) {
//       // 上传成功， 处理返回值
//       console.log(ret.hash, ret.key, ret.persistentId);
//     } else {
//       // 上传失败， 处理返回代码
//       console.log(err);
//     }
//   });
// }
// //调用uploadFile上传
// uploadFile(token, key, filePath);
