import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    customer: {
      cid:
          window.localStorage
              .getItem('customerinfo' || '[]') == null ? 0 : JSON.parse(window.localStorage.getItem('customerinfo' || '[]'))
              .cid,
      cname:
          window.localStorage
              .getItem('customerinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('customerinfo' || '[]'))
              .cname,
      cpassword:
          window.localStorage
              .getItem('customerinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('customerinfo' || '[]'))
              .cpassword

    },
    delivery: {
      did:
          window.localStorage
              .getItem('deliveryinfo' || '[]') == null ? 0 : JSON.parse(window.localStorage.getItem('deliveryinfo' || '[]'))
              .did,
      dname:
          window.localStorage
              .getItem('deliveryinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('deliveryinfo' || '[]'))
              .dname,
      dpassword:
          window.localStorage
              .getItem('deliveryinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('deliveryinfo' || '[]'))
              .dpassword

    },
    storeKeeper: {
      sid:
          window.localStorage
              .getItem('storekeeperinfo' || '[]') == null ? 0 : JSON.parse(window.localStorage.getItem('storekeeperinfo' || '[]'))
              .sid,
      sname:
          window.localStorage
              .getItem('storekeeperinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('storekeeperinfo' || '[]'))
              .sname,
      spassword:
          window.localStorage
              .getItem('storekeeperinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('storekeeperinfo' || '[]'))
              .spassword,
      isban:
          window.localStorage
              .getItem('storekeeperinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('storekeeperinfo' || '[]'))
              .isban

    }
  },
  getters: {
  },
  mutations: {
    saveCustomer(state, customer) {
      state.customer = customer;
      window.localStorage.setItem('customerinfo', JSON.stringify(customer))
    },
    saveDelivery(state, delivery) {
      state.delivery = delivery;
      window.localStorage.setItem('deliveryinfo', JSON.stringify(delivery))
    },
    saveStoreKeeper(state, storeKeeper) {
      state.storeKeeper = storeKeeper;
      window.localStorage.setItem('storekeeperinfo', JSON.stringify(storeKeeper))
    }
  },
  actions: {
  },
  modules: {
  }
})
