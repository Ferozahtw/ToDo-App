<template>
  <div class="login-container">
    <h2>Anmeldung</h2>

    <!-- Social Login Buttons -->
    <div class="social-login">
      <!-- Google Login -->
      <button class="social-button google" @click.prevent="loginWith('Google')">
        <span class="icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 533.5 544.3">
            <path fill="#4285F4" d="M533.5 278.4c0-18.7-1.5-37-4.3-54.7H272v103.6h146.9c-6.4 34.8-25.6 64.3-54.6 84.1v69h88.4c51.7-47.7 80.8-118 80.8-202z"/>
            <path fill="#34A853" d="M272 544.3c73.6 0 135.3-24.4 180.4-66.2l-88.4-69c-24.6 16.5-56.2 26.4-92 26.4-70.7 0-130.7-47.7-152.1-111.9H27.6v70.3C72.6 483.9 165.8 544.3 272 544.3z"/>
            <path fill="#FBBC04" d="M119.9 323.6c-10.7-31.7-10.7-65.5 0-97.2V156H27.6c-34.8 69.6-34.8 152 0 221.6l92.3-54z"/>
            <path fill="#EA4335" d="M272 107.7c39.9 0 75.7 13.7 104 40.5l78-78.1C407.2 24.4 345.5 0 272 0 165.8 0 72.6 60.4 27.6 156l92.3 70.4C141.3 155.4 201.3 107.7 272 107.7z"/>
          </svg>
        </span>
        <span>Mit Google anmelden</span>
      </button>

      <!-- Facebook Login -->
      <button class="social-button facebook" @click.prevent="loginWith('Facebook')">
        <span class="icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 320 512">
            <path fill="#1877F2" d="M279.14 288l14.22-92.66h-88.91V127.76c0-25.35 12.42-50.06 52.24-50.06h40.42V6.26S270.43 0 240.33 0c-73.22 0-121.12 44.38-121.12 124.72v70.62H71.56V288h47.65v224h92.66V288z"/>
          </svg>
        </span>
        <span>Mit Facebook anmelden</span>
      </button>

      <!-- Apple Login -->
      <button class="social-button apple" @click.prevent="loginWith('Apple')">
        <span class="icon">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 384 512">
            <path fill="#000000" d="M318.7 268.6c-0.5-47.6 39.1-70.4 40.9-71.6-22.4-32.6-57.3-37.1-69.8-37.6-29.6-3-57.7 17.4-72.7 17.4s-38.1-17-62.5-16.5c-32.1 0.5-61.8 18.7-78.3 47.6-33.4 57.9-8.5 143.4 23.9 190.4 15.9 23.1 34.9 48.9 59.8 48 23.6-0.9 32.6-15.4 61-15.4s36.2 15.4 61.3 15c25.3-0.5 41.3-23.5 56.8-46.7 17.8-26 25.2-51.2 25.6-52.5-0.5-0.2-48.7-18.6-49.2-73.1zM250.2 92.7c13.4-16.2 22.4-38.9 19.9-61.5-19.3 0.8-42.6 12.8-56.4 29-12.4 14.4-23.2 37.5-20.3 59.5 21.5 1.6 43.5-10.9 56.8-27z"/>
          </svg>
        </span>
        <span>Mit Apple anmelden</span>
      </button>
    </div>

    <div class="separator">oder</div>

    <!-- Klassisches Login -->
    <form @submit.prevent="login" class="form-fields">
      <input type="email" v-model="email" placeholder="E-Mail-Adresse" required />
      <input type="password" v-model="password" placeholder="Passwort" required />

      <div class="remember-row">
        <label class="remember-checkbox">
          <input type="checkbox" v-model="rememberMe" />
          <span>Angemeldet bleiben</span>
        </label>
      </div>

      <div class="links">
        <a href="#">Passwort vergessen oder Probleme bei der Anmeldung?</a>
      </div>

      <button type="submit" class="login-btn">Anmelden</button>

      <div class="register-option">
        <span>Neu bei ToDoList?</span>
        <button @click.prevent="goToRegister" class="register-btn">Jetzt Registrieren</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const email = ref('');
const password = ref('');
const rememberMe = ref(false);
const router = useRouter();

const login = async () => {
  // klassisches E-Mail/Passwort-Login
};

const goToRegister = () => {
  router.push('/register');
};

const loginWith = (provider) => {
  alert(`Weiter mit ${provider} wird bald unterstützt`);
  // später: window.location.href = `/oauth2/authorize/${provider.toLowerCase()}`
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 0 auto;
  padding: 1rem;
  background: white;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

.social-login {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 1.5rem;
}

.social-button {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  padding: 0.75rem;
  border-radius: 4px;
  border: 1px solid #ccc;
  background-color: #f5f5f5;
  color: #000;  /* Setze die Textfarbe auf Schwarz */
  cursor: pointer;
  font-weight: bold;
}

.google {
  border-color: #ccc; /* Anpassen der Randfarbe, um sie neutraler zu machen */
}

.facebook {
  border-color: #ccc; /* Anpassen der Randfarbe, um sie neutraler zu machen */
}

.apple {
  border-color: #ccc; /* Anpassen der Randfarbe, um sie neutraler zu machen */
}


.separator {
  text-align: center;
  margin: 1rem 0;
  font-size: 0.9rem;
  color: #666;
}

.form-fields {
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
}

input[type="email"],
input[type="password"] {
  width: 100%;
  padding: 0.6rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}

.remember-row,
.links {
  font-size: 0.85rem;
  line-height: 1.4;
  color: #333;
}

.remember-checkbox {
  display: flex;
  align-items: center;
  gap: 0.4rem;
  font-size: 0.9rem;
  color: #333;
}

.remember-checkbox span {
  font-size: 0.85rem;
}

.links {
  font-size: 0.9rem;
  text-align: left;
  margin-top: -0.3rem;
}

.links a {
  color: #4285f4;
  text-decoration: none;
  font-size: 0.85rem;
}

.links a:hover {
  text-decoration: underline;
}

.login-btn {
  background-color: #4285f4;
  color: white;
  font-size: 1rem;
  padding: 0.7rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.login-btn:hover {
  background-color: #3367d6;
}

.register-option {
  margin-top: 1.2rem;
  padding-top: 0.5rem;
  border-top: 1px solid #e0e0e0;
  text-align: center;
  font-size: 0.9rem;
}

.register-btn {
  margin-top: 0.3rem;
  background: none;
  border: none;
  color: #4285f4;
  font-weight: bold;
  cursor: pointer;
  text-decoration: underline;
}
</style>
