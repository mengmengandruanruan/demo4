<template>
  <el-container class="register_body">
    <el-header><navigation></navigation></el-header>

    <el-container style="text-align: center;margin-top: 20px">
      <el-aside width="600px">
        <div >
          <el-form :model="datafrom" :rules="datarules" ref="datafrom"  @keyup.enter.native="register" label-width="100px">

            <el-form-item label="用户名" prop="username">
              <el-input v-model="datafrom.username" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="datafrom.password" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="重复密码" prop="passwordagain">
              <el-input type="password" v-model="datafrom.passwordagain" autocomplete="off"></el-input>
            </el-form-item>


            <el-button type="primary" @click="register">立即注册</el-button>



          </el-form>

        </div>
      </el-aside >
    </el-container>

  </el-container>

</template>

<script>
  import navigation from './Header.vue'
    export default {
        name: "Register",
        components:{
          navigation
        },

      data()
      {
        //再次输入密码验证
        var againPassword = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.datafrom.password) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };



        return{
          mas:'',





          datafrom:{
            username:'',
            password:'',
            passwordagain:''
          }
          ,

          datarules:{
            username:[
              {required:true,message:"请输入用户名", trigger: 'blur'},
              {pattern:/[a-zA-Z]{1}([0-9]|[a-zA-Z]){4,15}$/,message:'第一位为字母，6-16位的字母或数字',trigger:'blur'}
            ],
            password:[
              {required:true,message:"请输入密码", trigger: 'blur'},
              {pattern:/([0-9]|[a-zA-Z]){5,16}$/,message:'6-16位的字母或数字',trigger:'blur'}
              ]
            ,
            passwordagain:[
              { validator:againPassword, trigger: 'blur' }
            ]
          }





        }

      }
      ,
      methods: {
          register()
          {
            this.$refs['datafrom'].validate((valid) => {
              if (valid)
              {
                this.$http.post("/api/user/register",{"username":this.datafrom.username,"password":this.datafrom.password}).
                then(res=>{
                  if(res.data.msg=='success'){
                    this.$message({message: '恭喜你，注册成功!', type: 'success'});
                  }else
                  {
                    this.$message.error(res.data.msg);
                  }
                })
              }
              else
                {
                  this.$message({message: '请先完善注册信息',   type: 'warning'});
                }
            })
          }




      }
    }
</script>

<style scoped>
  .register_body{
    background-image: url("../assets/myblue.jpg");
    height: 800px;
    padding: 50px;
  }
</style>
