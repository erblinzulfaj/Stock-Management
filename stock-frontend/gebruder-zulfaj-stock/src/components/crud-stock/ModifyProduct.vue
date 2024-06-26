<script setup>
import { ref, onMounted } from 'vue';
import { useRouter, useRoute } from 'vue-router';
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

const typeOptions = ['RAM', 'KRAH', 'HEKER','LLAJSNE', 'ADAPTER', 'MEKANIZEM', 'SHTOJSA','TETJERA'];

const productStore = useProductStore();
const router = useRouter();
const route = useRoute();

const fetchProduct = async () => {
  const id = route.params.id;
  try {
    const data = await productStore.getProductById(id); // Fetch the product by ID
    product.value = data;
  } catch (error) {
    console.error('Error fetching product:', error);
  }
};

onMounted(fetchProduct);

const submitForm = async () => {
  const id = route.params.id;
  try {
    await productStore.updateProduct(id, product.value); // Call the updateProduct method from the store
    console.log('Product updated successfully');
    router.go(-1); // Redirect back
  } catch (error) {
    console.error('Error updating product:', error);
  }
};
</script>

<template>
  <div class="edit-product-page">
    <h2 style="color: green">Edit Product</h2>
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
      <button type="submit" class="btn btn-primary">Update Product</button>
    </form>
  </div>
</template>

<style scoped>
.edit-product-page {
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
