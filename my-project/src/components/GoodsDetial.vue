<template>
  <div>

      <navigation></navigation>

    <el-button type="danger" @click="black()">返回</el-button>
    <el-button v-show="this.guanzhushow" type="primary" @click="guanzhu()" > {{this.guanzhutext}}</el-button>
      <div>
        <div style="float: left;margin-left: 20px;padding: 10px">
          <div >
            <span>{{this.goods.username}}</span>
            同学分享的
            <span>{{this.goods.goodsname}}</span>
            有兴趣的同学可以联系他tel:
            <span>
                 {{this.goods.tel}}
              </span>
          </div>
          <div class="container">
          <h1>有意见可以在这里讲哈。。</h1>
          <template>
          <div >
            <!--评论栏。。-->
            <ul v-for="(item,key) in this.com">
              <li >
                {{item.user.username}}:{{item.text}}
                <br/>
                <span style="color: #409EFF" @click="docom(item.commentid)">回复</span>
                <ul v-for="(subitem,key) in item.children">
                  <li>
                    {{subitem.user.username}}回复{{item.user.username}}:{{subitem.text}}
                  </li>
                </ul>
              </li>
            </ul>
          </div>


            <span @click="docom(0)" style="margin-left: 40px;color: #409EFF">评论</span>



              <transition name="el-fade-in-linear">
                <div width="100%" v-show="this.show" class="com_text">
                  <el-input
                    v-model=" text"
                    type="textarea"
                    style="height: 100%"
                    placeholder="请输入内容"

                    clearable>
                  </el-input>


                  <el-button style="width: 48%" @click="sub()">发表</el-button>
                  <el-button style="width: 48%" @click="over">取消</el-button>
                </div>
              </transition>




          </template>

          </div>



        </div>
      </div>
        <div class="big_img">
        <div >
          <img :src="this.img"/>
        </div>
          <ul >
            <li  v-for="item in this.goods.imgurls">
              <img v-preview="item.imgurl"
                   :src="item.imgurl"
                   preview-title-enable="true"
                   preview-nav-enable="true">
            </li>
          </ul>
        </div>









      </div>






</template>

<script>
  import navigation from './Header.vue'
  import imagePreview from 'vue-image-preview'
  import { treeDataTranslate } from '@/utils'

    export default {
        name: "GoodsDetial",
      components:{
        navigation
      },
        data(){
          return{

            text:'',
            comparentid:'',
            goodsid:'',
            goodstypeid:'',
            goods:{},
            img:'',
            com:[],
            show:false,
            guanzhushow:true,
            guanzhutext:'关注'


          }
        }
        ,
        created() {
          this.initgoodsid()
          this.initgoods()
          this.initcom()
          this.initguanzhutype()
        }
        ,
        methods: {
          //获取商品id
          initgoodsid()
          {
            this.goodsid=this.$route.query.goodsid
          }
          ,
          // 获取goods数据
          initgoods(){
            this.goodsid

            this.$http.post("/api/goods/selectOneById",{"goodsid":this.goodsid}).then(res=> {
              this.goodstypeid = res.body.goodsdto.goodstypeid
              this.goods=res.body.goodsdto
              this.img = res.body.goodsdto.imgurls[0].imgurl

            })
          }
          ,
          //初始化留言
          initcom(){
            this.$http.post("/api/comment/selectByGoodsId",{"goodsid":this.goodsid}).then(res=>{
              this.com = treeDataTranslate(res.body, 'commentid', 'parentid')
              console.log(this.com)
            })
          }

          ,
          black(){
            this.$router.push({name:'Goodslist',
              query: {
                goodstypeid: this.goodstypeid
              }
            })
          },

          //评论回复
          docom(parentid){

            this.show=!this.show

            this.comparentid=parentid;
            if(this.$cookie.get('token')!=null)
            {


            }
            else{
              this.$confirm('进行此操作需要先登陆, 是否登陆?', '提示', {
                confirmButtonText: '登陆',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.$router.push({name:"Login"})
              }).catch(() => {
                // this.$router.push({name:"Main"})
              });
            }
          }
          ,



          // 发表评论
          sub(){

            this.$http.post("/api/comment/save",{"parentid":this.comparentid,"text":this.text,"goodsid":this.goodsid}).then(res=>{
              if(res.body.msg=='success') {
                this.$message({
                  message: '留言成功',
                  type: 'success'
                });
                this.initcom()

              }


              this.show = false;
              this.comparentid = ''
              this.text = ''
            })


          },
          //取消
          over(){
            this.show=!this.show
          },



          //预览
          preview: function($event){

            var dom = $event.currentTarget;
            console.log(dom.getAttribute('src'));
            this.imgzoomSrc = $event.currentTarget.getAttribute("src");
            this.imgzoomBSrc = $event.currentTarget.getAttribute("src");
          },
          guanzhu()
          {
            if(this.$cookie.get('token')!=null) {

                this.$http.post("/api/guanzhu/guanzhu", {"goodsid": this.goodsid}).then(res => {
                  this.initguanzhutype()
                  this.$message({message: res.body.msg, type: 'success'});
                })
            }
            else {
              this.$confirm('进行此操作需要先登陆, 是否登陆?', '提示', {
                confirmButtonText: '登陆',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.$router.push({name:"Login"})
              }).catch(() => {
                // this.$router.push({name:"Main"})
              });
            }

          }
          ,
          initguanzhutype()
          {
            if(this.$cookie.get('token')!=null)
            {
              this.$http.post("/api/guanzhu/gettype",{"goodsid":this.goodsid}).then(res=>{
                // assadasdasdadsadsa
                if(res.body.type==true){
                  this.guanzhutext = "关注"

                }
                else if(res.body.type==false){
                  this.guanzhutext = "取消关注"
                }

              })
            }else
              {
            this.guanzhushow=false
            }


          }




        }


    }
</script>

<style scoped>
  .big_img{
    padding: 20px;
    float: left;
    height: 25em;
    width: 20em;
    display:flex;
    align-items: center;
  }
  .big_img img{
    margin: 0 auto ;
    width: 20em;
    align-items: center;
  }

  .big_img ul{
    margin: 0px;
    padding: 0px;
    list-style: none;

    width: 10em;
}
  .big_img ul li img{
    width: 10em;
  }

  .el-aside{
    overflow-x:hidden;
    overflow-y:hidden
  }
.container{

}
  li{
    list-style:none;
  }

  .com_text{
    width: 100%;
  }

</style>
