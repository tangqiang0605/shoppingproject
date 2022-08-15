<template>
<div>
  {{activeIndex}}
</div>
</template>

<script>
export default {
  name: "Customer",
  data(){
    return {
      activeIndex:'1',
    }

  },
  created() {
    this.activeIndex=this.$route.query.activeIndex;
  }
}
</script>

<style scoped>

</style>