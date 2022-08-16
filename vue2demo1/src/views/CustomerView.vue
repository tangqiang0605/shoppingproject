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

    <div>
      <el-dialog title="支付" :visible.sync="payVisible">
        请选择支付方式
        <div slot="footer" class="dialog-footer">
          <el-button @click="afterbuy('到店购买')" size="small">到店购买</el-button>
          <el-button type="primary" @click="afterbuy('快跑配送')" size="small">快跑配送</el-button>
        </div>
      </el-dialog>
    </div>


    <el-row v-show="activeIndex==='1'">
      <el-row v-for="(item,index) in shopCartsData" style="margin: 40px">
        <el-card shadow="hover">
          <el-row>
            <h4>{{ item.shop.sname }}</h4>
            <!--            <h4>{{ item.carts.length}}</h4>-->
          </el-row>
          <el-row v-for="(cart,cartIndex) in item.carts" style="height: 150px">
            <el-col :span="6">
              <el-image :src="cart.srcurl" style="height: 120px" fit="contain"></el-image>
            </el-col>
            <el-col :span="10">
              <div>
                <h3>{{ cart.gname }}</h3>
                已选数量：
                <el-button icon="el-icon-minus" @click="changeAmount(index,cartIndex,false)"></el-button>
                &nbsp;&nbsp;{{ cart.oamount }}
                <el-button icon="el-icon-plus" @click="changeAmount(index,cartIndex,true)"></el-button>
              </div>
            </el-col>
            <el-col :span="4" style="margin-top: 50px">
            </el-col>
          </el-row>
          <el-button type="danger" @click="removeShops(index)">删除订单</el-button>
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
      shopIndex:0,
      payVisible:false,
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
      // shopsData: [],
      // cartsData: [],
      shopCartsData: [],
      orderData: [],

    }

  },
  methods: {
    // 购物车操作
    changeAmount(shopIndex, cartIndex, isPlus) {
      var isDel = false;
      if (isPlus) {
        // alert(this.cartsData[shopIndex]);
        // this.cartsData[shopIndex][cartIndex].oamount += 1;
        this.shopCartsData[shopIndex].carts[cartIndex].oamount++;
      } else if (this.shopCartsData[shopIndex].carts[cartIndex].oamount != 1) {
        this.shopCartsData[shopIndex].carts[cartIndex].oamount--;
      } else {
        isDel = true;
      }
      if (isDel) {
        this.$confirm('将从购物车移除该商品, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //   更新远程数据
          axios.get('http://localhost:8181/customer/removeacart?gid=' + this.shopCartsData[shopIndex].carts[cartIndex].gid);
          //   更新本地数据
          if (this.shopCartsData[shopIndex].carts.length == 1) {
            this.shopCartsData.splice(shopIndex, 1);
          } else {
            this.shopCartsData[shopIndex].carts.splice(cartIndex, 1);
          }
        }).catch();
      } else {
        axios.post('http://localhost:8181/customer/updatecart', this.shopCartsData[shopIndex].carts[cartIndex]);
      }
    },

    buy(index) {
      this.shopIndex = index;
      this.payVisible = true;
    },
    afterbuy(way){
      this.payVisible=false;
      //更新远程数据
      axios.get("http://localhost:8181/customer/pay?cid="+this.customer.cid+"&sid="+this.shopCartsData[this.shopIndex].shop.sid+"&way="+way);
      // 更新本地数据
      this.shopCartsData.splice(this.shopIndex,1);
    },
    removeShops(index) {
      this.$confirm('将从购物车删除该店的所有订单, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //   更新远程数据
        axios.get('http://localhost:8181/customer/removecarts?cid=' + this.customer.cid + '&sid=' + this.shopCartsData[index].shop.sid);
        //   更新本地数据
        this.shopCartsData.splice(index, 1);
      }).catch(() => {
      });
    },

    //导航栏
    // 导航栏切换
    toCart() {
      this.activeIndex = '1';
      axios.get("http://localhost:8181/customer/findshops?cid=" + this.customer.cid).then(resp => {
        this.shopCartsData.length = 0;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/findcarts?cid=' + this.customer.cid + '&sid=' + resp.data[index].sid).then(resp1 => {
            this.shopCartsData.push({shop: resp.data[index], carts: resp1.data});
          })
        }
      })
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
    // this.payVisible=true;


    this.activeIndex = this.$route.query.activeIndex;
    this.customer = this.$store.state.customer;
    if (this.activeIndex === '1') {


      // 从服务器获取商品信息
      axios.get("http://localhost:8181/customer/findshops?cid=" + this.customer.cid).then(resp => {
        this.testInf = '';
        // finish：更快的方法清空数组
        // this.shopCartsData=[];
        // this.shopCartsData.splice(0,this.shopCartsData.length);
        this.shopCartsData.length = 0;
        for (const index in resp.data) {
          // this.testInf = resp.data[index].sid;
          axios.get('http://localhost:8181/customer/findcarts?cid=' + this.customer.cid + '&sid=' + resp.data[index].sid).then(resp1 => {
            // this.cartsData.push(resp1.data);
            // this.testInf=this.testInf+index+resp1.data[0].gid;
            // console.log({shop: resp.data[index], carts: resp1.data});
            this.shopCartsData.push({shop: resp.data[index], carts: resp1.data});
          })
        }
        // this.shopsData = resp.data;
      })
    } else if (this.activeIndex === '2') {

    } else {

    }

  },


}
</script>

<style scoped>

</style>