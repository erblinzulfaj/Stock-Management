import './assets/main.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from '@/router/index.js';
import { createPinia } from 'pinia';

// Create Pinia instance
const pinia = createPinia();

// Create Vue app instance
const app = createApp(App);

// Use router and pinia with the Vue app
app.use(router);
app.use(pinia);

// Mount the app to the DOM
app.mount('#app');
