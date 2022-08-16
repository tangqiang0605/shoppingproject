<template>
  <div>
<!--    {{ delivery }}-->
    {{ testInf }}
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="toNewOrders">未接单</el-menu-item>
          <el-menu-item index="2" @click="toMyOrders">配送中</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item></el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="4">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item>您好！打工人{{ delivery.dname }}</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>

    <el-row v-show="activeIndex==='1'">
      <el-row v-for="(item,orderIndex) in ordersCartsData1" style="margin: 40px">
        <el-card shadow="hover">
          <el-descriptions title="订单信息">
            <el-descriptions-item label="订单号">{{ item.orders.oid }}</el-descriptions-item>
            <!--        <el-descriptions-item label="配送员">{{item.orders.did}}</el-descriptions-item>-->
            <el-descriptions-item label="店铺id">{{ item.orders.sid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag size="small">{{ item.orders.ostate }}</el-tag>
            </el-descriptions-item>
          </el-descriptions>
          <!--          <div style="font-size: 15px;color: #999999;margin-bottom: 10px" v-for="(cart,index) in item.carts">-->
          <!--            商品{{ index + 1 }}:{{ cart.gname }}&emsp;数量：{{ cart.oamount }}-->
          <!--          </div>-->
          <el-button type="primary" size="small" style="margin-top: 10px" @click="takeOrders(orderIndex)">接受订单
          </el-button>
        </el-card>
      </el-row>
    </el-row>

    <el-row v-show="activeIndex==='2'">
      <el-row v-for="(item,orderIndex) in ordersCartsData2" style="margin: 40px">
        <el-card shadow="hover">
          <el-descriptions title="订单信息">
            <el-descriptions-item label="订单号">{{ item.orders.oid }}</el-descriptions-item>
            <!--        <el-descriptions-item label="配送员">{{item.orders.did}}</el-descriptions-item>-->
            <el-descriptions-item label="店铺id">{{ item.orders.sid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag size="small">{{ item.orders.ostate }}</el-tag>
            </el-descriptions-item>
          </el-descriptions>
          <!--          <div style="font-size: 15px;color: #999999;margin-bottom: 10px" v-for="(cart,index) in item.carts">-->
          <!--            商品{{ index + 1 }}:{{ cart.gname }}&emsp;数量：{{ cart.oamount }}-->
          <!--          </div>-->
          <el-button type="primary" size="small" style="margin-top: 10px" v-show="item.orders.ostate!='已完成'"
                     @click="finishOrders(orderIndex)">完成配送
          </el-button>
          <el-button type="success" size="small" style="margin-top: 10px" disabled v-show="item.orders.ostate=='已完成'">
            取消配送
          </el-button>
        </el-card>
      </el-row>
    </el-row>
    <!--    <button @click="testPost"></button>-->
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "DeliveryView",
  data() {
    return {
      testInf: '',
      activeIndex: '1',

      delivery: {
        did: 0,
        dname: '',
        dpassword: ''
      },

      ordersCartsData1: [],
      ordersCartsData2: [],
    }
  },
  methods: {
    toNewOrders() {
      this.activeIndex = '1';
      axios.get("http://localhost:8181/delivery/neworders").then(resp => {
        this.ordersCartsData1.length = 0;
        this.testInf = resp.data;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData1.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })
    },
    toMyOrders() {
      this.activeIndex = '2';
      axios.get("http://localhost:8181/delivery/myorders?did=" + this.delivery.did).then(resp => {
        this.ordersCartsData2.length = 0;
        // this.testInf=resp.data;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData2.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })
    },
    takeOrders(orderIndex) {

      axios.get('http://localhost:8181/delivery/takeorders?oid=' + this.ordersCartsData1[orderIndex].orders.oid + "&did=" + this.delivery.did).then(resp => {
        axios.get("http://localhost:8181/delivery/neworders").then(resp => {
          this.ordersCartsData1.length = 0;
          this.testInf = resp.data;
          for (const index in resp.data) {
            axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
              this.ordersCartsData1.push({orders: resp.data[index], carts: resp1.data});
            })
          }
        })
      })

    },
    finishOrders(orderIndex){

    }

    // testPost() {
    //   axios.post('https://localhost:8181/src/testpost', {"sid": '3', "gid": '8'}).then(resp => {
    //     console.log(resp.data)
    //   });
    // }

  },
  created() {
    this.delivery = this.$store.state.delivery;

    if (this.activeIndex === '1') {
      axios.get("http://localhost:8181/delivery/neworders").then(resp => {
        this.ordersCartsData1.length = 0;
        this.testInf = resp.data;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData1.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })
    } else if (this.activeIndex === '2') {
      axios.get("http://localhost:8181/delivery/myorders?did=" + this.delivery.did).then(resp => {
        this.ordersCartsData1.length = 0;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData1.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>