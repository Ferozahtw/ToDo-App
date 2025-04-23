<template>
  <div>
    <h2>ToDo Liste</h2>
    <div class="filter">
      <button @click="filterTodos('all')">Alle</button>
      <button @click="filterTodos('done')">Erledigt</button>
      <button @click="filterTodos('undone')">Nicht erledigt</button>
    </div>
    <transition-group name="fade" tag="ul">
      <ToDoItem
        v-for="todo in filteredTodos"
        :key="todo.id"
        :todo="todo"
        @delete="deleteTodo(todo.id)"
        @toggleDone="toggleTodoDone(todo)"
      />
    </transition-group>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import ToDoItem from './ToDoItem.vue';

// Beispielhafte ToDo-Liste
const todos = ref([
  { id: 1, text: 'Einkaufen', done: false },
  { id: 2, text: 'Hausaufgaben machen', done: false },
  { id: 3, text: 'Fahrrad reparieren', done: true },
]);

// Filterstatus und Filterfunktion
const filterStatus = ref('all');

const filterTodos = (status) => {
  filterStatus.value = status;
};

const filteredTodos = computed(() => {
  let result = todos.value;

  if (filterStatus.value === 'done') {
    return result.filter(todo => todo.done);
  } else if (filterStatus.value === 'undone') {
    return result.filter(todo => !todo.done);
  } else {
    // Sortiere: nicht erledigte zuerst
    return [...result].sort((a, b) => a.done - b.done);
  }
});


// Funktionen für das Markieren von erledigten Aufgaben und Löschen
const toggleTodoDone = (todo) => {
  todo.done = !todo.done;
};

const deleteTodo = (id) => {
  const index = todos.value.findIndex(todo => todo.id === id);
  if (index !== -1) {
    todos.value.splice(index, 1);
  }
};
</script>

<style scoped>
/* Filterbuttons Styling */
.filter {
  margin-bottom: 10px;
}

.filter button {
  margin-right: 10px;
  padding: 5px 10px;
  background-color: #42b983;
  color: white;
  border: none;
  cursor: pointer;
}

.filter button:hover {
  background-color: #3d9b72;
}

/* Animationen */
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s ease;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

li.done {
  background-color: var(--color-background-soft);
  border-left: 4px solid green;
}

</style>
