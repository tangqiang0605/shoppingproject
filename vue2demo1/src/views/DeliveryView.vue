<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="toOrders('1')">未接单</el-menu-item>
          <el-menu-item index="2" @click="toOrders('2')">配送中</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item></el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="4">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-submenu index="5">
            <template slot="title">您好！{{ delivery.dname }}</template>
            <el-menu-item index="5-1" @click="deliveryChangeNameVisible=true">修改昵称</el-menu-item>
            <el-menu-item index="5-2" @click="deliveryChangePassWordVisible=true">修改密码</el-menu-item>
            <el-menu-item index="5-5" @click="outLogin">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
    <el-row v-show="activeIndex==='1'">
      <el-row v-for="(item,orderIndex) in ordersCartsData1" style="margin: 40px">
        <el-card shadow="hover">
          <el-descriptions title="订单信息">
            <el-descriptions-item label="订单号">{{ item.orders.oid }}</el-descriptions-item>
            <el-descriptions-item label="店铺id">{{ item.orders.sid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag size="small">{{ item.orders.ostate }}</el-tag>
            </el-descriptions-item>
          </el-descriptions>
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
            <el-descriptions-item label="店铺id">{{ item.orders.sid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag size="small" type="warning" v-show="item.orders.ostate=='已发货'">已发货</el-tag>
              <el-tag size="small" type="" v-show="item.orders.ostate=='配送中'">配送中</el-tag>
              <el-tag size="small" type="danger" v-show="item.orders.ostate=='已送达'">已送达</el-tag>
              <el-tag size="small" type="success" v-show="item.orders.ostate=='已完成'">已完成</el-tag>
            </el-descriptions-item>
          </el-descriptions>
          <el-button type="primary" size="small" style="margin-top: 10px" v-show="item.orders.ostate=='配送中'"
                     @click="finishOrders(orderIndex)">完成配送
          </el-button>
          <el-button type="primary" size="small" style="margin-top: 10px" v-show="item.orders.ostate!='配送中'"
                     disabled>完成配送
          </el-button>
        </el-card>
      </el-row>
    </el-row>

    <!--    修改昵称-->
    <div>
      <el-dialog title="修改昵称" :visible.sync="deliveryChangeNameVisible">
        <el-form :model="delivery">
          <el-form-item label="用户昵称" label-width="120px">
            <el-input v-model="newName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="deliveryChangeNameVisible = false;newName=''">取 消</el-button>
          <el-button type="primary" @click="changeName">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!--    修改密码-->
    <div>
      <el-dialog title="修改密码" :visible.sync="deliveryChangePassWordVisible">
        <el-form :model="delivery">
          <el-form-item label="旧的密码" label-width="120px">
            <el-input v-model="newPassword0" autocomplete="off" show-password></el-input>
          </el-form-item>
          <el-form-item label="新的密码" label-width="120px">
            <el-input v-model="newPassword1" autocomplete="off" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认新的密码" label-width="120px">
            <el-input v-model="newPassword2" autocomplete="off" show-password></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="changePasswordCancel">取 消</el-button>
          <el-button type="primary" @click="changePassword">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "DeliveryView",
  data() {
    return {
      activeIndex: '1',

      newName:'',
      newPassword0:'',
      newPassword1:'',
      newPassword2:'',
      deliveryChangeNameVisible:false,
      deliveryChangePassWordVisible:false,

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
    toOrders(page) {
      this.activeIndex=page;
      if (this.activeIndex === '1') {
        axios.get("http://localhost:8181/delivery/neworders").then(resp => {
          this.ordersCartsData1.length = 0;
          if(resp.data.length!==0){
            for (const index in resp.data) {
              axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
                this.ordersCartsData1.push({orders: resp.data[index], carts: resp1.data});
              })
            }
          }
        })
      } else if (this.activeIndex === '2') {
        axios.get("http://localhost:8181/delivery/myorders?did=" + this.delivery.did).then(resp => {
          this.ordersCartsData2.length = 0;
          for (const index in resp.data) {
            axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
              this.ordersCartsData2.push({orders: resp.data[index], carts: resp1.data});
            })
          }
        })
      }
    },
    takeOrders(orderIndex) {
      axios.get('http://localhost:8181/delivery/takeorders?oid=' + this.ordersCartsData1[orderIndex].orders.oid + "&did=" + this.delivery.did).then(r => this.ordersCartsData1.splice(orderIndex,1))
    },
    finishOrders(orderIndex){
      axios.get('http://localhost:8181/delivery/finishorders?oid=' + this.ordersCartsData2[orderIndex].orders.oid).then(resp => this.ordersCartsData2[orderIndex].orders.ostate='已送达')
    },
    // 用户管理
    changeName() {
      if (this.newName === '') {
      } else if (this.newName === this.delivery.dname) {
        this.$message.error("用户昵称重复");
      } else {
        let newDelivery = this.delivery;
        newDelivery.dname = this.newName;
        axios.post('http://localhost:8181/delivery/update', newDelivery)
            .then(resp => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.delivery = newDelivery;
              this.$store.commit('saveDelivery', newDelivery);
            });
        this.newName = '';
        this.deliveryChangeNameVisible = false;
      }
    },
    changePassword() {
      if (this.newPassword0 === '' || this.newPassword1 === '' || this.newPassword2 === '') {

      } else if (this.newPassword0 != this.delivery.dpassword) {
        this.$message.error("密码错误");
      } else if (this.newPassword1 != this.newPassword2) {
        this.$message.error("两次输入密码不一致");
      } else {
        let newDelivery = this.delivery;
        newDelivery.dpassword = this.newPassword2;
        axios.post('http://localhost:8181/delivery/update', newDelivery)
            .then(resp => {
              this.$message({
                message: '修改成功' + '请重新登录',
                type: 'success'
              });
              // 清空登录信息:
              this.$store.commit('saveDelivery', {});
              this.$router.push('/');
            });
      }

    },
    changePasswordCancel() {
      this.newPassword0 = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      this.deliveryChangePassWordVisible = false;
    },
    outLogin() {
      this.$store.commit('saveStoreKeeper', {});
      this.$router.push('/');
    },
  },
  created() {
    this.delivery = this.$store.state.delivery;
    this.toOrders(this.activeIndex);
  }
}
</script>

<style scoped>

</style>