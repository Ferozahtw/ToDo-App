<template>
  <li>
   <span class="task-text">{{ todo.text }}</span>
   <div class="btn-group">
     <button @click="$emit('toggleDone', todo)" class="done-btn">
       {{ todo.done ? '✓' : 'Erledigen' }}
     </button>
     <button @click="confirmDelete" class="delete-btn">✖</button>
   </div>
  </li>
</template>


<script setup>
defineProps({
  todo: Object,
});

const emit = defineEmits(['delete', 'toggleDone']);

const confirmDelete = () => {
  if (confirm('Willst du diese Aufgabe wirklich löschen?')) {
    emit('delete');
  }
};
</script>


<style scoped>
li {
  display: flex;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #ccc;
  margin: 5px 0;
}

.task-text {
  flex-grow: 1;
}

.btn-group {
  display: flex;
  gap: 5px;
}

.done-btn, .delete-btn {
  padding: 4px 8px;
  font-size: 12px;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

.done-btn {
  background-color: #4caf50;
  color: white;
}

.done-btn:hover {
  background-color: #388e3c;
}

.delete-btn {
  background-color: #ff6347;
  color: white;
}

.delete-btn:hover {
  background-color: #ff4500;
}

</style>
