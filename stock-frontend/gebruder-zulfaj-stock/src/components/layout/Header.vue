<template>
  <nav class="navbar navbar-expand-lg navbar-light custom-navbar">
    <div class="container-fluid">
      <router-link to="/home" class="navbar-brand">Gebruder Zulfaj</router-link>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <router-link to="/home" class="nav-link">Products</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/add" class="nav-link">Add Product</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/less-quantity" class="nav-link">Less Quantity</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/discount-history" class="nav-link">Discount History</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/increment-history" class="nav-link">Increment History</router-link>
          </li>
          <li class="nav-item" v-if="isLoggedIn">
            <button @click="onLogOut" class="btn btn-danger nav-link">Logout</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

export default {
  name: 'Navbar',
  setup() {
    const authStore = useAuthStore();
    const router = useRouter();

    function onLogOut() {
      authStore.logOut();
      router.push({ name: 'login' });
    }

    return {
      onLogOut,
      isLoggedIn: authStore.isLoggedIn
    };
  }
};
</script>

<style scoped>
.custom-navbar {
  width: 100%;
  background-color: white;
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
}

.navbar-brand {
  font-size: 24px;
  font-weight: bold;
  color: #91C11E;
}

.navbar-nav .nav-link {
  font-size: 16px;
  color: #666;
  margin-right: 20px;
}

.navbar-nav .nav-link:hover {
  color: #91C11E;
}

.navbar-nav .nav-item.active .nav-link {
  color: #91C11E;
}
</style>
