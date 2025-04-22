<template>
  <div>
    <h2>Registrieren</h2>
    <form @submit.prevent="handleRegister">
      <div>
        <label for="email">E-Mail:</label>
        <input
          type="email"
          id="email"
          v-model="email"
          required
          placeholder="E-Mail eingeben"
        />
      </div>

      <div>
        <label for="password">Passwort:</label>
        <input
          type="password"
          id="password"
          v-model="password"
          required
          placeholder="Passwort eingeben"
        />
      </div>

      <div>
        <label for="confirmPassword">Passwort wiederholen:</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="confirmPassword"
          required
          placeholder="Passwort wiederholen"
        />
      </div>

      <div v-if="password !== confirmPassword && confirmPassword !== ''" style="color: red;">
        Passwörter stimmen nicht überein!
      </div>

      <button type="submit" :disabled="password !== confirmPassword">
        Registrieren
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

// E-Mail, Passwort und Passwortwiederholung als Refs definieren
const email = ref('');
const password = ref('');
const confirmPassword = ref('');

// Zugriff auf den Router
const router = useRouter();

// Registrierungsmethode
const handleRegister = async () => {
  // Anfrage an Backend senden, um Benutzer zu registrieren
  try {
    const response = await axios.post('http://localhost:8080/api/register', {
      email: email.value,
      password: password.value
    });

    // Erfolgreiche Registrierung – Weiterleitung zum Login
    if (response.status === 200) {
      router.push('/login');
    }
  } catch (error) {
    console.error('Fehler bei der Registrierung:', error);
  }
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
}

div {
  margin-bottom: 1rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  margin-top: 0.5rem;
}

button {
  width: 100%;
  padding: 0.75rem;
  font-size: 1.2rem;
  background-color: #42b983;
  color: white;
  border: none;
  cursor: pointer;
  margin-top: 1rem;
}

button:disabled {
  background-color: grey;
}

div[v-if] {
  color: red;
  font-size: 0.9rem;
  margin-top: 0.5rem;
}
</style>
