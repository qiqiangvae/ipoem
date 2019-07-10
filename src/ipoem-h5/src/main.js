// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import MintUI from 'mint-ui'
import router from './router'
import Api from './utils/api'
import VueAxios from 'vue-axios'
import AlloyFinger from 'alloyfinger'
import AlloyFingerPlugin from 'alloyfinger/vue/alloy_finger_vue'

Vue.config.productionTip = false
Vue.use(MintUI)
Vue.use(VueAxios, Api)
Vue.use(AlloyFingerPlugin, {AlloyFinger})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
