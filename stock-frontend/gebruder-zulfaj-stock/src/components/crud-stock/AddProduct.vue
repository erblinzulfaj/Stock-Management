<template>
  <div class="add-product-page">
    <h2>Add New Product</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="type">Type</label>
        <select v-model="product.type" id="type" required>
          <option v-for="option in typeOptions" :key="option" :value="option">{{ option }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="origin">Origin</label>
        <input v-model="product.origin" type="text" id="origin" required />
      </div>
      <div class="form-group">
        <label for="name">Name</label>
        <input v-model="product.name" type="text" id="name" required />
      </div>
      <div class="form-group">
        <label for="code">Code</label>
        <input v-model="product.code" type="text" id="code" required />
      </div>
      <div class="form-group">
        <label for="emri">Emri</label>
        <input v-model="product.emri" type="text" id="emri" required />
      </div>
      <div class="form-group">
        <label for="quantity">Quantity</label>
        <input v-model="product.quantity" type="number" id="quantity" required />
      </div>
      <button type="submit" class="btn btn-primary">Add Product</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useProductStore } from "@/stores/store.js";

const product = ref({
  id: null,
  type: '',
  origin: '',
  name: '',
  code: '',
  emri: '',
  quantity: null,
  localDateTime: ''
});

const typeOptions = ['RAM', 'KRAH', 'HEKER','LLAJSNE', 'ADAPTER', 'MEKANIZEM', 'SHTOJSA','TETJERA']; // Replace with your actual type options

const productStore = useProductStore(); // Initialize the store
const router = useRouter(); // Initialize the router

const submitForm = async () => {
  try {
    await productStore.addProduct(product.value); // Call the addProduct method from the store
    console.log('Product added successfully');
    router.go(-1); // Redirect back
  } catch (error) {
    console.error('Error adding product:', error);
  }
};
</script>

<style scoped>
.add-product-page {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input, select {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background-color: #0056b3;
}

.btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.btn-primary:hover {
  background-color: #0056b3;
  border-color: #0056b3;
}
</style>
