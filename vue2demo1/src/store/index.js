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
      isbank:
          window.localStorage
              .getItem('storekeeperinfo' || '[]') == null ? '' : JSON.parse(window.localStorage.getItem('storekeeperinfo' || '[]'))
              .isbank

    }
  },
  getters: {
  },
  mutations: {
    saveCustomer(state, customer) {
      state.customer = customer;
      window.localStorage.setItem('customerinfo', JSON.stringify(customer))
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
