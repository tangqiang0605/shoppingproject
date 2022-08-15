<template>
  <div>
    {{testInf}}
    <el-row>
      <el-col :span="8">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1">已上架</el-menu-item>
          <el-menu-item index="2">仓库中</el-menu-item>
          <el-menu-item index="3">订单处理</el-menu-item>
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
            <template slot="title">您好！{{storeKeeper.sname}}店长</template>
            <el-menu-item index="5-1" @click="changeName">修改昵称</el-menu-item>
            <el-menu-item index="5-2" @click="changePassword">修改密码</el-menu-item>
            <el-menu-item index="5-5" @click="outLogin">退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>


    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>
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
              :on-success="handleSuccess"
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
        <el-button type="primary" @click="submitForm('form')">确 定</el-button>
      </div>
    </el-dialog>
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

      testInf:'',

      activeIndex:'1',

      storeKeeper:{
        sid:'',
        sname:'',
        spassword:'',
        isban:false
      },

    }
  },
  methods: {
    outLogin(){
      this.$store.commit('saveStoreKeeper',{});
      this.$router.push('/');
    },

    handleRemove(file) {
      this.$refs.pictureUpload.handleRemove(file)
      this.goods.srcid = 0;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleExceed() {
      this.$message.error("只能上传一张封面");
    },
    handleSuccess(resp) {
      this.goods.srcid = resp;
    },
    getSid() {
      this.inf = this.goods.srcid;
    },


    submitForm(formName) {
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
            this.goods.sid = 100;
            // this.goods.srcid = 100;
            // 从表单读取的值
            this.goods.gsave = n;

            this.dialogFormVisible = false;
            axios.post('http://localhost:8181/storekeeper/addgoods/base', this.goods).then(
                resp => {
                  if (resp.data != 0) {
                    this.goods = {};
                    // this.inf=this.goods;
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

    this.storeKeeper=this.$store.state.storeKeeper;

    // if(this.$store.state.storeKeeper.sid){
    //   this.testInf=this.$store.state.storeKeeper.sname;
    // }else{
    //
    // }
  }

}
</script>

<style scoped>

</style>