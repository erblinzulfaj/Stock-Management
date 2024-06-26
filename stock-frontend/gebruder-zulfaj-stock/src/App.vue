<template>
  <div id="app">
    <Navbar v-if="isLoggedIn" />
    <div class="main-content container">
      <router-view />
    </div>
  </div>
</template>

<script>
import Navbar from './components/layout/Header.vue';
import { useAuthStore } from "@/stores/auth.js";
import {computed} from "vue";

export default {
  name: 'App',
  components: {
    Navbar
  },
  setup() {
    const authStore = useAuthStore();
    const isLoggedIn = computed(() => authStore.isLoggedIn);

    return {
      isLoggedIn
    };
  }
}
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.main-content {
  flex: 1;
  padding: 20px;
}
</style>
