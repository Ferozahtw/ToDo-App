<template>
  <form @submit.prevent="addTodo" class="add-todo-form">
    <input
      v-model="newTodo"
      type="text"
      placeholder="Neue Aufgabe hinzufügen..."
      class="todo-input"
    />
    <button type="submit" class="add-btn">Hinzufügen</button>
  </form>
</template>

<script setup>
import { ref } from 'vue';

const newTodo = ref(''); // Ref für die neue Aufgabe

const addTodo = () => {
  if (newTodo.value.trim()) {
    // Hinzufügen des neuen ToDos zur Liste
    todos.value.push({
      id: Date.now(), // Einzigartige ID
      text: newTodo.value,
      done: false,
    });
    newTodo.value = ''; // Eingabefeld leeren
  }
};
</script>

<style scoped>
.add-todo-form {
  display: flex;
  gap: 10px;
  margin-bottom: 20px;
}

.todo-input {
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
  width: 100%;
  font-size: 16px;
}

.add-btn {
  padding: 10px 20px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-btn:hover {
  background-color: #3d9b72;
}
</style>
