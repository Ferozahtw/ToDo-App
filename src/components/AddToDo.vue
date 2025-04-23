<template>
  <form @submit.prevent="addTodo" class="add-todo-form">
    <input
      v-model="newTodo"
      type="text"
      placeholder="Neue Aufgabe..."
      class="todo-input"
    />

    <select v-model="newPriority" class="priority-select">
      <option disabled value="">Priorität</option>
      <option value="hoch">Hoch</option>
      <option value="mittel">Mittel</option>
      <option value="niedrig">Niedrig</option>
    </select>

    <input
      v-model="newDueDate"
      type="date"
      class="date-input"
    />

    <button type="submit" class="add-btn">➕</button>
  </form>
</template>


<script setup>
import { ref } from 'vue';

const newTodo = ref('');
const newPriority = ref('');
const newDueDate = ref('');

const addTodo = () => {
  if (newTodo.value.trim()) {
    todos.value.push({
      id: Date.now(),
      text: newTodo.value,
      done: false,
      priority: newPriority.value,
      dueDate: newDueDate.value,
    });

    newTodo.value = '';
    newPriority.value = '';
    newDueDate.value = '';
  }
};
</script>

<style scoped>
.add-todo-form {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 20px;
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.05);
}

.todo-input,
.priority-select,
.date-input {
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
  flex: 1;
  min-width: 150px;
}

.add-btn {
  padding: 8px 12px;
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
