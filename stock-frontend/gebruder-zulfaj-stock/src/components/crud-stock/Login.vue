<script setup>
import { reactive } from 'vue';
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth.js";

const formData = reactive({
  email: '',
  password: ''
});

const router = useRouter();
const authStore = useAuthStore();

const handleSubmit = async () => {
  try {
    const response = await authStore.logIn(formData);
    console.log(response);
    await router.push({ name: 'home' });
  } catch (error) {
    console.error("Error during login:", error);
  }
};
</script>

<template>
  <form @submit.prevent="handleSubmit" id="container">
    <h1 id="name" style="margin-top: 100px; font-weight: bold">Gebruder Zulfaj</h1>
    <div class="wrapper">
      <div class="login-box">
        <h2>Login</h2>
        <div class="input-box">
          <input type="text" v-model.trim="formData.email" required/>
          <label>Username</label>
          <span class="icon"></span>
        </div>
        <div class="input-box">
          <input type="password" v-model.trim="formData.password" required/>
          <label>Password</label>
          <span class="icon"></span>
        </div>
        <button id="btn">Login</button>
      </div>
    </div>
  </form>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Montserrat', sans-serif;
}

#container {
  margin-top: 50px;
  height: 100vh;
}

#name {
  position: absolute;
  top: 20px;
  left: 50%;
  transform: translateX(-50%);
  font-size: 50px;
  color: green;
}

.wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 100px);
}

#btn {
  width: 100%;
  padding: 10px;
}

@keyframes animateBg {
  100% {
    filter: hue-rotate(360deg);
  }
}

.login-box {
  width: 400px;
  padding: 40px;
  background: rgba(255, 255, 255, 0.1);
  border-radius: 10px;
  border: 1px solid rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  background-color: green;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.login-box:hover {
  transform: translateY(-20px);
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.3);
}

h2 {
  font-size: 2em;
  color: #fff;
  text-align: center;
  margin-bottom: 20px;
}

.input-box {
  position: relative;
  width: 100%;
  margin: 20px 0;
  border-bottom: 1px solid #fff;
}

.input-box label {
  position: absolute;
  top: 50%;
  left: 5px;
  transform: translateY(-50%);
  font-size: 1em;
  color: #fff;
  pointer-events: none;
  transition: 0.5s;
}

.input-box input:focus ~ label,
.input-box input:valid ~ label {
  top: -5px;
}

.input-box input {
  width: 100%;
  height: 40px;
  background: transparent;
  border: none;
  outline: none;
  font-size: 1em;
  color: #fff;
  padding: 0 35px 0 5px;
}

.input-box .icon {
  position: absolute;
  right: 8px;
  top: 50%;
  color: #fff;
  transform: translateY(-50%);
}

.remember-forgot {
  margin: -10px 0 20px;
  font-size: 0.9em;
  color: #fff;
  display: flex;
  justify-content: space-between;
}

.remember-forgot label input {
  margin-right: 5px;
}

button {
  width: 100%;
  height: 40px;
  background-color: #fff;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  font-size: 1em;
  color: #000;
  font-weight: 500;
}

@media (max-width: 500px) {
  .login-box {
    width: 100%;
    border-radius: 0;
  }

  .input-box {
    width: 100%;
  }
}
</style>
