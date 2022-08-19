<template>
  <div class="home">
    <!--    导航栏-->
    <el-row>
      <!--      导航栏左-->
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="toPage('1')">商品</el-menu-item>
          <el-menu-item index="2" @click="toPage('2')">店铺</el-menu-item>
        </el-menu>
      </el-col>
      <!--      导航栏中-->
      <el-col :span="12">
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-menu-item></el-menu-item>
        </el-menu>
      </el-col>
      <!--      导航栏右-->
      <el-col :span="4">
        <!--        导航栏-注册登录》-->
        <el-menu class="el-menu-demo" mode="horizontal">
          <el-submenu index="3" v-show="!isLogin">
            <template slot="title">登录</template>
            <el-menu-item index="3-1" @click="customerLoginVisible=true">我是顾客</el-menu-item>
            <el-menu-item index="3-2" @click="storeKeeperLoginVisible=true">我是店长</el-menu-item>
            <el-menu-item index="3-3" @click="deliveryLoginVisible=true">我要接单</el-menu-item>
          </el-submenu>
          <el-submenu index="4" v-show="!isLogin">
            <template slot="title">注册</template>
            <el-menu-item index="4-1" @click="customerSignupVisible=true">我是顾客</el-menu-item>
            <el-menu-item index="4-2" @click="storeKeeperSignupVisible=true">我是店长</el-menu-item>
            <el-menu-item index="4-3" @click="deliverySignupVisible=true">我要接单</el-menu-item>
          </el-submenu>
          <!--          导航栏-用户管理-->
          <el-submenu index="5" v-show="isLogin">
            <template slot="title">您好！{{ customer.cname }}</template>
            <el-menu-item index="5-1" @click="customerChangeNameVisible=true">修改昵称</el-menu-item>
            <el-menu-item index="5-2" @click="customerChangePassWordVisible=true">修改密码</el-menu-item>
            <el-menu-item index="5-3" @click="toCustomer('1')">购物车</el-menu-item>
            <el-menu-item index="5-4" @click="toCustomer('2')">我的订单</el-menu-item>
            <el-menu-item index="5-5" @click="outLogin">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>

      </el-col>
    </el-row>

    <!--    顾客登录与注册的弹窗-->
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
    <!--    店主登录与注册的弹窗-->
    <div class="storekeeper-login">
      <el-dialog title="登录" :visible.sync="storeKeeperLoginVisible">

        <el-form :model="storeKeeper">
          <el-form-item label="用户id" label-width="120px">
            <el-input v-model="storeKeeper.sid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="storeKeeper.spassword" autocomplete="off" show-password></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="storeKeeperLoginVisible = false">取 消</el-button>
          <el-button type="primary" @click="storeKeeperLogin">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="storeKeeper-signup">
      <el-dialog title="注册" :visible.sync="storeKeeperSignupVisible">

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
          <el-button @click="storeKeeperSignupCancel">取 消</el-button>
          <el-button type="primary" @click="storeKeeperSignup">注 册</el-button>
        </div>
      </el-dialog>
    </div>
    <!--    配送员登录与注册的弹窗-->
    <div class="delivery-login">
      <el-dialog title="登录" :visible.sync="deliveryLoginVisible">

        <el-form :model="delivery">
          <el-form-item label="用户id" label-width="120px">
            <el-input v-model="delivery.did" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" label-width="120px">
            <el-input v-model="delivery.dpassword" autocomplete="off" show-password></el-input>
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="deliveryLoginVisible = false">取 消</el-button>
          <el-button type="primary" @click="deliveryLogin">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div class="delivery-signup">
      <el-dialog title="注册" :visible.sync="deliverySignupVisible">

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
          <el-button @click="deliverySignupCancel">取 消</el-button>
          <el-button type="primary" @click="deliverySignup">注 册</el-button>
        </div>
      </el-dialog>
    </div>

    <!--    顾客账号管理-->
    <!--    修改昵称-->
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
    <!--    修改密码-->
    <div>
      <el-dialog title="修改密码" :visible.sync="customerChangePassWordVisible">
        <el-form :model="customer">
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
    <el-row v-show="activeIndex==='1'">
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
              <time class="time">销售：{{ o.goods.gsales }}&emsp;&emsp;剩余：{{ o.goods.gonlinenum }}<br/>店铺：{{ o.sname }}
              </time>
              <el-button type="text" class="button" @click="addToCart(index)">加入购物车</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!--    店铺列表-->
    <el-row v-show="activeIndex==='2'"
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
import axios from 'axios'

export default {
  name: 'HomeView',
  data() {
    return {
      // 导航栏
      activeIndex: '1',
      // 登录与注册的弹窗控制
      customerLoginVisible: false,
      storeKeeperLoginVisible: false,
      deliveryLoginVisible: false,
      customerSignupVisible: false,
      storeKeeperSignupVisible: false,
      deliverySignupVisible: false,
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
        did: '',
        dname: '',
        dpassword: ''
      },

      goodsData: [],
      shopsData: [],
    }
  },
  methods: {
    // 导航栏切换
    toPage(page) {
      // 设置导航栏
      this.activeIndex = page;
      // 重置搜索框
      this.search = '';
      this.searchHint = this.searchText[Number(page)-1];
      // 从服务器获取商品信息
      if (this.activeIndex === '1') {
        // 从服务器获取商品信息
        axios.get('http://localhost:8181/goods/searchgoods?gname=').then(resp => {
          this.goodsData = resp.data;
        })
      } else if (this.activeIndex === '2') {
        // 从服务器获取店铺信息
        axios.get('http://localhost:8181/goods/searchshops?sname=').then(resp => {
          this.shopsData = resp.data;
        });
      }
    },
    //登录与注册检查
    customerLogin() {
      // 前端登录验证
      if (this.customer.cpassword === '') {
        this.$message.error("请输入密码");
      } else if (this.customer.cid === '') {
        this.$message.error("请输入用户id");
      } else {
        // 格式转换
        let n = Number(this.customer.cid);
        if (isNaN(n)) {
          this.$message.error("用户id必须为数字");
        } else {
          // 后台登录验证
          this.customer.cid = n;
          axios.post('http://localhost:8181/customer/login', this.customer).then(resp => {
            if ("登录验证成功" === resp.data) {
              axios.post('http://localhost:8181/customer/getinf', this.customer).then(resp1 => {
                // 保存本地
                this.customer = resp1.data;
                this.isLogin = true;
                // 保存缓存
                this.$store.commit('saveCustomer', this.customer);
              })
              // 登录成功，关闭弹窗
              this.$message({
                message: resp.data,
                type: 'success'
              });
              this.customerLoginVisible = false;
            } else {
              // 登录失败，错误提示
              this.$message.error(resp.data);
            }
          })
        }
      }
    },
    customerSignup() {
      // 前端注册验证
      if (this.newName === '' || this.newPassword1 === '' || this.newPassword2 === '') {
        this.$message.error('请补充完所有信息');
      } else if (this.newPassword2 !== this.newPassword1) {
        this.$message.error('两次输入密码不一致');
      } else {
        // 后端注册验证
        // 准备临时对象并提交
        let newCustomer = this.customer;
        newCustomer.cname = this.newName;
        newCustomer.cpassword = this.newPassword1;
        axios.post('http://localhost:8181/customer/signup', newCustomer).then(resp => {
          this.$alert('请使用账号id进行登录，您的账号id是： ' + resp.data, '注册成功', {
            confirmButtonText: '记好了',
            callback: action => {
              // 清空临时对象属性
              this.newName = '';
              this.newPassword1 = '';
              this.newPassword2 = '';
              // 关闭弹窗
              this.customerSignupVisible = false;
            }
          });
          // 打开登录弹窗
          this.customer.cid = resp.data;
          // this.customer.cpassword = '';
          this.customerLoginVisible = true;
        });
      }
    },
    customerSignupCancel() {
      // 取消注册，清空临时对象属性
      this.newName = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      // 关闭弹窗
      this.customerSignupVisible = false
    },

    storeKeeperLogin() {
      // 前端验证登录
      if (this.storeKeeper.spassword === '') {
        this.$message.error("请输入密码");
      } else if (this.storeKeeper.sid === '') {
        this.$message.error("请输入用户id");
      } else {
        let n = Number(this.storeKeeper.sid);
        if (isNaN(n)) {
          this.$message.error("用户id必须为数字");
        } else {
          // 后端验证登录
          this.storeKeeper.sid = n;
          axios.post('http://localhost:8181/storekeeper/login', this.storeKeeper).then(resp => {
            if ("登录验证成功" === resp.data) {
              axios.post('http://localhost:8181/storekeeper/getinf', this.storeKeeper).then(resp1 => {
                // 保存缓存
                this.$store.commit('saveStoreKeeper', resp1.data);
                // 跳转网页
                this.$router.push('storekeeper');
              })
              // 登录成功，关闭弹窗
              this.$message({
                message: resp.data,
                type: 'success'
              });
              this.storeKeeperLoginVisible = false;
            } else {
              // 登录失败，错误提示
              this.$message.error(resp.data);
            }
          })
        }
      }
    },
    storeKeeperSignup() {
      // 前端注册验证
      if (this.newName === '' || this.newPassword1 === '' || this.newPassword2 === '') {
        this.$message.error('请补充完所有信息');
      } else if (this.newPassword2 !== this.newPassword1) {
        this.$message.error('两次输入密码不一致');
      } else {
        // 准备临时对象
        let newStoreKeeper = this.storeKeeper;
        newStoreKeeper.sname = this.newName;
        newStoreKeeper.spassword = this.newPassword1;
        axios.post('http://localhost:8181/storekeeper/signup', newStoreKeeper).then(resp => {
          this.$alert('请使用账号id进行登录，您的账号id是： ' + resp.data, '注册成功', {
            confirmButtonText: '记好了',
            callback: action => {
              // 注册成功，清空临时对象属性
              this.newName = '';
              this.newPassword1 = '';
              this.newPassword2 = '';
              this.storeKeeperSignupVisible = false;
            }
          });
          // 打开登录弹窗
          this.storeKeeper.sid = resp.data;
          this.storeKeeperLoginVisible = true;
        });
      }
    },
    storeKeeperSignupCancel() {
      // 清空临时对象属性
      this.newName = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      // 关闭弹窗
      this.storeKeeperSignupVisible = false
    },

    deliveryLogin() {
      // 前端验证登录
      if (this.delivery.dpassword === '') {
        this.$message.error("请输入密码");
      } else if (this.delivery.did === '') {
        this.$message.error("请输入用户id");
      } else {
        let n = Number(this.delivery.did);
        if (isNaN(n)) {
          this.$message.error("用户id必须为数字");
        } else {
          // 后台验证登录
          this.delivery.did = n;
          axios.post('http://localhost:8181/delivery/login', this.delivery).then(resp => {
            if ("登录验证成功" === resp.data) {
              axios.post('http://localhost:8181/delivery/getinf', this.delivery).then(resp1 => {
                this.$store.commit('saveDelivery', resp1.data);
                this.$router.push('/delivery');
              })
              // 登录成功，关闭弹窗
              this.$message({
                message: resp.data,
                type: 'success'
              });
              this.deliveryLoginVisible = false;
            } else {
              // 后台验证失败
              this.$message.error(resp.data);
            }
          })
        }
      }
    },
    deliverySignup() {
      // 前端注册验证
      if (this.newName === '' || this.newPassword1 === '' || this.newPassword2 === '') {
        this.$message.error('请补充完所有信息');
      } else if (this.newPassword2 !== this.newPassword1) {
        this.$message.error('两次输入密码不一致');
      } else {
        // 准备临时对象
        let newDelivery = this.delivery;
        newDelivery.dname = this.newName;
        newDelivery.dpassword = this.newPassword1;
        axios.post('http://localhost:8181/delivery/signup', newDelivery).then(resp => {
          this.$alert('请使用账号id进行登录，您的账号id是： ' + resp.data, '注册成功', {
            confirmButtonText: '记好了',
            callback: action => {
              // 清空临时对象，关闭弹窗
              this.newName = '';
              this.newPassword1 = '';
              this.newPassword2 = '';
              this.deliverySignupVisible = false;
            }
          });
          // 登录的关闭弹窗可以写在任意地方，但是注册的关闭弹窗后面不可以太紧跟着下一个弹窗的打开
          this.delivery.did = resp.data;
          this.deliveryLoginVisible = true;
        });
      }
    },
    deliverySignupCancel() {
      // 清空临时对象属性
      this.newName = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      // 关闭弹窗
      this.deliverySignupVisible = false
    },

    // 用户管理
    changeName() {
      // 前端验证
      if (this.newName === '') {
      } else if (this.newName === this.customer.cname) {
        this.$message.error("用户昵称重复");
      } else {
        // 准备临时对象，用于更改信息
        let newCustomer = this.customer;
        newCustomer.cname = this.newName;
        // 保存服务器
        axios.post('http://localhost:8181/customer/change', newCustomer)
            .then(resp => {
              this.$message({
                message: '修改成功',
                type: 'success'
              });
              // 保存本地
              this.customer = newCustomer;
              // 保存缓存
              this.$store.commit('saveCustomer', newCustomer);
            });
        this.newName = '';
        this.customerChangeNameVisible = false;
      }
    },
    changePassword() {
      // 前端验证
      if (this.newPassword0 === '' || this.newPassword1 === '' || this.newPassword2 === '') {
      } else if (this.newPassword0 !== this.customer.cpassword) {
        this.$message.error("密码错误");
      } else if (this.newPassword1 !== this.newPassword2) {
        this.$message.error("两次输入密码不一致");
      } else if (this.newPassword1 === this.customer.cpassword) {
        this.$message.error("新旧密码相同");
      } else {
        // 更改信息，准备临时对象
        let newCustomer = this.customer;
        newCustomer.cpassword = this.newPassword2;
        // 保存到服务器
        axios.post('http://localhost:8181/customer/change', newCustomer)
            .then(resp => {
              this.$message({
                message: '修改成功' + '请重新登录',
                type: 'success'
              });
              // 清空登录信息
              this.$store.commit('saveCustomer', {});
              this.customer.cname = '';
              this.customer.cpassword = '';
              this.isLogin = false;
              // 清空输入框内容
              this.newPassword0 = '';
              this.newPassword1 = '';
              this.newPassword2 = '';
              // 关闭弹窗
              this.customerChangePassWordVisible = false;
            });
        // 打开登录弹窗
        this.customerLoginVisible = true;
      }
    },
    changePasswordCancel() {
      this.newPassword0 = '';
      this.newPassword1 = '';
      this.newPassword2 = '';
      this.customerChangePassWordVisible = false;
    },
    toCustomer(page) {
      // 带参跳转。跳转到顾客界面，page是导航栏的index，读取进行页面初始化
      this.$router.push({name: 'customer', query: {activeIndex: page}});
    },
    outLogin() {
      // 清空本地顾客
      this.customer = {};
      // 清空缓存顾客
      this.$store.commit('saveCustomer', {});
      // 设置登录标识。登录标识用于切换登录注册和用户管理
      this.isLogin = false;
      this.$message({
        message: "已退出登录",
        type: 'success'
      });
    },

    // 商品列表的操作
    addToCart(index) {
      this.cart.cid = this.customer.cid;
      this.cart.gid = this.goodsData[index].goods.gid;
      this.cart.oamount = 1;
      if (this.isLogin) {
        axios.post('http://localhost:8181/customer/addToCart', this.cart).then(resp => {
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
      if (this.activeIndex === '1') {
        axios.get('http://localhost:8181/goods/searchgoods?gname=' + this.search).then(resp => {
          this.goodsData = resp.data;
        })
      } else if (this.activeIndex === '2') {
        axios.get('http://localhost:8181/goods/searchshops?sname=' + this.search).then(resp => {
          this.shopsData = resp.data;
        })
      }
    }
  },

  created() {
    // 设置搜索框
    if (this.activeIndex === '1') {
      this.searchHint = this.searchText[0];
    } else if (this.activeIndex === '2') {
      this.searchHint = this.searchText[1];
    }
    // 从缓存中获取用户信息
    if (this.$store.state.customer.cid) {
      this.isLogin = true;
      this.customer = this.$store.state.customer;
    }
    this.toPage(this.activeIndex);
  },

}
</script>

<style>
/*商品卡片css*/
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
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}
</style>