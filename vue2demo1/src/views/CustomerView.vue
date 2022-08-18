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
      <el-row v-for="(item,orderIndex) in ordersCartsData" style="margin: 40px">
        <el-card shadow="hover">
          <el-descriptions title="订单信息">
            <el-descriptions-item label="订单号">{{ item.orders.oid }}</el-descriptions-item>
            <!--        <el-descriptions-item label="配送员">{{item.orders.did}}</el-descriptions-item>-->
            <el-descriptions-item label="店铺id">{{ item.orders.sid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
<!--              <el-tag size="small">{{ item.orders.ostate }}</el-tag>-->
              <el-tag size="small" type="info" v-show="item.orders.ostate=='待发货'">待发货</el-tag>
              <el-tag size="small" type="warning" v-show="item.orders.ostate=='已发货'">已发货</el-tag>
              <el-tag size="small" type="danger" v-show="item.orders.ostate=='待取货'">待取货</el-tag>
              <el-tag size="small" type="" v-show="item.orders.ostate=='配送中'">配送中</el-tag>
              <el-tag size="small" type="danger" v-show="item.orders.ostate=='已送达'">已送达</el-tag>
              <el-tag size="small" type="success" v-show="item.orders.ostate=='已完成'">已完成</el-tag>
            </el-descriptions-item>
          </el-descriptions>
          <div style="font-size: 15px;color: #999999;margin-bottom: 10px" v-for="(cart,index) in item.carts">
            商品{{ index + 1 }}:{{ cart.gname }}&emsp;数量：{{ cart.oamount }}
          </div>
<!--          未送达之前是没有完成按钮的，而完成后会显示不可操作的完成按钮-->
          <el-button type="success" size="small" style="margin-top: 10px"
                     v-show="item.orders.ostate=='已送达'||item.orders.ostate=='待取货'"
                     @click="finishOrders(orderIndex)">完成订单
          </el-button>
          <el-button type="success" size="small" style="margin-top: 10px" disabled
                     v-show="item.orders.ostate=='已完成'">
            完成订单
          </el-button>
        </el-card>
      </el-row>
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
      shopIndex: 0,
      payVisible: false,
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
      shopCartsData: [],
      ordersCartsData: [],

    }

  },
  methods: {
    // 购物车操作
    changeAmount(shopIndex, cartIndex, isPlus) {
      var isDel = false;
      if (isPlus) {
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
          axios.get('http://localhost:8181/customer/removeacart?cid='+this.customer.cid+'&gid=' + this.shopCartsData[shopIndex].carts[cartIndex].gid);
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
    afterbuy(way) {
      // finish：尝试购买，确认数量是否充足。更新远程数据时，接口那里除了删除购物车记录，还要视图下架商品。
      // finish：前端要更新本地是否有下架的
      axios.post('http://localhost:8181/customer/detectamount', this.shopCartsData[this.shopIndex].carts).then(resp => {
        // console.log(resp.data);
        if (resp.data.length != 0) {
          for (const index in resp.data) {
            this.$message.error('支付失败！' + resp.data[index]);
          }
        } else {
          //更新远程数据
          axios.get("http://localhost:8181/customer/pay?cid=" + this.customer.cid + "&sid=" + this.shopCartsData[this.shopIndex].shop.sid + "&way=" + way);
          // 更新本地数据
          this.shopCartsData.splice(this.shopIndex, 1);
        }
        this.payVisible = false;
      })
      // this.shopCartsData[this.shopIndex].carts

      // xx库存不足，剩余x件。


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
      }).catch();
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
      axios.get("http://localhost:8181/customer/getorders?cid=" + this.customer.cid).then(resp => {
        this.ordersCartsData.length = 0;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })
    },
    finishOrders(ordersIndex) {
      // finish：订单完成
      var state=this.ordersCartsData[ordersIndex].orders.ostate;
      if(state==='已送达'||state==='待取货'){
        this.$confirm('请确认订单已签收, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //   更新远程数据
          axios.get('http://localhost:8181/customer/finishorders?oid=' + this.ordersCartsData[ordersIndex].orders.oid);
          //   更新本地数据
          this.ordersCartsData[ordersIndex].orders.ostate = "已完成";
        }).catch();
      }else{
       // 整个if判断其实可以去掉
      }
    }


  },

  created() {
    // this.payVisible=true;


    this.activeIndex = this.$route.query.activeIndex;
    this.customer = this.$store.state.customer;

      if (this.activeIndex === '1') {
        // 从服务器获取商品信息
        axios.get("http://localhost:8181/customer/findshops?cid=" + this.customer.cid).then(resp => {
          this.shopCartsData.length = 0;
          for (const index in resp.data) {
            axios.get('http://localhost:8181/customer/findcarts?cid=' + this.customer.cid + '&sid=' + resp.data[index].sid).then(resp1 => {
              this.shopCartsData.push({shop: resp.data[index], carts: resp1.data});
            })
          }
        })
      } else if (this.activeIndex === '2') {
        axios.get("http://localhost:8181/customer/getorders?cid=" + this.customer.cid).then(resp => {
          this.ordersCartsData.length = 0;
          for (const index in resp.data) {
            axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
              this.ordersCartsData.push({orders: resp.data[index], carts: resp1.data});
            })
          }
        })
      } else {

      }




  },


}
</script>

<style scoped>

</style>