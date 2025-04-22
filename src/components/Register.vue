<template>
  <div>
    <h2>Registrierung</h2>
    <form @submit.prevent="register">
      <input type="email" v-model="email" placeholder="Email" required />
      <input type="password" v-model="password" placeholder="Password" required />
      <button type="submit">Registrieren</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    async register() {
      await axios.post('http://localhost:8080/api/register', {
        email: this.email,
        password: this.password
      });
      this.$router.push('/login'); // Redirect to login page after successful registration
    }
  }
};
</script>
