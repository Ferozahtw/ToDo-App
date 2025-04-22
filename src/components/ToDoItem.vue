<template>
  <li>
    <span>{{ todo.title }}</span>
    <button @click="markAsDone">{{ todo.done ? 'Mark as undone' : 'Mark as done' }}</button>
    <button @click="deleteTodo">Delete</button>
  </li>
</template>

<script>
export default {
  name: 'ToDoItem',
  props: {
    todo: Object
  },
  methods: {
    async markAsDone() {
      this.todo.done = !this.todo.done;
      await axios.put(`http://localhost:8080/api/todos/${this.todo.id}`, this.todo);
    },
    async deleteTodo() {
      await axios.delete(`http://localhost:8080/api/todos/${this.todo.id}`);
      this.$emit('todo-deleted');
    }
  }
};
</script>
