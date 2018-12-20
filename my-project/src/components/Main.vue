<template>
  <el-container class="main_body">
    <el-header  style="line-height: 100px;height: 100px;padding: 10px;background: black">
      <navigation></navigation>
    </el-header>

          <div  style="margin-top: 20px">
            <!--活动轮播图-->
            <el-carousel :interval="4000" type="card" height="340px">
              <el-carousel-item  >
                <img :src="this.imgs[0].url"/>
              </el-carousel-item>


              <el-carousel-item >
                <img :src="this.imgs[1].url"/>
              </el-carousel-item>

              <el-carousel-item >
                <img :src="this.imgs[2].url"/>
              </el-carousel-item>

            </el-carousel>


            <!--<el-tree-->
              <!--:data="this.goodstype"-->
              <!--:props="defaultProps"-->
              <!--accordion-->
              <!--@node-click="handleNodeClick">-->
            <!--</el-tree>-->

            <template v-for="(item, key) in this.goodstype" >
              <div class="max_title" >
                    <button :id="getid(key)" @click="btn($event,key)">{{item.goodstypename}}</button>
                    <div :id="gettagid(key)" style="display: none" class="tag_box" >
                      <span v-for="subitem in item.children"  @click="gotoGoodslist(subitem.goodstypeid)" >{{subitem.goodstypename}}</span>
                    </div>
              </div>
            </template>




          </div>


    <el-footer style="background: black;color: white">
      作者：阮航同学
    </el-footer>
    </el-container>
</template>

<script>
  import navigation from './Header.vue'
  import { treeDataTranslate } from '@/utils'
    export default {
        name: "Main",
      components:{
        navigation
      },
      data()
      {
            return {
                  mas:"",
                  goodstype:[],
                  imgs:[{url:'http://pgivnxfcv.bkt.clouddn.com/banner_01.jpg'},{url:'http://pgivnxfcv.bkt.clouddn.com/banner_02.jpg'},{url:'http://pgivnxfcv.bkt.clouddn.com/banner_03.jpg'}],



                    }
      }
      ,
      created ()
      {
            //初始化商品分类
            this.initGoodstype()
      }
      ,
      methods: {
               //遍历设置id骚操作！！
               getid:function (key)
               {
                 return "person_"+key
               },

              //遍历设置id骚操作！！
              gettagid:function (key)
              {
                return "tag_"+key
              },

              btn(event,key)
              {
                  let id= "tag_"+key
                  event.target.style.display="none"
                  document.getElementById(id).style.display="inline-flex"
              },


              //初始化商品分类
              initGoodstype()
              {
                this.$http.post("/api/goods/getAllTypes").then(res=> {

                      this.goodstype = treeDataTranslate(res.body, 'goodstypeid', 'parentid')


                    })
              }

              ,

              gotoGoodslist(goodstypeid)
              {

                 this.$router.push({name:'Goodslist',
                       query: {
                         goodstypeid: goodstypeid
                       }
                 })
              }






      }




    }
</script>

<style scoped>
  .main_body{
    background-image: url("../assets/myblue.jpg");
    height: 100%;

  }

  *{margin: 0px;padding: 0px}
  .main_body {margin-top: 10px;}

  .el-carousel__item{text-align: center}

  .max_title {display: inline-block;width: 50%;}
  .max_title button{width: 100%;height: 300px;font-size: 8em;border-radius: 10px;float: left;color: white}
  .max_title div{margin-top: 0px; }

  .tag_box{width: 100%;height: 300px;float: left;
    justify-content: flex-start;
    flex-wrap:wrap;

  }

  .tag_box span{display: inline-block;float: left;height: 150px;font-size: 2em;line-height: 150px;margin-left: 10px;padding-left: 15px;padding-right: 15px}




  #person_0{background: #67C23A}
  #person_1{background: #E6A23C}
  #person_2{background: #F56C6C}
  #person_3{background: #909399}
  #person_4{background: #67C23A}
  #person_5{background: #E6A23C}
  #person_6{background: #F56C6C}
  #person_7{background: #909399}

</style>
