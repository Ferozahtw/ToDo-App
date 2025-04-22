<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <input
          type="email"
          v-model="email"
          placeholder="Email"
          required
          class="input-field"
        />
      </div>
      <div class="form-group">
        <input
          type="password"
          v-model="password"
          placeholder="Password"
          required
          class="input-field"
        />
      </div>
      <button type="submit" class="submit-btn">Anmelden</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    async login() {
      const response = await axios.post('http://localhost:8080/api/login', {
        email: this.email,
        password: this.password
      });
      localStorage.setItem('auth-token', response.data.token); // Save token for authentication
      this.$router.push('/todos'); // Redirect to the ToDo page after successful login
    }
  }
};
</script>

<style scoped>
/* Styling for the login form */
form {
  display: flex;
  flex-direction: column;
  gap: 1rem; /* Adds space between the form fields */
  max-width: 400px;
  margin: 0 auto;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.input-field {
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-btn {
  padding: 10px;
  font-size: 1rem;
  background-color: #42b983; /* Green button */
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-btn:hover {
  background-color: #36a270; /* Darker green when hovered */
}
</style>
