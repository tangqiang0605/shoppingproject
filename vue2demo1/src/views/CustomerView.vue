<template>
  <div>
    <!--    测试信息-->
    {{ testInf }}
    <!--    导航栏-->
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="toCart">购物车</el-menu-item>
          <el-menu-item index="2" @click="toOrder">订单</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item></el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="4">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item>您好！{{ customer.cname }}</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>

    <el-row v-show="activeIndex==='1'">
      <el-row v-for="(item,index) in shopsData" style="margin: 40px">
        <el-card shadow="hover">
          <el-row>
            <h4>{{ item.sname }}</h4>
          </el-row>
          <el-row v-for="(cart,cartIndex) in cartsData[index]" style="height: 150px">
            <el-col span="6">
              <el-image :src="cart.srcurl" style="height: 120px" fit="contain"></el-image>
            </el-col>
            <el-col span="10">
              <div>
                <!--              {{ cart }}-->
                <h3>{{ cart.gname }}</h3>
                已选数量：
                <el-button icon="el-icon-minus" @click="changeAmount(index,cartIndex,false)"></el-button>
                &nbsp;&nbsp;{{ cart.oamount }}
                <el-button icon="el-icon-plus" @click="changeAmount(index,cartIndex,true)"></el-button>
                <!--        {{cart.gname}}{{cart.oamount}}{{cart.srcurl}}-->
              </div>
            </el-col>
            <el-col span="4" style="margin-top: 50px">
              <!--              <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
              <!--              <el-button type="text" size="small">减少</el-button>-->
              <!--              <el-button type="text" size="small">增加</el-button>-->

            </el-col>
          </el-row>

          <!--        <el-button-->
          <!--            size="mini"-->
          <!--            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>-->
          <el-button type="danger" @click="removeShops(index)">删除订单</el-button>
          <!--          @click="handleDelete(scope.$index, scope.row)"-->
          <el-button type="primary" @click="buy(index)">支付订单</el-button>
        </el-card>
      </el-row>
    </el-row>
    <el-row v-show="activeIndex==='2'">
      dingdan
    </el-row>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Customer",
  data() {
    return {
      testInf: '',
      activeIndex: '1',

      // 数据实体
      cart: {
        cid: 0,
        gname: '',
        gid: 0,
        oamount: 0
      },
      customer: {
        cid: '',
        cname: '',
        cpassword: ''
      },
      shopsData: [],
      cartsData: [],
      shopCartsData: [],
      orderData: [],

    }

  },
  methods: {
    // 购物车操作
    // plus(){
    //
    // },
    // minus(){
    //
    // },
    changeAmount(shopIndex, cartIndex, isPlus) {
      var isDel = false;
      if (isPlus) {
        // alert(this.cartsData[shopIndex]);
        this.cartsData[shopIndex][cartIndex].oamount += 1;
      } else if (this.cartsData[shopIndex][cartIndex].oamount != 1) {
        this.cartsData[shopIndex][cartIndex].oamount -= 1;
      } else {
        isDel = true;
      }
      if (isDel) {
        alert("删除");
        // 删除
      } else {
        // 提交
      }


    },
    buy(index) {
      // 选择方式
      this.$confirm('请选择配送方式', '支付订单', {
        confirmButtonText: '快跑配送',
        cancelButtonText: '到店购买',
      }).then(() => {
        alert('peisong');

      }).catch(() => {
        alert('daodian');
      });


    },
    removeShops(index) {
      this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
      // 更新远程数据
      //   更新本地数据
        this.shopsData.splice(index, 1);

        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    //导航栏
    // 导航栏切换
    toCart() {
      this.activeIndex = '1';

      // 从服务器获取商品信息
      // if (this.goodsData.length == 0) {
      axios.get("http://localhost:8181/customer/findshops?cid=" + this.customer.cid).then(resp => {
        this.shopsData = resp.data;
        this.testInf = this.shopsData;
        for (const index in this.shopsData) {
          console.log(this.shopsData[index].sid);
          axios.get('http://localhost:8181/customer/findcarts?cid=' + this.customer.cid + '&sid=' + this.shopsData[index].sid).then(resp1 => {
            // this.shopCartsData.push({shop:this.shopsData,carts:resp1.data});
            // this.testInf=this.shopCartsData;
            // this.cartData=[];
            // this.cartData.push(resp1.data);
            this.cartsData.push(resp1.data);
          })

        }
        // for () {


        // }
      })
      // }
    },
    toOrder() {
      this.activeIndex = '2';

      // 从服务器获取店铺信息
      // if (this.shopsData.length == 0) {
      // axios.get('http://localhost:8181/goods/shops').then(resp => {
      //   this.shopsData = resp.data;
      //   this.testInf = this.shopsData;
      // })
      // }
    },
  },

  created() {
    this.activeIndex = this.$route.query.activeIndex;
    this.customer = this.$store.state.customer;
    if (this.activeIndex === '1') {

    } else if (this.activeIndex === '2') {

    } else {

    }

  }
}
</script>

<style scoped>

</style>