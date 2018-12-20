<template>
<div>
  <h1>新闻类别管理</h1>
  <el-button  @click="dialogFormVisible = true">
    新增
  </el-button>
  <el-button  @click="dialogFormVisibledelect = true">
    删除
  </el-button>
 <el-dialog title="新增新闻类别" :visible.sync="dialogFormVisible">

   <el-form :model="form">
     <el-form-item label="商品类别" label-width="100px">
       <el-input v-model="form.goodstypename" auto-complete="off"></el-input>
     </el-form-item>

     <el-form-item label="分类等级" label-width="100px">
       <el-select v-model="form.level" placeholder="请先选择这里" @change="change">
         <el-option label="一级标题" value="1"></el-option>
         <el-option label="二级标题" value="2"></el-option>
       </el-select>
     </el-form-item>


     <el-form-item label="父级名称" label-width="100px" :v-if="this.fathershow">
       <el-select v-model="form.parentid" placeholder="请选择" >
         <el-option
           v-for="(item,key) in fatherlist"
           :key="key"
           :label="item.goodstypename"
           :value="item.goodstypeid">
         </el-option>

       </el-select>
     </el-form-item>

     <div class="dialog-footer">

       <el-button @click="dialogFormVisible = false">取 消</el-button>
       <el-button type="primary" @click="submit">确 定</el-button>

     </div>

   </el-form>
 </el-dialog>

  <el-dialog title="删除新闻类别" :visible.sync="dialogFormVisibledelect">

      <el-cascader
        :options="this.goodstypelist"
        :show-all-levels="false"
        @change="handleChange"
      >
      </el-cascader>

    <div class="dialog-footer">

      <el-button @click="dialogFormVisibledelect = false">取 消</el-button>
      <el-button type="primary" @click="dsubmit">确 定</el-button>

    </div>

  </el-dialog>
</div>
</template>



<script>
  import navigation from '@/components/Header.vue'
  import context_hellow from '@/components/backstage/Context_hellow.vue'
  import context_myPublish from '@/components/backstage/Context_myPublish.vue'

  import { treeDataTranslate } from '@/utils'

    export default {
        name: "index",
      components: {

        context_hellow,
        context_myPublish,
        navigation
      },
      data(){
          return{
            goodstypelist:[],
            goodstypeid:'',

            fathershow:true,
            taglist:[],
            isrc:'',
            shows: {
              context_myPublish:false
            }
            ,


        //     private String goodstypename;
        // private Long parentid;
        // private Integer level;
            dialogFormVisible:false,

            dialogFormVisibledelect:false,

            form:{
              goodstypename:"",
              parentid:"",
              level:""
            },
            fatherlist:[]

          }
      }
      ,
      created(){

        this.getlist()

      }
      ,
      methods: {
          getlist(){
            this.$http.post("/api/menu/selectlist").then(res=> {
              this.taglist = treeDataTranslate(res.body, 'menuid', 'parentid')
            })

            this.$http.post("/api/goods/getAllTypes").then(res=>{
              this.goodstypelist = treeDataTranslate(res.body,"goodstypeid","parentid")

            })
          }
          ,
        test(src){
              this.$ref.context_hellow.style.display='none'
        }
        ,


        change(e)
        {
          if(e==1)
          {
            this.form.parentid = 1
            this.fatherlist=[
              {goodstypename:"无",
                goodstypeid:0
              }
            ]

          }
          else {
              this.$http.post("/api/goods/getTypesbylevel",{"level":e-1}).then(res=>{
                this.fatherlist=res.body;
                console.log(this.fatherlist)
                this.fathershow=false
              })
          }


        }
        ,


        submit()
        {
         this.$http.post("/api/goods/addGoodsType",{"goodstypename":this.form.goodstypename,"level":this.form.level,"parentid":this.form.parentid}).then(res=>{
            if (res.body.msg=="success"){
                this.$message({type:res.body.msg,  message: res.body.msg})
              this.dialogFormVisible = false
            }
         })
        },
        dsubmit(){
            alert(this.goodstypeid)
          this.$http.post("/api/goods/delectTypeById",{"goodstypeid":this.goodstypeid}).then(res=>{
            this.$message({type:res.body.msg,  message: res.body.msg})
          })
        },



        handleChange(value){
          this.goodstypeid=value[value.length-1]
        }


        //   this.$http
        //   ({
        //     url: this.$http.adornUrl(`/newstype/level/${e}`),
        //     method: "get",
        //   }).then(({data})=>{
        //     this.fatherlist = data
        //     console.log(this.fatherlist)
        //   })


        }




    }
</script>

<style scoped>
#context_hellow{display: block}
#context_myPublish{display: none}

.dialog-footer{
  text-align: center;
}
</style>
