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
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
        name: "Mypublish",
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
        this.initmygoods()
      },

      methods: {
        initmygoods() {
          this.$http.post("/api/goods/selectMyGoods").then(res => {
            this.goodslist = res.body
          })
        }
        ,
        // 编辑
        handleEdit(index, row) {
          this.$router.push({name:"UpdateGoods",
            query: {
              goodsid: row.goodsid
            }}
          )
        },
      //  删除
        handleDelete(index, row) {
          console.log(index, row);

          this.$http.post("/api/goods/delectbyid",{"goodsid":row.goodsid}).then(res=>{
            this.initmygoods()
          })


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
