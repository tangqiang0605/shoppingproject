<template>
  <div class="home">
    <!--    测试信息-->
    {{ testInf }}
    <!--    导航栏-->
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1" @click="toGoods">商品</el-menu-item>
          <el-menu-item index="2" @click="toShops">店铺</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="12">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item></el-menu-item>
        </el-menu>
      </el-col>

      <el-col :span="4">
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-submenu index="3" v-show="!isLogin">
            <template slot="title">登录</template>
            <el-menu-item index="3-1" @click="customerLoginVisible=true">我是顾客</el-menu-item>
            <el-menu-item index="3-2" @click="storeKeeperLoginVisible=true">我是店长</el-menu-item>
            <el-menu-item index="3-3" @click="">我要接单</el-menu-item>
          </el-submenu>
          <el-submenu index="4" v-show="!isLogin">
            <template slot="title">注册</template>
            <el-menu-item index="4-1" @click="customerSignupVisible=true">我是顾客</el-menu-item>
            <el-menu-item index="4-2">我是店长</el-menu-item>
            <el-menu-item index="4-3">我要接单</el-menu-item>
          </el-submenu>
          <el-submenu index="5" v-show="isLogin">
            <template slot="title">您好！{{ customer.cname }}</template>
            <el-menu-item index="5-1" @click="customerChangeNameVisible=true">修改昵称</el-menu-item>
            <el-menu-item index="5-2" @click="customerChangePassWordVisible=true">修改密码</el-menu-item>
            <el-menu-item index="5-3" @click="toCustomer('1')">购物车</el-menu-item>
            <el-menu-item index="5-4" @click="toCustomer('2')">我的订单</el-menu-item>
            <!--            <el-divider />-->
            <el-menu-item index="5-5" @click="outLogin">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>

      </el-col>
    </el-row>
    <!--    登录与注册的弹窗-->
    <div class="customer-login">
      <el-dialog title="登录" :visible.sync="customerLoginVisible">

        <el-form :model="customer">
          <el-form-item label="用户id" label-width="120px">
            <el-input v-model="customer.cid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="customer.cpassword" autocomplete="off" show-password></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="customerLoginVisible = false">取 消</el-button>
          <el-button type="primary" @click="customerLogin">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="customer-signup">
      <el-dialog title="注册" :visible.sync="customerSignupVisible">

        <!--        :model="customer"-->
        <el-form>
          <el-form-item label="用户昵称" label-width="120px">
            <el-input v-model="newName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户密码" label-width="120px">
            <el-input v-model="newPassword1" autocomplete="off" show-password></el-input>
          </el-form-item>
          <el-form-item label="再次输入密码" label-width="120px">
            <el-input v-model="newPassword2" autocomplete="off" show-password></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="customerSignupCancel">取 消</el-button>
          <el-button type="primary" @click="customerSignup">注 册</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="storekeeper-login">
      <el-dialog title="登录" :visible.sync="storeKeeperLoginVisible">

        <el-form :model="storeKeeper">
          <el-form-item label="用户id" label-width="120px">
            <el-input v-model="storeKeeper.sid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="storeKeeper.spassword" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="storeKeeperLoginVisible = false">取 消</el-button>
          <el-button type="primary" @click="storeKeeperLogin">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="delivery-login">
      <!--      <el-dialog title="登录" :visible.sync="storeKeeperLoginVisible">-->

      <!--        <el-form :model="s">-->
      <!--          <el-form-item label="用户id" label-width="120px">-->
      <!--            <el-input v-model="storeKeeper.sid" autocomplete="off"></el-input>-->
      <!--          </el-form-item>-->
      <!--          <el-form-item label="密码" label-width="120px">-->
      <!--            <el-input v-model="storeKeeper.spassword" autocomplete="off"></el-input>-->
      <!--          </el-form-item>-->
      <!--        </el-form>-->

      <!--        <div slot="footer" class="dialog-footer">-->
      <!--          <el-button @click="storeKeeperLoginVisible = false">取 消</el-button>-->
      <!--          <el-button type="primary" @click="storeKeeperLogin">确 定</el-button>-->
      <!--        </div>-->
      <!--      </el-dialog>-->
    </div>

    <!--    账号管理-->
    <div>
      <el-dialog title="修改昵称" :visible.sync="customerChangeNameVisible">
        <el-form :model="customer">
          <el-form-item label="用户昵称" label-width="120px">
            <el-input v-model="newName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="customerChangeNameVisible = false;newName=''">取 消</el-button>
          <el-button type="primary" @click="changeName">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div>
      <el-dialog title="修改密码" :visible.sync="customerChangePassWordVisible">
        <el-form :model="customer">
          <el-form-item label="旧的密码" label-width="120px">
            <el-input v-model="newPassword0" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="新的密码" label-width="120px">
            <el-input v-model="newPassword1" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认新的密码" label-width="120px">
            <el-input v-model="newPassword2" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="changePasswordCancel">取 消</el-button>
          <el-button type="primary" @click="changePassword">确 定</el-button>
        </div>
      </el-dialog>
    </div>


    <!--    搜索框-->
    <el-row>
      <el-col :offset="2">
        <el-input v-model="search" :placeholder="searchHint" style="width: 80%;margin-top: 20px">
        </el-input>
        <!--        <input @keyup.enter="searchData">-->
        <el-button type="primary" style="margin-left: 5px;margin-bottom: 20px" @click="searchData">搜索</el-button>
      </el-col>
    </el-row>

    <!--    商品列表-->
    <el-row v-show="activeIndex=='1'">
      <el-col :span="4" v-for="(o, index) in goodsData" :offset="index%4? 1 : 2"
              style="margin-top: 10px;margin-bottom: 10px">
        <el-card :body-style="{ padding: '10px'}" style="width:240px;height:350px" shadow="hover">
          <el-image
              fit="contain"
              style="width: 220px; height: 220px"
              :src="o.srcurl"
              :preview-src-list="[o.srcurl]"></el-image>
          <div style="padding: 14px;">
            <span>{{ o.goods.gname }}</span>
            <div class="bottom clearfix">
              <time class="time">销售：{{ o.goods.gsales }}&emsp;&emsp;剩余：{{ o.goods.gonlinenum }}<br></br>店铺：{{ o.sname }}
              </time>
              <el-button type="text" class="button" @click="addToCart(index)">加入购物车</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!--    店铺列表-->
    <el-row v-show="activeIndex=='2'"
            v-for="(item,index) in shopsData"
            style="margin-left: 60px;margin-right: 60px;margin-top: 20px">

      <div @click="viewShop(index)">
        <el-card @click="viewShop(index)">
          <h2>{{ item.sname }}</h2>
          <div style="color: #999999">在售商品数量：{{ item.onlinegoodsnum }}&emsp;&emsp;总销售量：{{ item.totalsales }}</div>
        </el-card>
      </div>
    </el-row>

  </div>
</template>

<script>
// import ShopHead from "@/components/ShopHead";
// import GoodsTable from "@/components/GoodsTable";

import axios from 'axios'

export default {
  name: 'HomeView',
  data() {
    return {
      // 输出测试信息
      testInf: "",
      // 登录与注册的弹窗控制
      customerLoginVisible: false,
      storeKeeperLoginVisible: false,
      deliveryLoginVisible: false,
      customerSignupVisible: false,
      storeKeeperSignupVisible: false,
      deliverySignupVisible: false,
      // 切换商品和店铺
      activeIndex: '1',
      // 账户管理
      isLogin: false,
      customerChangeNameVisible: false,
      customerChangePassWordVisible: false,
      newName: '',
      newPassword0: '',
      newPassword1: '',
      newPassword2: '',
      // 搜索的内容
      searchHint: '搜索全部商品',
      search: '',
      searchText: ['搜索全部商品', '搜索全部店铺'],

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
      storeKeeper: {
        sid: '',
        sname: '',
        spassword: '',
        isban: false
      },
      delivery: {
        did: ''
      },

      goodsData: [],
      shopsData: [],
    }
  },
  methods: {
    //导航栏
    // 导航栏切换
    toGoods() {

      this.activeIndex = '1';
      this.search = '';
      this.searchHint = this.searchText[0];
      // 从服务器获取商品信息
      // if (this.goodsData.length == 0) {
      axios.get("http://localhost:8181/goods/show").then(resp => {
        this.goodsData = resp.data;
      })
      // }
    },
    toShops() {
      this.activeIndex = '2';
      this.search = '';
      this.searchHint = this.searchText[1];
      // 从服务器获取店铺信息
      // if (this.shopsData.length == 0) {
      axios.get('http://localhost:8181/goods/shops').then(resp => {
        this.shopsData = resp.data;
        this.testInf = this.shopsData;
      })
      // }
    },
    //登录与注册检查
    customerLogin() {

      if (this.customer.cpassword == '') {
        this.$message.error("请输入密码");
      } else if (this.customer.cid == '') {
        this.$message.error("请输入用户id");
      } else {
        var n = Number(this.customer.cid);
        if (isNaN(n)) {
          this.$message.error("用户id必须为数字");
        } else {
          this.customer.cid = n;
          axios.post('http://localhost:8181/customer/login', this.customer).then(resp => {
            if ("登录验证成功" === resp.data) {
              axios.post('http://localhost:8181/customer/getinf', this.customer).then(resp => {
                this.customer = resp.data;
                this.testInf = this.customer;
                this.customerLoginVisible = false;
                this.isLogin = true;
                this.$store.commit('saveCustomer', this.customer);
              })
              this.$message({
                message: resp.data,
                type: 'success'
              });

            } else {
              this.$message.error(resp.data);
            }
          })

        }
      }
    },
    customerSignup(){
      if(this.newName===''||this.newPassword1===''||this.newPassword2===''){
        this.$message.error('请补充完所有信息');
      }else if(this.newPassword2!=this.newPassword1){
        this.$message.error('两次输入密码不一致');
      }else{
        // 注册并返回提示，设置customerid。
        var newCustomer=this.customer;
        newCustomer.cname=this.newName;
        newCustomer.cpassword=this.newPassword1;
        axios.post('http://localhost:8181/customer/signup',newCustomer).then(resp=>{
          newCustomer.cid=resp.data;
          this.customer=resp.data;
          this.$alert('请使用账号id进行登录，您的账号id是： '+resp.data, '注册成功', {
            confirmButtonText: '记好了',
            callback: action => {
              // this.$message({
              //   type: 'info',
              //   message: `action: ${ action }`
              // });
              this.newName='';
              // this.newPassword0 = '';
              this.newPassword1 = '';
              this.newPassword2 = '';
              this.customerSignupVisible = false
            }
          });
        });

      }
    },
    customerSignupCancel(){
      this.newName='';
      // this.newPassword0 = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      this.customerSignupVisible = false
    },
    storeKeeperLogin() {

      if (this.storeKeeper.spassword == '') {
        this.$message.error("请输入密码");
      } else if (this.storeKeeper.sid == '') {
        this.$message.error("请输入用户id");
      } else {
        var n = Number(this.storeKeeper.sid);
        if (isNaN(n)) {
          this.$message.error("用户id必须为数字");
        } else {
          this.storeKeeper.sid = n;
          axios.post('http://localhost:8181/storekeeper/login', this.storeKeeper).then(resp => {
            if ("登录验证成功" === resp.data) {
              axios.post('http://localhost:8181/storekeeper/getinf', this.storeKeeper).then(resp => {
                // this.customer = resp.data;
                // this.testInf = this.customer;
                this.storeKeeperLoginVisible = false;
                // this.isLogin = true;
                // this.$store.commit('saveCustomer', this.customer);
                this.$store.commit('saveStoreKeeper', resp.data);
                // 储存并跳转网页
                this.$router.push('storekeeper');
              })
              this.$message({
                message: resp.data,
                type: 'success'
              });

            } else {
              this.$message.error(resp.data);
              //接口放回其他数据（登录验证失败的种类及原因）
            }
          })

        }
      }
    },


    handleSelect(key, keyPath) {
      // console.log(activeIndex)
    },
    // 用户管理
    changeName() {
      // this.testInf = "changName";
      if (this.newName === '') {

      } else if (this.newName === this.customer.cname) {
        this.$message.error("用户昵称重复");
      } else {
        var newCustomer = this.customer;
        newCustomer.cname = this.newName;
        axios.post('http://localhost:8181/customer/change', newCustomer)
            .then(resp => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              this.customer = newCustomer;
              this.$store.commit('saveCustomer', newCustomer);
            });
        this.newName = '';
        this.customerChangeNameVisible = false;
      }


    },
    changePassword() {
      // this.testInf = "changePassword";
      if (this.newPassword0 === '' || this.newPassword1 === '' || this.newPassword2 === '') {

      } else if (this.newPassword0 != this.customer.cpassword) {
        this.$message.error("密码错误");
      } else if (this.newPassword1 != this.newPassword2) {
        this.$message.error("两次输入密码不一致");
      } else {
        var newCustomer = this.customer;
        newCustomer.cpassword = this.newPassword2;
        axios.post('http://localhost:8181/customer/change', newCustomer)
            .then(resp => {
              this.$message({
                message: '修改成功' + '请重新登录',
                type: 'success'
              });
              // 清空登录信息:
              this.$store.commit('saveCustomer', {});
              this.customer = {};
              this.isLogin = false;
            });
        this.newPassword0 = '';
        this.newPassword1 = '';
        this.newPassword2 = '';
        this.customerChangePassWordVisible = false;
      }

    },
    changePasswordCancel() {
      this.newPassword0 = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      this.customerChangePassWordVisible = false;
    },
    toCustomer(page) {
      this.$router.push({name: 'customer', query: {activeIndex: page}});
    },
    outLogin() {
      // this.testInf = "outLogin";
      this.customer = {};
      this.$store.commit('saveCustomer', {});
      this.isLogin = false;
      this.$message({
        message: "已退出登录",
        type: 'success'
      });
    },

    //商品列表的操作
    addToCart(index) {
      this.cart.cid = this.customer.cid;
      this.cart.gid = this.goodsData[index].goods.gid;
      this.cart.oamount = 1;
      if (this.isLogin) {
        axios.post('http://localhost:8181/customer/addToCart', this.cart).then(resp => {
          // alert(resp.data);
          this.$message({
            message: resp.data,
            type: 'success'
          });
        })
      } else {
        this.$message.error("请先登录账号");
        this.customerLoginVisible = true;
      }

    },
    // 店铺列表的操作
    viewShop(index) {
      this.$router.push({name: 'shop', query: {sid: this.shopsData[index].sid}});
    },
    // 搜索功能:当搜索为空时搜索全部
    searchData() {
      // if (this.search==='') {
      //   if (this.activeIndex == '1') {
      //     // this.searchHint = '搜索全部商品';
      //     // 从服务器获取商品信息
      //     axios.get("http://localhost:8181/goods/show").then(resp => {
      //       this.goodsData = resp.data;
      //     })
      //
      //   } else if (this.activeIndex == '2') {
      //     // this.searchHint = '搜索全部店铺';
      //     // 从服务器获取店铺信息
      //     axios.get('http://localhost:8181/goods/shops').then(resp => {
      //       this.shopsData = resp.data;
      //     })
      //   } else {
      //     // this.searchHint = "搜索什么";
      //   }
      //
      // } else
      if (this.activeIndex == '1') {
        // alert('搜索商品');
        axios.get('http://localhost:8181/goods/searchgoods?gname=' + this.search).then(resp => {
          this.goodsData = resp.data;
        })
      } else if (this.activeIndex == '2') {
        // alert('搜索店铺');
        axios.get('http://localhost:8181/goods/searchshops?sname=' + this.search).then(resp => {
          this.shopsData = resp.data;
        })
      } else {
        alert("搜索什么");
      }
    }
  }
  ,

  created() {
    // 测试shop界面用
    // this.activeIndex = '2';
    // var cid = this.$store.state.customer.cid
    // alert(this.shopsData.length);
    // alert(this.shopsData.isEmpty());
    // alert(this.shopsData==null);
    // 设置布局
    this.activeIndex = '2';
    if (this.activeIndex == '1') {
      this.searchHint = this.searchText[0];
    } else if (this.activeIndex == '2') {
      this.searchHint = this.searchText[1];
    } else {
      this.searchHint = "搜索什么";
    }
    // 从缓存中获取用户信息
    if (this.$store.state.customer.cid) {
      this.isLogin = true;
      this.customer = this.$store.state.customer;
    }
    // 从服务器获取商品信息
    axios.get("http://localhost:8181/goods/show").then(resp => {
      this.goodsData = resp.data;
    })
    // 从服务器获取店铺信息
    axios.get('http://localhost:8181/goods/shops').then(resp => {
      this.shopsData = resp.data;
    })
  }
  ,

}
</script>

<style>
/*卡片*/
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 220px;
  height: 220px;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>