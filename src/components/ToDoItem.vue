<template>
  <li :class="{'done': todo.done}">
    <input
      type="checkbox"
      v-model="todo.done"
      @change="$emit('toggleDone')"
    />
    {{ todo.text }}
    <button @click="$emit('delete')" class="delete-btn">Löschen</button>
  </li>
</template>

<script setup>
defineProps({
  todo: Object,
});
</script>

<style scoped>
li {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ccc;
  margin: 5px 0;
}

li.done {
  text-decoration: line-through;
  color: #888;
}

input[type="checkbox"] {
  margin-right: 10px;
}

button.delete-btn {
  margin-left: auto;
  padding: 5px 10px;
  background-color: #ff6347;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button.delete-btn:hover {
  background-color: #ff4500;
}

/* Animation für das Löschen */
.delete-btn {
  transition: background-color 0.3s;
}
</style>
