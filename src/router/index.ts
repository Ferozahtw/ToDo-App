import { createRouter, createWebHistory } from 'vue-router';
import ToDoList from '../components/ToDoList.vue';
import AddToDo from '../components/AddToDo.vue';
import Login from '../components/Login.vue';
import Register from '../components/Register.vue';

const routes = [
  {
    path: '/',
    name: 'ToDoList',
    component: ToDoList,
  },
  {
    path: '/add',
    name: 'AddToDo',
    component: AddToDo,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
