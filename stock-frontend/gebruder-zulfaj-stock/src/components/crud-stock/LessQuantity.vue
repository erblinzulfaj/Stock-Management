<script setup>
import { ref, onMounted } from 'vue';
import { useProductStore } from "@/stores/store.js";

const products = ref([]);

onMounted(async () => {
  try {
    const productStore = useProductStore();
    products.value = await productStore.getAllLessQuantity();
  } catch (error) {
    console.error('Error fetching products with less quantity:', error);
  }
});
</script>

<template>
  <div class="products-page">
    <h1 style="color: green">Products with Less Quantity</h1>
    <div v-if="products.length > 0" class="product-details">
      <table class="product-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Type</th>
          <th>Origin</th>
          <th>Name</th>
          <th>Code</th>
          <th>Emri</th>
          <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="product in products" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.type }}</td>
          <td>{{ product.origin }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.code }}</td>
          <td>{{ product.emri }}</td>
          <td>{{ product.quantity }} metra</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div v-else>
      <p style="color: red">No product with less quantity</p>
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
