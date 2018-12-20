<template>
  <el-container>
    <el-header>
      <navigation></navigation>
    </el-header>

    <el-container>
      <el-aside>



      </el-aside>

      <el-main>
        <el-form label-position="left" label-width="80px" >
          <el-form-item label="名称">
            <el-input v-model="goods.goodsname"></el-input>
          </el-form-item>
          <el-form-item label="类别">
            <el-cascader
              :options="goodstypes"
              :show-all-levels="false"
              @change="handleChange"
            >
            </el-cascader>
          </el-form-item>
          <el-form-item label="tel">
            <el-input v-model="goods.tel"></el-input>
          </el-form-item>
          <el-form-item label="img">

                   <img width="100px"  v-for="(item,key) in goods.imgurls" :src="item.imgurl" @click="delectimg(item.id,key)">


          </el-form-item>


        </el-form>




      </el-main>
    </el-container>

  </el-container>
</template>

<script>
  import navigation from './Header.vue'
  import { treeDataTranslate } from '@/utils'
  import {genUpToken} from '../assets/utils/qiniutoken'
  export default {
    name: "UpdateGoods",
    components:{
      navigation
    },
    data(){
      return{
        goodsid:''
        ,
        goods:{}
        ,
        goodstypes:[]
        ,
        postData: {token:{},key:""}
        ,
        //图片上传令牌
        postData: {token:{},key:""}
        ,

      }
    },
    created ()
    {
        this.initgoodsid()
        this.initgoods()
        this.initgoodstype()
      var token;
      var policy = {};
      var bucketName = 'rhdbysj-2018';
      var AK ='3pMHq03DNa_DzdeYg0l-rbyAblGw8ZUGlqFcXORH';
      var SK = '2qL9zEQCin2j_tdn2mEA3ULO87YW6oTYUgcLkWir';
      var deadline = Math.round(new Date().getTime() / 1000) + 3600;
      policy.scope = bucketName;
      policy.deadline = deadline;
      token=genUpToken(AK, SK, policy);
      this.postData.token=token;
    },

    methods: {
      //获取goodsid
      initgoodsid(){
        this.goodsid=this.$route.query.goodsid
      },
      //  获取goods
      initgoods(){
        this.$http.post("/api/goods/selectOneById",{"goodsid":this.goodsid}).then(res => {
        this.goods =   res.body.goodsdto
          console.log(this.goods)
        })
      },
      initgoodstype()
      {
        this.$http.post("/api/goods/getAllTypes").then(res=> {

          this.goodstypes = treeDataTranslate(res.body, 'goodstypeid', 'parentid')
          // this.goodstype = res.body
          console.log(this.goodstypes)

        })
      },
      // 商品类别点击事件
      handleChange(value){
        //value数组包含每一级对象的value值
        this.goods.goodstypeid=value[value.length-1]
        console.log(this.goods.goodstypeid)

      },

      delectimg(id,key){
        this.$http.post("/api/goods/delectpricture",{"id":id}).then(res=>{
          this.goods.imgurls.pop(key)
          console.log(res)
        })
      }


    }

  }
</script>

<style scoped>

</style>
