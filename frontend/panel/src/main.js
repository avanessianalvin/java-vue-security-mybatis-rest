import { createApp } from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import { loadFonts } from './plugins/webfontloader';
import router from './router';
import store from './store'; // Import the Vuex store

import {ShowToast} from '@/assets/toastify'
import 'toastify-js/src/toastify.css';
import '@/assets/toastify-styles.css';


import VuetifyDialogPromise from "vuetify-dialog-promise";

//import axios from "axios";
//axios.defaults.baseURL = process.env.VUE_APP_API_URL

loadFonts();

const app = createApp(App);
    app.use(router)
    .use(store) // Use the Vuex store
    .use(vuetify)
    .use(VuetifyDialogPromise)
    .mount('#app');

app.config.globalProperties.$toast = ShowToast;
