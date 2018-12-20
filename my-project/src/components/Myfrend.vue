<template>
  <el-container class="mypublishbody">
    <el-header style="line-height: 100px;height: 100px;padding: 10px;background: black">
      <navigation></navigation>
    </el-header>
    <el-main>
      <el-table :data="goodslist" style="width: 100%">
        <el-table-column label="名称" width="180">
          <template slot-scope="scope">
            <i class="el-icon-menu"></i>
            <span style="margin-left: 10px">{{ scope.row.goodsname}}</span>
          </template>
        </el-table-column>


        <el-table-column label="种类" width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.goodstypeid}}</span>
          </template>
        </el-table-column>

        <el-table-column label="tel" width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.tel}}</span>
          </template>
        </el-table-column>

        <el-table-column label="发布人" width="180">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.username}}</span>
          </template>
        </el-table-column>

        <el-table-column label="图片" width="180">
          <template slot-scope="scope">
           <span v-for="item in scope.row.imgurls">
             <img style="width: 100px;height: 50px" :src="item.imgurl" />
           </span>

          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template slot-scope="scope">


            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row)">取消关注</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>


  </el-container>
</template>

<script>
  import navigation from './Header.vue'
  import { treeDataTranslate } from '@/utils'
  export default {
    name: "Myfrend",
    components:{
      navigation
    },
    data(){
      return{
        goodslist:[]
      }
    },
    created ()
    {
      this.initgoodlist()
    },

    methods: {
      initgoodlist(){
        this.$http.post("/api/guanzhu/getgoodslist").then(res => {
          this.goodslist = res.body
          console.log(this.goodslist)
        })
      }
      ,
      handleDelete(row) {

        if(this.$cookie.get('token')!=null) {
          this.$http.post("/api/guanzhu/guanzhu", {"goodsid": row.goodsid}).then(res => {
            this.initgoodlist()
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



    }


  }
</script>

<style scoped>

  .mypublishbody{
    background-image: url("../assets/childrenbg.jpg");
    height:800px;
  }
</style>
