import { createApp } from 'vue';
import axios from 'axios';
import App from './App.vue';
import router from './router';
import store from './store';

// createApp(App).use(store).use(router).mount('#app');
const app = createApp(App);
app.config.globalProperties.axios = axios;
app.use(store).use(router).mount('#app');
