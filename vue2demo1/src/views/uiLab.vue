<template>
  <div>
    {{uploadTime}}
    <el-button @click="upload">ok</el-button>
  <div class="block">
    <span class="demonstration">默认</span>
    <el-date-picker v-model="uploadTime" type="datetime" value-format="timestamp" placeholder="选择日期时间"></el-date-picker>

  </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "uiLab",
  data() {
    return {
      uploadTime:0,

    };
  },
  methods:{
    upload(){
      console.log(this.uploadTime);
      // axios.get('http://localhost:8181/storekeeper/time?time='+this.uploadTime);
    }
  }

}
</script>

<style scoped>

</style>