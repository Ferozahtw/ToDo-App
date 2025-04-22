<template>
  <div>
    <h2>ToDo Liste</h2>
    <ul>
      <ToDoItem
      v-for="todo in todos"
      :key="todo.id"
      :todo="todo"
      />
    </ul>
  </div>
</template>

<script setup>
import ToDoItem from './ToDoItem.vue';

export default {
  name: 'ToDoList',
  components: {
    ToDoItem
  },
  data() {
    return {
      todos: []
    };
  },
  mounted() {
    this.fetchTodos();
  },
  methods: {
    async fetchTodos() {
      const response = await axios.get('http://localhost:8080/api/todos');
      this.todos = response.data;
    }
  }
};
</script>
