<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input type="email" v-model="email" placeholder="Email" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Anmelden</button>
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
