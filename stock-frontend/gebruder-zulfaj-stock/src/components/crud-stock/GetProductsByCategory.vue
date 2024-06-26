<template>
  <div class="products-page">
    <h1 style="color: green">Choosen Product: {{ selectedItem }}</h1>
    <div v-if="selectedItem" class="product-details">
      <table v-if="filteredProducts.length" class="product-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Type</th>
          <th>Origin</th>
          <th>Name</th>
          <th>Code</th>
          <th>Emri</th>
          <th>Quantity</th>
          <th>Change Quantity</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="product in filteredProducts" :key="product.id">
          <td>{{ product.id }}</td>
          <td>{{ product.type }}</td>
          <td>{{ product.origin }}</td>
          <td>{{ product.name }}</td>
          <td>{{ product.code }}</td>
          <td>{{ product.emri }}</td>
          <td>{{ product.quantity }} metra</td>
          <td>
            <!-- Input and buttons for updating quantity -->
            <input type="number" v-model="product.newQuantity" placeholder="New Quantity" class="form-control d-inline-block" style="width: auto; margin-right: 10px">
            <button @click="incrementQuantity(product.id, product.newQuantity)" class="btn btn-success me-2">+</button>
            <button @click="decrementQuantity(product.id, product.newQuantity)" class="btn btn-danger">-</button>
          </td>
          <td>
            <button @click="editProduct(product.id)" class="btn btn-primary me-2">Edit</button>
            <button @click="confirmDeleteProduct(product.id)" class="btn btn-danger">Delete</button>
          </td>
        </tr>
        </tbody>
      </table>
      <p style="color: red" v-else>No products found for the selected type.</p>
    </div>
    <div v-else>
      <p style="color: red">No item selected.</p>
    </div>
  </div>
</template>

<script setup>
import { useProductStore } from "@/stores/store.js";
import { useRouter } from 'vue-router';
import Swal from "sweetalert2";
import { ref, onMounted } from "vue";

const selectedItem = ref(localStorage.getItem('selectedItem'));
const filteredProducts = ref([]);
const productStore = useProductStore();
const router = useRouter();

onMounted(async () => {
  if (selectedItem.value) {
    try {
      const products = await productStore.getAllProducts();
      filteredProducts.value = products
          .filter(product => product.type === selectedItem.value)
          .map(product => ({
            ...product,
            newQuantity: null
          }));
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  }
});

const incrementQuantity = async (productId, newQuantity) => {
  try {
    const quantityToAdd = parseFloat(newQuantity);
    if (!isNaN(quantityToAdd) && quantityToAdd > 0) {
      await productStore.incrementQuantity(productId, quantityToAdd);
      console.log(`Quantity updated successfully for product with ID ${productId}`);
      window.location.reload();
    } else {
      console.error('Invalid quantity input');
    }
  } catch (error) {
    console.error('Error updating quantity:', error);
  }
};

const decrementQuantity = async (productId, newQuantity) => {
  try {
    const quantityToAdd = parseFloat(newQuantity);
    if (!isNaN(quantityToAdd) && quantityToAdd > 0) {
      await productStore.decrementQuantity(productId, quantityToAdd);
      console.log(`Quantity updated successfully for product with ID ${productId}`);
      window.location.reload();
    } else {
      console.error('Invalid quantity input');
    }
  } catch (error) {
    console.error('Error updating quantity:', error);
  }
};

const editProduct = (productId) => {
  router.push(`/update/${productId}`);
};

const confirmDeleteProduct = async (productId) => {
  const result = await Swal.fire({
    title: 'Are you sure?',
    text: "You won't be able to revert this!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes, delete it!'
  });

  if (result.isConfirmed) {
    await deleteProduct(productId);
  }
};

const deleteProduct = async (productId) => {
  try {
    await productStore.deleteProduct(productId);
    console.log(`Product with ID ${productId} deleted successfully`);
    filteredProducts.value = filteredProducts.value.filter(product => product.id !== productId);
    Swal.fire(
        'Deleted!',
        'Your product has been deleted.',
        'success'
    );
  } catch (error) {
    console.error('Error deleting product:', error);
  }
};
</script>

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

button {
  padding: 5px 10px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #ddd;
}
</style>
