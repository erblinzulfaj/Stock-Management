<script setup>
import { ref, computed, onMounted } from 'vue';
import { useProductStore } from '@/stores/store.js';

const products = ref([]);

const filteredProducts = computed(() => {
  const today = new Date();
  const tenDaysAgo = new Date();
  tenDaysAgo.setDate(today.getDate() - 10);
  return products.value.filter(product => {
    const productDate = new Date(product.date);
    return productDate >= tenDaysAgo && productDate <= today;
  });
});

function formatDate(dateString) {
  const options = {
    year: 'numeric',
    month: 'numeric',
    day: 'numeric',
    hour: 'numeric',
    minute: 'numeric'
  };
  const date = new Date(dateString);
  return date.toLocaleString(undefined, options);
}

onMounted(async () => {
  try {
    const productStore = useProductStore();
    const productsData = await productStore.getDiscountHistory();
    products.value = productsData;
  } catch (error) {
    console.error('Error fetching products with less quantity:', error);
  }
});
</script>

<template>
  <div class="products-page">
    <h1 style="color: green">Discount History in the last 10 Days</h1>
    <div v-if="filteredProducts.length > 0" class="product-details">
      <table class="product-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Code</th>
          <th>Emri</th>
          <th>Discount</th>
          <th>Date and Hour</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="product in filteredProducts" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.code }}</td>
          <td>{{ product.emri }}</td>
          <td>{{ product.discount }}</td>
          <td>{{ formatDate(product.date) }}</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <p style="color: red">No products discount in the last 10 days.</p>
    </div>
  </div>
</template>

<style scoped>
.products-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.product-details {
  background: #f9f9f9;
  border: 1px solid #ddd;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.product-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

.product-table th,
.product-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.product-table th {
  background-color: #f2f2f2;
}
</style>
