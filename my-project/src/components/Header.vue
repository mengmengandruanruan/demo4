<template>
  <el-row type="flex" justify="end"  class="header">
    <el-col :span="12" >
      <img src="http://pgivnxfcv.bkt.clouddn.com/logo.jpeg" width="80px" height="80px" style="border-radius: 50%">
    </el-col>

    <el-col :span="2">

      <el-button @click="this.gotoMain" plain>回到首页</el-button>
    </el-col>
    <el-col :span="2">
      <el-button type="primary" plain @click="this.gotoPublish">发布法宝</el-button>
    </el-col>
    <el-col :span="2">
      <el-button type="success" plain  @click="this.gotoMypublish">我发布的</el-button>
    </el-col>
    <el-col :span="2">

      <el-button type="info" plain  @click="this.gotoMyfrend">我关注的</el-button>
    </el-col>


    <template v-if="islogin">
      <template v-if="isadmin">
      <el-col :span="2">

          <el-button type="warning" @click="gotoBackmain" plain>管理类别</el-button>


      </el-col>
      </template>
      <el-col :span="2">
        <el-button  type="danger" @click="outlogin" plain >退出登陆</el-button>
      </el-col>

    </template>
    <template v-else>
      <el-col :span="2">

        <el-button type="warning" @click="gotoRegister"  plain>注册用户</el-button>
      </el-col>
      <el-col :span="2">
        <el-button  @click="gotoLogin" type="danger" plain>用户登陆</el-button>
      </el-col>
    </template>


  </el-row>
</template>

<script>
  import 'element-ui/lib/theme-chalk/display.css';
    export default {
        name: "Header"
      ,
      data () {
        return {
             msg:'',
              islogin:this.$cookie.get('token')!=null,
              isadmin:true

        }
      },
      create()
      {

      }
      ,

      methods:{
          //退出登陆
          outlogin(){
            this.$cookie.delete('token')
            this.$cookie.delete("admin")
            this.$http.post("/api/user/outlogin").then(res=>{
              console.log(res.body)
            })


            this.islogin=false
          },

          //登陆后
          oldlogin(){
            this.islogin=true
          },


          gotoMain()
          {
            this.$router.push({name:"Main"})
          },

          gotoPublish()
          {
            this.$router.push({name:'Publish'})
          }

          ,


          gotoLogin()
          {

            this.$router.push({name:'Login'})
          },

          gotoRegister()
          {
            this.$router.push({name:"Register"})
          },

          gotoBackmain()
          {
            this.$http.get("/api/user/isadmin").then(res=>{

              if (res.body.msg=='success'){

                this.$router.push({name:"Index"})
              }
              else {
                this.$message({
                  type: 'info',
                  message: '该操作需要管理员权限'
                });
              this.isadmin=false
              }

            })



          },

          gotoMypublish()
          {
            if(this.$cookie.get('token')!=null)
            {
              this.$router.push({name:"Mypublish"})
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

        gotoMyfrend(){
          if(this.$cookie.get('token')!=null)
          {
            this.$router.push({name:"Myfrend"})
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


        }
    }
</script>

<style scoped>

  /*.logo{*/
    /*background-image: url("@\assets\logo.jpeg");*/
  /*}*/

</style>
