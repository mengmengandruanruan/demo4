<template>
  <el-container class="goodslistbody">
    <el-header  style="line-height: 100px;height: 100px;padding: 10px;background: black">
      <navigation></navigation>
    </el-header>
  <el-container>
    <el-aside style="background: white;">
      <el-tree
        :data="goodstype"
        accordion
        @node-click="handleNodeClick"
      style="width: 100%">
      </el-tree>
    </el-aside>

    <el-main>
      <ul >
        <li v-for="(item,key) in goodslist" :key="key" class="goods_box">
          <div class="img_box"  >
            <img :src="item.imgurls[0].imgurl" @click="gotoDetial(item.goodsid)"/>
            <span  @click="gotoDetial(item.goodsid)">{{item.goodsname}}
                  </span>
          </div>

          <div class="item_box">


            <!--{{item}}-->
          </div>
        </li>
      </ul>
    </el-main>
  </el-container>


  </el-container>
</template>

<script>
  import { treeDataTranslate } from '@/utils'
  import navigation from './Header.vue'
    export default {
        name: "Goodslist",
        components:{
          navigation
        },
        data(){
              return{
                //商品类别
                goodstype:[],
                goodstypeid:'',
                goodslist:[]
              }
        }
      ,
        created() {
          //初始化商品分类
          this.initGoodstype();
          //初始化类别参数
          this.getgoodstypeid()
          this.initlist()
        },
        methods: {
          //初始化商品分类
          initGoodstype()
          {
            this.$http.post("/api/goods/getAllTypes").then(res=> {

              this.goodstype = treeDataTranslate(res.body, 'goodstypeid', 'parentid')
              // this.goodstype = res.body
              console.log(this.goodstype)

            })
          },


          //获取商品类别id
          getgoodstypeid()
          {
            this.goodstypeid=this.$route.query.goodstypeid
          }
          ,
          //初始化商品列表
          initlist()
          {
            this.$http.post("/api/goods/goodlist",{"goodstypeid":this.goodstypeid}).then(res=> {

              this.goodslist = res.body
              console.log(this.goodslist)
            })
          },

          handleNodeClick(data){

            if (data.level==2){
             this.goodstypeid = data.goodstypeid
              this.initlist()
            }
          }
          ,

          gotoDetial(goodsid)
          {
            this.$router.push({name:'GoodsDetial',
              query: {
                goodsid: goodsid
              }
            })
          }



        }
    }

</script>

<style scoped>



  .goodslistbody{
    background-image: url("../assets/childrenbg.jpg");
    height:800px;

  }


  .content{display: flex;width: 100%}
  .el-tree-node__label{
    font-size: 33px;
  }


  .el-tree{
    color: black;
    width: 10em;
  }

  .el-tree-node__content{
    width: 4em;
    height: 1em;

  }

  .el-icon-caret-right:before{
   display: none;
 }











  .goods_box{display: inline-flex;list-style:none;}

  .item_box{}

  .item_box span{background: black;color: white;display: block;width: 20em;height: 4em;line-height: 4em;text-align: center}


  .img_box{width: 15em;height:15em;display: inline-flex;padding: 1em;flex-direction:column;}
  .img_box span{display: block; width: 15em;background: black;text-align: center;color: white;font-size: 1em;line-height: 30px;box-shadow: 10px 10px 5px #888888;}
  .img_box img{width: 15em;height: 15em;box-shadow: 10px 10px 5px #888888;}
</style>
