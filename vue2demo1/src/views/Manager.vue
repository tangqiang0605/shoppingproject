<template>
<div>
  <div v-show="!isLogin">
    <input v-model="password"></input>
    <button @click="tryLogin">ok</button>
  </div>
<!--  <div :v-show="isLogin">-->
    <!--不展示的表格-->
<!--  <div>{{shopkeeperData}}</div>-->
    <el-table
        v-show="isLogin"
        :data="shopkeeperData"
        border
        style="width: 100%">
      <!--      fixed prop label width-->
      <el-table-column
          prop="sid"
          label="商家id"
          width="120">
      </el-table-column>
      <el-table-column
          prop="sname"
          label="店铺名称"
          width="120">
      </el-table-column>
      <el-table-column
          prop="spassword"
          label="密码"
          width="120">
      </el-table-column>
      <el-table-column
          prop="isban"
          label="状态"
          width="120">
        <template slot-scope="scope">
          <el-link type="success" v-show="!scope.row.isban">正常</el-link>
          <el-link type="danger" v-show="scope.row.isban">封禁中</el-link>
<!--          {{scope.row.isban}}-->
        </template>
      </el-table-column>


<!--          fixed="right"-->
      <el-table-column
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="changeState(scope.row,true)" type="text" size="small" v-if="!scope.row.isban">设置封禁</el-button>
          <el-button @click="changeState(scope.row,false)" type="text" size="small" v-if="scope.row.isban">解除封禁</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--  </div>-->
</div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Manager",
  data(){
    return{
      password:'',
      isLogin:false,

      shopkeeperData:[],
    }

  },
  created() {
    // this.password='1234';
  },
  methods:{
    changeState(storeKeeper,state){
      storeKeeper.isban=state;
      axios.post('http://localhost:8181/manager/updatestorekeeperisban',storeKeeper).then(resp=>{

            axios.get('http://localhost:8181/manager/findallstorekeeper').then(resp1=>
            {
              // console.log(storeKeeper);
              this.shopkeeperData=resp1.data;
              // this.isLogin=true;
            })
      }
          // console.log("finish");
      )
    },
    tryLogin(){
      // console.log(this.password);
      axios.get('http://localhost:8181/manager/login?password='+this.password).then(
          resp=>{
            if(resp.data){

              axios.get('http://localhost:8181/manager/findallstorekeeper').then(resp1=>
              {
                this.shopkeeperData=resp1.data;
                this.isLogin=true;
              })

            }
          }
      )
    }
  }
}
</script>

<style scoped>

</style>