import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // importieren!

const app = createApp(App);

app.use(router); // Router verwenden
app.mount('#app');
