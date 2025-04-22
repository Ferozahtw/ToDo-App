<script setup>
import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="100" />

    <nav>
      <RouterLink to="/">ToDos</RouterLink>
      <RouterLink to="/add">Neue Aufgabe</RouterLink>
      <RouterLink to="/login">Login</RouterLink>
      <RouterLink to="/register">Registrieren</RouterLink>
    </nav>
  </header>

  <main>
    <RouterView />
  </main>
</template>

<style scoped>
header {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
}

nav {
  display: flex;
  gap: 1rem;
  margin-top: 1rem;
}

nav a {
  text-decoration: none;
  color: #42b983;
  font-weight: bold;
}

nav a.router-link-exact-active {
  text-decoration: underline;
}

main {
  padding: 2rem;
}
</style>
