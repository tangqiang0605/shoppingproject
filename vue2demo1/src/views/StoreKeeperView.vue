<template>
  <div>
    {{ testInf }}
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" @click="toOnline">已上架</el-menu-item>
          <el-menu-item index="2" @click="toRepository">仓库中</el-menu-item>
          <el-menu-item index="3" @click="toOrders">订单处理</el-menu-item>
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
            <template slot="title">您好！{{ storeKeeper.sname }}店长</template>
            <el-menu-item index="5-3" @click="dialogFormVisible = true">新增商品</el-menu-item>
            <el-menu-item index="5-1" @click="changeName">修改昵称</el-menu-item>
            <el-menu-item index="5-2" @click="changePassword">修改密码</el-menu-item>
            <el-menu-item index="5-5" @click="outLogin">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>

    <!--    添加商品-->
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form :model="goods">
        <el-form-item label="商品名" :label-width="formLabelWidth">
          <el-input v-model="goods.gname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数量" :label-width="formLabelWidth">
          <el-input v-model="goods.gsave" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="实物图" :label-width="formLabelWidth">
          <el-upload
              action="http://localhost:8181/src/upload"
              list-type="picture-card"
              :limit="1"
              :on-exceed="handleExceed"
              ref="pictureUpload"
          >
            <i slot="default" class="el-icon-plus"></i>
            <div slot="file" slot-scope="{file}">
              <img
                  class="el-upload-list__item-thumbnail"
                  :src="file.url" alt=""
              >
              <span class="el-upload-list__item-actions">
        <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>

        <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file)"
        >
          <i class="el-icon-delete"></i>
        </span>

      </span>
            </div>
          </el-upload>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm()">确 定</el-button>
      </div>
    </el-dialog>
    <!--    查看图片-->
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>

    <!--不展示的表格-->
    <el-table
        v-show="false"
        :data="goodsData"
        border
        style="width: 100%">
      <!--      fixed prop label width-->
      <el-table-column
          label="商品"
          width="120">
        <template slot-scope="scope">
          <el-image
              fit="contain"
              style="width: 100px; height: 100px"
              :src="scope.row.srcurl"
              :preview-src-list="[scope.row.srcurl]"></el-image>
        </template>
      </el-table-column>
      <el-table-column
          prop="goods.gname"
          label="名称"
          width="120">
      </el-table-column>
      <el-table-column
          prop="sname"
          label="商家"
          width="120">
      </el-table-column>
      <el-table-column
          prop="goods.gsales"
          label="销售量"
          width="120">
      </el-table-column>
      <el-table-column
          prop="goods.gonlinenum"
          label="剩余"
          width="120">
      </el-table-column>

      <el-table-column
          fixed="right"
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-button @click="addToCart(scope.row)" type="text" size="small">加入购物车</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row v-show="activeIndex==='1'">
      <el-table
          v-show="isLogin"
          :data="goodsData"
          border
          style="margin: 30px">
        <!--      fixed prop label width-->
        <el-table-column
            prop="srcurl"
            label="商品图"
            width="180">
          <!--          <template slot-scope="scope">-->
          <!--            <el-image :src="scope.srcurl"></el-image>-->
          <!--          </template>-->
          <template slot-scope="scope">
            <el-image
                fit="contain"
                style="width: 150px; height: 150px"
                :src="scope.row.srcurl"
                :preview-src-list="[scope.row.srcurl]"></el-image>
          </template>
        </el-table-column>
        <!--        <el-table-column-->
        <!--            prop="sname"-->
        <!--            label="店铺名称"-->
        <!--            width="120">-->
        <!--        </el-table-column>-->
        <el-table-column
            prop="goods.gname"
            label="商品名"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gid"
            label="商品id"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gsave"
            label="库存"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gsales"
            label="销售量"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gonlinenum"
            label="在售"
            width="120">
        </el-table-column>
        <el-table-column
            label="操作"
            width="100">
          <template slot-scope="scope">
            <el-button @click="changeState(scope.row,'仓库中')" type="danger" size="small" v-if="!scope.row.isban">下架
            </el-button>
            <!--            <el-button @click="changeState(scope.row,false)" type="text" size="small" v-if="scope.row.isban">解除封禁</el-button>-->
          </template>
        </el-table-column>
      </el-table>
    </el-row>


    <el-row v-show="activeIndex==='2'">
      <el-table
          v-show="isLogin"
          :data="goodsData"
          border
          style="margin: 30px">
        <!--      fixed prop label width-->
        <el-table-column
            prop="srcurl"
            label="商品图"
            width="180">
          <!--          <template slot-scope="scope">-->
          <!--            <el-image :src="scope.srcurl"></el-image>-->
          <!--          </template>-->
          <template slot-scope="scope">
            <el-image
                fit="contain"
                style="width: 150px; height: 150px"
                :src="scope.row.srcurl"
                :preview-src-list="[scope.row.srcurl]"></el-image>
          </template>
        </el-table-column>
        <!--        <el-table-column-->
        <!--            prop="sname"-->
        <!--            label="店铺名称"-->
        <!--            width="120">-->
        <!--        </el-table-column>-->
        <el-table-column
            prop="goods.gname"
            label="商品名"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gid"
            label="商品id"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gsave"
            label="库存"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gsales"
            label="销售量"
            width="120">
        </el-table-column>
        <el-table-column
            prop="goods.gonlinenum"
            label="在售"
            width="120">
        </el-table-column>
        <el-table-column
            label="操作"
            width="240">
          <template slot-scope="scope">
            <el-button @click="changeState(scope.row,'已上架')" type="success" size="small">上架</el-button>
            <el-button @click="changeGoods" type="primary" size="small">修改</el-button>
            <el-button @click="delGoods(scope.row)" type="danger" size="small">删除</el-button>
            <!--            <el-button @click="changeState(scope.row,false)" type="text" size="small" v-if="scope.row.isban">解除封禁</el-button>-->
          </template>
        </el-table-column>
      </el-table>
    </el-row>


    <el-row v-show="activeIndex==='3'">
      <el-row v-for="(item,orderIndex) in ordersCartsData" style="margin: 40px">
        <el-card shadow="hover">
          <el-descriptions title="订单信息">
            <el-descriptions-item label="订单号">{{ item.orders.oid }}</el-descriptions-item>
            <!--        <el-descriptions-item label="配送员">{{item.orders.did}}</el-descriptions-item>-->
            <el-descriptions-item label="顾客id">{{ item.orders.cid }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <!--              <el-tag size="small">{{ item.orders.ostate }}</el-tag>-->
              <el-tag size="small" type="info" v-show="item.orders.ostate=='待发货'">待发货</el-tag>
              <el-tag size="small" type="warning" v-show="item.orders.ostate=='已发货'">已发货</el-tag>
              <el-tag size="small" type="danger" v-show="item.orders.ostate=='待取货'">待取货</el-tag>
              <el-tag size="small" type="" v-show="item.orders.ostate=='配送中'">配送中</el-tag>
              <el-tag size="small" type="danger" v-show="item.orders.ostate=='已送达'">已送达</el-tag>
              <el-tag size="small" type="success" v-show="item.orders.ostate=='已完成'">已完成</el-tag>
              <!--              <el-tag type="success">标签二</el-tag>-->
              <!--              <el-tag type="info">标签三</el-tag>-->
              <!--              <el-tag type="warning">标签四</el-tag>-->
              <!--              <el-tag type="danger">标签五</el-tag>-->
            </el-descriptions-item>
          </el-descriptions>
          <div style="font-size: 15px;color: #999999;margin-bottom: 10px" v-for="(cart,index) in item.carts">
            商品{{ index + 1 }}:{{ cart.gname }}&emsp;数量：{{ cart.oamount }}
          </div>
          <!--          未送达之前是没有完成按钮的，而完成后会显示不可操作的完成按钮-->
          <el-button type="success" size="small" style="margin-top: 10px"
                     v-show="item.orders.ostate=='待发货'"
                     @click="finishOrders(orderIndex,'已发货')">发布为配送任务
          </el-button>
          <el-button type="success" size="small" style="margin-top: 10px"
                     v-show="item.orders.ostate=='待发货'"
                     @click="finishOrders(orderIndex,'待取货')">等待顾客取货
          </el-button>
        </el-card>
      </el-row>
    </el-row>


  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "StoreKeeperView",
  // components: {AddGoods},
  data() {
    return {
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      isLogin: false,


      dialogFormVisible: false,
      goods: {
        gid: 0,
        gsave: '',
        gsales: 0,
        sid: 0,
        state: '仓库中',
        srcid: 0,
        gname: '',
        gonlinenum: 0,
        time: null
      },
      formLabelWidth: '120px',
      title: '添加商品',

      testInf: '',

      activeIndex: '1',

      storeKeeper: {
        sid: '',
        sname: '',
        spassword: '',
        isban: false
      },

      goodsImg: {},
      goodsData: [],
      ordersData: [],
      ordersCartsData: [],

    }
  },
  methods: {
    changeGoods() {

    },

    delGoods(showingGoods) {

      this.$confirm('将从仓库永久移除该商品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        showingGoods.goods.state = '已删除';
        //   更新远程数据
        axios.post('http://localhost:8181/storekeeper/updategoodsstate', showingGoods).then(resp => {
          axios.get('http://localhost:8181/storekeeper/showrepository?sid=' + this.storeKeeper.sid).then(resp => {
            this.goodsData = resp.data;
          })
        })
        //   更新本地数据
        // if (this.shopCartsData[shopIndex].carts.length == 1) {
        //   this.shopCartsData.splice(shopIndex, 1);
        // } else {
        //   this.shopCartsData[shopIndex].carts.splice(cartIndex, 1);
        // }
      }).catch();
    },


    toOnline() {
      this.activeIndex = '1';
      axios.get('http://localhost:8181/storekeeper/findgoodsbysid?sid=' + this.storeKeeper.sid).then(resp => {
        this.goodsData = resp.data;
      })
    },
    toRepository() {
      this.activeIndex = '2';
      // alert(this.activeIndex);
      axios.get('http://localhost:8181/storekeeper/showrepository?sid=' + this.storeKeeper.sid).then(resp => {
        this.goodsData = resp.data;
      })
    },
    toOrders() {
      this.activeIndex = '3';
      // this.activeIndex = '2';
      axios.get("http://localhost:8181/storekeeper/getorders?sid=" + this.storeKeeper.sid).then(resp => {
        this.ordersCartsData.length = 0;
        for (const index in resp.data) {
          axios.get('http://localhost:8181/customer/getorderson?oid=' + resp.data[index].oid).then(resp1 => {
            this.ordersCartsData.push({orders: resp.data[index], carts: resp1.data});
          })
        }
      })

    },
    finishOrders(orderIndex, state) {

      // 更新远程数据
      axios.get('http://localhost:8181/storekeeper/updateodersstate?oid=' + this.ordersCartsData[orderIndex].orders.oid + '&ostate=' + state).then(
          resp => {

            // 更新本地数据
            this.ordersCartsData[orderIndex].orders.ostate = state;
          }
      )

    },
    changeState(goods, state) {
      // 改变商品state
      goods.goods.state = state;
      // console.log(goods.goods);
      axios.post('http://localhost:8181/storekeeper/updategoodsstate', goods).then(resp => {
        if (this.activeIndex == '1') {
          axios.get('http://localhost:8181/storekeeper/findgoodsbysid?sid=' + this.storeKeeper.sid).then(resp => {
            // 修改本地数据
            this.goodsData = resp.data;
          })
        } else if (this.activeIndex == '2') {
          axios.get('http://localhost:8181/storekeeper/showrepository?sid=' + this.storeKeeper.sid).then(resp => {
            this.goodsData = resp.data;
          })
        }

      })

    },
    // 用户管理
    changeName() {
      // this.testInf = "changName";
      if (this.newName === '') {

      } else if (this.newName === this.storeKeeper.sname) {
        this.$message.error("用户昵称重复");
      } else {
        var newCustomer = this.storeKeeper;
        newCustomer.sname = this.newName;
        axios.post('http://localhost:8181/storekeeper/change', newCustomer)
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
    outLogin() {
      this.$store.commit('saveStoreKeeper', {});
      this.$router.push('/');
    },

    // 文件处理
    handleRemove(file) {
      this.$refs.pictureUpload.handleRemove(file);
      this.goods.srcid = 0;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleExceed() {
      this.$message.error("只能上传一张封面");
    },
    // handleSuccess(response, file, fileList){
    //   this.goodsImg=file;
    //   alert(this.goodsImg.url);
    // },

    submitForm() {


      try {
        this.goods.srcid = this.$refs.pictureUpload.$children[1]._props.fileList[0].response;
      } catch (err) {
        this.goods.srcid = 0;
      }

      if (this.goods.gname == "") {
        this.$message.error("商品名不能为空");
      } else if (this.goods.gsave == "") {
        this.$message.error("数量不能为空");
      } else if (this.goods.srcid == 0) {
        this.$message.error("请上传商品实物图");
      } else {
        var n = Number(this.goods.gsave);
        if (!isNaN(n)) {
          if (n < 0) {
            this.$message.error("数量不能为负数");
          } else {
            // 默认值
            this.goods.gsales = 0;
            this.goods.state = "仓库中";
            this.goods.gonlinenum = 0;

            // 从store读取的值
            // todo需要重新重store读取并赋值
            // this.goods.sid = 100;
            this.goods.sid = this.storeKeeper.sid;
            // this.goods.srcid = 100;
            // 从表单读取的值
            this.goods.gsave = n;

            this.dialogFormVisible = false;
            axios.post('http://localhost:8181/storekeeper/addgoods/base', this.goods).then(
                resp => {
                  if (resp.data != 0) {
                    this.goods = {};
                    // this.inf=this.goods;
                    if (this.activeIndex == '2') {
                      axios.get('http://localhost:8181/storekeeper/showrepository?sid=' + this.storeKeeper.sid).then(resp => {
                        this.goodsData = resp.data;
                      })
                    }
                    this.$message({
                      message: '添加成功，返回商品id' + resp.data,
                      type: 'success'
                    });

                  } else {
                    this.$message.error("storekeeper接口报错，添加进数据库时数据不匹配出现问题返回id0");
                  }
                }
            )


          }


        } else {
          this.$message.error("数量必须为数字");
        }
      }
    },
  },
  created() {
    this.storeKeeper = this.$store.state.storeKeeper;
    this.isLogin = true;
    if (this.activeIndex == '1') {
      axios.get('http://localhost:8181/storekeeper/findgoodsbysid?sid=' + this.storeKeeper.sid).then(resp => {
        this.goodsData = resp.data;
        // this.testInf=resp.data;
      })

    } else if (this.activeIndex == '2') {
      axios.get('http://localhost:8181/storekeeper/showrepository?sid=' + this.storeKeeper.sid).then(resp => {
        this.goodsData = resp.data;
      })
    }
  }

}
</script>

<style scoped>

</style>