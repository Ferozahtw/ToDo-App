import { createRouter, createWebHistory } from 'vue-router';
import ToDoList from '@/components/ToDoList.vue';
import AddToDo from '@/components/AddToDo.vue';
import Login from '@/components/Login.vue';
import Register from '@/components/Register.vue';

const routes = [
  { path: '/', component: ToDoList },
  { path: '/add', component: AddToDo },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
