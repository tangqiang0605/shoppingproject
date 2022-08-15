<template>
  <div>
    {{sid}}
  </div>
</template>

<script>
export default {
  name: "ShopVies",
  data(){
    return{
      sid:0
    }
  },
  created(){
    this.sid=this.$route.query.sid;
  }

}
</script>

<style scoped>

</style>