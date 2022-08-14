import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    console.log("前端拦截器启动");
    // 模板
    // if(to.path.indexOf('')){
    //   if (store.state.){
    //     next();
    //   }else{
    //     alert("请登录账号");
    //     next('/');
    //   }
    // }
    if(to.path.indexOf('/customer')==0){
      if (store.state.customer.cid){
        next();
      }else{
        alert("请登录账号");
        next('/');
      }
    }

    if(to.path.indexOf('/storekeeper')==0){
      if (store.state.storeKeeper.sid){
        next();
      }else{
        alert("请登录账号");
        next('/');
      }
    }
    // console.log(to.path.match(new RegExp("^/storekeeper")));
    // console.log(to.path=='/storekeeper');
    // console.log(this.$route.query);
    // next();
    // if (store.state.customer.cname) {
    //   console.log("welcome"+store.state.customer.cname)
    //   next()
    // } else {
    //   // 跳到登录
    //   alert("拦截")
    //   next({path: '/', query: {redirect: to.fullPath}})
    // }
  } else {
    next()
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


