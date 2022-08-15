<template>
<div>{{delivery}}</div>
</template>

<script>
export default {
  name: "DeliveryView",
  data(){
    return{
      delivery:{
        did:0,
        dname:'',
        dpassword:''
      }
    }
  },
  created() {
    this.delivery=this.$store.state.delivery;
  }
}
</script>

<style scoped>

</style>