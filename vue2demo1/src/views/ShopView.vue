<template>
  <div>
    <!--    导航栏-->
    <div style="background: whitesmoke;height: 60px;margin-left: 100px">
      <div style="font-size: 40px;margin:30px">{{ sname }}&emsp;欢迎您!</div>
    </div>
    <!--    商品信息-->
    <el-row>
      <el-col :span="4" v-for="(o, index) in goodsData" :offset="index%4? 1 : 2"
              style="margin-top: 10px;margin-bottom: 10px">
        <el-card :body-style="{ padding: '10px'}" style="width:240px;height:350px" shadow="hover">
          <!--          图片-->
          <el-image
              fit="contain"
              style="width: 220px; height: 220px"
              :src="o.srcurl"
              :preview-src-list="[o.srcurl]"></el-image>
          <!--          描述-->
          <div style="padding: 14px;">
            <span>{{ o.goods.gname }}</span>
            <div class="bottom clearfix">
              <time class="time">销售：{{ o.goods.gsales }}&emsp;&emsp;剩余：{{ o.goods.gonlinenum }}</time>
              <el-button type="text" class="button" @click="addToCart(index)">加入购物车</el-button>
            </div>
          </div>

        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ShopVies",
  data() {
    return {
      // 商家名，用于导航栏的设计
      sname: '',
      // 界面全局标记登录状态
      isLogin: false,
      // 数据实体
      cart: {
        cid: 0,
        gid: 0,
        oamount: 0
      },
      customer: {
        cid: '',
        cname: '',
        cpassword: ''
      },
      goodsData: []
    }
  },
  methods: {
    addToCart(index) {
      // 添加商品到购物车
      if (this.isLogin) {
        this.cart.cid = this.customer.cid;
        this.cart.gid = this.goodsData[index].goods.gid;
        this.cart.oamount = 1;
        axios.post('http://localhost:8181/customer/addToCart', this.cart).then(resp => {
          this.$message({
            message: resp.data,
            type: 'success'
          });
        })
      } else {
        this.$message.error("请先登录账号");
      }
    }
  },
  created() {
    // 从缓存中获取用户信息
    if (this.$store.state.customer.cid) {
      this.isLogin = true;
      this.customer = this.$store.state.customer;
    }
    // 从服务器获取商品信息
    axios.get("http://localhost:8181/goods/shop?sid=" + this.$route.query.sid).then(resp => {
      this.goodsData = resp.data;
      this.sname = resp.data[0].sname;
    })
  }
}
</script>
