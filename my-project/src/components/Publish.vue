<template>
  <el-container class="publishbody">
    <el-header style="line-height: 100px;height: 100px;padding: 10px;background: black">
      <navigation></navigation>
    </el-header>

 <el-container>

   <el-aside>

   </el-aside>
   <el-main >
     <el-form :model="goods" :rules="rules" ref="goods"  @keyup.enter.native="register" label-width="100px" class="demo-ruleForm">

       <el-form-item label="宝贝名称" prop="goodsname">
         <el-input v-model="goods.goodsname"></el-input>
       </el-form-item>

       <el-form-item label="宝贝类别" prop="goodstypeid">
         <el-cascader
           :options="this.goodstype"
           :show-all-levels="false"
           @change="handleChange"
         >
         </el-cascader>
       </el-form-item>


       <el-form-item label="联系电话" prop="tel">
         <el-input v-model="goods.tel"></el-input>
       </el-form-item>









     </el-form>
     <div>
       <el-upload

         drag
         list-type="picture-card"
         ref="upload"
         action="https://upload.qiniup.com"
         :before-upload="beforeAvatarUpload"
         :on-remove="removepicture"
         :auto-upload="false"
         :data="postData"
         multiple>
         <el-button size="small" type="primary">点击上传</el-button>

       </el-upload>
     </div>
     <div style="text-align: center">


       <br/>
       <br/>
       <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
       <el-button type="primary" @click="register">马上分享</el-button>
       <el-button type="primary" @click="reset">重置表单</el-button>
     </div>

   </el-main>

   <el-aside>

   </el-aside>
    </el-container>





    </el-container>

</template>

<script>
    import { treeDataTranslate } from '@/utils'
    import {genUpToken} from '../assets/utils/qiniutoken'
    import navigation from './Header.vue'
    export default {
        name: "Publish",
      components:{
        navigation
      },
      data(){
          return{
            goodsid:'',

            //图片上传令牌
            postData: {token:{},key:""}
             ,


            goods:{
                    goodsname:'',
                    goodstypeid:'',
                    tel:'',

                    // price:'',
                  },

            rules:
              {
                goodsname: [
                  { required: true, message: '请输入名称', trigger: 'blur' },
                  { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],

                goodstypeid:[
                  { required: true, message: '请选择类别', trigger: 'blur' }
                  ]
                ,

                tel:[
                  {required:true,message:'请输入手机号码',trigger:'blur'},
                  {pattern:/^(0|86|17951)?(13[0-9]|15[012356789]|16[6]|19[89]]|17[01345678]|18[0-9]|14[579])[0-9]{8}$/,message: '手机号码不正确', trigger: 'blur' }
                ],

                // price:[],
              },



            goodstype:[]


          }
      }
      ,
      created ()
      {
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






        //初始化商品分类
        this.initGoodstype()
      }
      ,
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

        // 商品类别点击事件
        handleChange(value){
          //value数组包含每一级对象的value值
          this.goods.goodstypeid=value[value.length-1]

        },



        register()
        {
          this.$refs['goods'].validate((valid) => {
            if (valid)
            {
              if (this.$cookie.get('token')==null){
                this.$confirm('进行此操作需要先登陆, 是否登陆?', '提示', {
                  confirmButtonText: '登陆',
                  cancelButtonText: '回首页',
                  type: 'warning'
                }).then(() => {
                  this.$router.push({name:"Login"})
                }).catch(() => {
                  this.$router.push({name:"Main"})
                });


              } else {
                this.$http.post("/api/goods/save",{"goodsname":this.goods.goodsname,"goodstypeid":this.goods.goodstypeid,"tel":this.goods.tel}).
                then(res=>{
                  console.log(res.body)
                  if (res.body.msg=="success"){
                    this.$message.success("分享成功")
                    this.goodsid = res.body.goodsid
                    this.$refs.upload.submit()
                  }
                })
              }



             }else
               {
                 this.$message.error('请完善信息');
               }

                                                    })

        }





        ,
        //重置
        reset(){
          this.$confirm('此操作将重置表单, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

            this.$refs.upload.clearFiles()
            this.$refs.goods.resetFields()

            this.$message({
              type: 'success',
              message: '重置成功'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消重置'
            });
          });
        },

          //上传图片前
          beforeAvatarUpload(file) {
            let imgurl = "http://pgivnxfcv.bkt.clouddn.com/"+this.goodsid+file.name
            this.$http.post("/api/goods/pricturesave",{"imgurl":imgurl,"goodsid":this.goodsid})

          const isLt5M = file.size / 1024 / 1024 < 5;
          this.postData.key=this.goodsid+file.name
          if (!isLt5M) {
            this.$message.error('上传头像图片大小不能超过 5MB!');
          }
          return isLt5M;
        },

      // //  上传成功
      //    uploadsuccess(file){
      //
      //
      //
      //
      //
      //
      //    },

      //  移除图片
        removepicture(file){


        },


      dododo(){
          console.log(this.uploadImage)
      }



      }
    }
</script>

<style scoped>
  .publishbody{
    background-image: url("../assets/childrenbg.jpg");
    height:800px;

  }
</style>
