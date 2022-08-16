<template>
  <div>{{ delivery }}
<!--    <button @click="testPost"></button>-->
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "DeliveryView",
  data() {
    return {
      delivery: {
        did: 0,
        dname: '',
        dpassword: ''
      }
    }
  },
  methods: {
    // testPost() {
    //   axios.post('https://localhost:8181/src/testpost', {"sid": '3', "gid": '8'}).then(resp => {
    //     console.log(resp.data)
    //   });
    // }

  },
  created() {
    this.delivery = this.$store.state.delivery;
  }
}
</script>

<style scoped>

</style>