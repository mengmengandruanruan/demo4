<template>

  <el-container  class="login_body">
    <el-header style="line-height: 100px;height: 100px;padding: 10px">
      <navigation></navigation>
    </el-header >

    <el-container >
        <el-row>
            <el-col :span="24">
              <el-main>
              <div>
                <el-form :model="datafrom" :rules="datarules" ref="datafrom"  @keyup.enter.native="login()" label-width="70px" class="demo-ruleFrom">

                  <el-form-item label="用户名" prop="usernamen">
                    <el-input v-model="datafrom.usernamen" autocomplete="off">
                    </el-input>
                  </el-form-item>

                  <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="datafrom.password" autocomplete="off">
                    </el-input>
                  </el-form-item>
                  <div style="width: 100%;text-align: center">
                    <el-button type="primary" @click="gotoRegister">注册用户</el-button>
                    <el-button type="primary" @click="login">立即登陆</el-button>
                  </div>
                </el-form>
              </div>
            </el-main></el-col>
          </el-row>



    </el-container>







  </el-container>
</template>

<script>

  // import 'element-ui/lib/theme-chalk/display.css';
  import navigation from './Header.vue'
    export default {
        name: "Login",
      components:{
        navigation
      },
      data()
      {
       return{
          mas:"",




         datafrom:
           {
             usernamen:'',
             password:''

           },
         datarules:{
           usernamen:[
                { required: true, message: '请输入用户名', trigger: 'blur'}
                ,
                { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur'}
              ],
           password:
             [
               {required:true,message:"请输入密码",trigger:'blur'}
                ,
               { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur'}

              ]
         },



        }
      }
      ,
      create()
      {

      }
      ,


      methods: {
          gotoRegister()
          {
            this.$router.push({name:"Register"})
          }
          ,


          login()
          {
            this.$refs['datafrom'].validate((valid) => {
              if (valid)
              {
                this.$http.post("/api/user/login",{"username":this.datafrom.usernamen,"password":this.datafrom.password}).
                then(res=>{
                   if(res.data.msg=='success'){

                     // if (this.datafrom.username=="admin")
                     // {
                     //
                     //   this.$cookie.set("admin",this.datafrom.username,2)
                     // }

                        if (this.$cookie.get('token')!=null){
                          this.$cookie.delete('token')
                          this.$cookie.set('token',this.datafrom.usernamen,1)

                        } else {

                          this.$cookie.set('token',this.datafrom,1)

                        }



                       this.$message({message: '恭喜你，登陆成功', type: 'success'});

                     this.$router.push({name:"Main"})
                   }else
                     {
                       this.$message.error(res.data.msg);
                     }
                })
              }else
                {
                  this.$message.error('请正确输入用户名与密码');
                }

            })




          },






      }


    }
</script>

<style scoped>
  .login_body{
    background-image: url("../assets/myblue.jpg");
    height: 800px;

  }
</style>
