import Vue from 'vue';
import App from './App.vue';
import { router } from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
  faUserSecret,
} from '@fortawesome/free-solid-svg-icons';
import ListBox from './components/ListBox';
import List from './components/List';
library.add( faUser, faUserPlus, faSignInAlt, faSignOutAlt,faUserSecret);


Vue.config.productionTip = false;

Vue.use(VeeValidate);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('my-listBox',ListBox);
Vue.component('my-list',List);

Vue.use(Vuex);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
