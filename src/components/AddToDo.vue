<template>
  <div>
    <h3>Neue Aufgabe hinzufügen</h3>
    <form @submit.prevent="addTodo">
      <input type="text" v-model="newTodo.title" placeholder="Aufgabe eingeben" required />
      <button type="submit">Hinzufügen</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'AddToDo',
  data() {
    return {
      newTodo: {
        title: '',
        done: false
      }
    };
  },
  methods: {
    async addTodo() {
      await axios.post('http://localhost:8080/api/todos', this.newTodo);
      this.newTodo.title = ''; // Reset input field after adding
      this.$emit('todo-added');
    }
  }
};
</script>
