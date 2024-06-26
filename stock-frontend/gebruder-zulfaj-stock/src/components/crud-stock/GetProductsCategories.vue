<template>
  <div class="container">
    <h1 style="color: green">Types of Products</h1>
    <div class="item-list">
      <div class="item" v-for="item in items" :key="item.name">
        <div class="item-content">
          <div class="item-title">{{ item.name }}</div>
          <button @click="redirectTo(item.name)" class="btn btn-primary">View</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const items = ref([
  { name: 'RAM' },
  { name: 'KRAH' },
  { name: 'HEKER' },
  { name: 'LLAJSNE' },
  { name: 'ADAPTER' },
  { name: 'MEKANIZEM' },
  { name: 'SHTOJSA' },
  { name: 'TETJERA' }
]);

const router = useRouter();

const redirectTo = (itemName) => {
  // Save the item name to localStorage
  localStorage.setItem('selectedItem', itemName);

  // Redirect to the products page
  router.push('/products');
};
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.item-list {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.item {
  background: #f9f9f9;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.item-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.item-title {
  font-size: 18px;
  font-weight: bold;
}

.btn {
  padding: 10px 20px;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  text-decoration: none;
}

.btn:hover {
  background-color: #0056b3;
}
</style>
