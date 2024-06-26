import { defineStore } from 'pinia';
import axios from 'axios';
import client from "@/helpers/client.js";

export const useProductStore = defineStore('product', () => {


    const getAllLessQuantity = async ()=>{
        try {
            const response = await client.get(`/api-product/less-quantity`);
            return response.data;
        } catch (error) {
            console.error('Error getting products:', error);
            throw error;
        }
    };
    const getDiscountHistory = async ()=>{
        try {
            const response = await client.get(`/api-product/discount-history`);
            return response.data;
        } catch (error) {
            console.error('Error getting products:', error);
            throw error;
        }
    };

    const getIncrementHistory = async ()=>{
        try {
            const response = await client.get(`/api-product/increment-history`);
            return response.data;
        } catch (error) {
            console.error('Error getting products:', error);
            throw error;
        }
    };


    const getAllProducts = async () => {
        try {
            const response = await client.get(`/api-product`);
            return response.data;
        } catch (error) {
            console.error('Error getting products:', error);
            throw error;
        }
    };

    const incrementQuantity = async (productId, quantityToAdd) => {
        try {
            const response = await client.put(`/api-product/increment/${productId}`, quantityToAdd, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            console.log(`Quantity incremented successfully for product with ID ${productId}`);
            return response.data;
        } catch (error) {
            console.error(`Error incrementing quantity for product with ID ${productId}:`, error);
            throw error;
        }
    };

    const decrementQuantity = async (productId, quantityToSubtract) => {
        try {
            const response = await client.put(`/api-product/decrement/${productId}`, quantityToSubtract, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            console.log(`Quantity decremented successfully for product with ID ${productId}`);
            return response.data;
        } catch (error) {
            console.error(`Error decrementing quantity for product with ID ${productId}:`, error);
            throw error;
        }
    };
    const addProduct = async (newProduct) => {
        try {
            await client.post(`/api-product`, newProduct,{

                 });
        } catch (error) {
            console.error('Error adding balance:', error);
            throw error;
        }
    };

    const updateProduct = async (id, updatedProduct) => {
        try {
            await client.put(`/api-product/${id}`, updatedProduct,{
            });
        } catch (error) {
            console.error('Error updating expenses:', error);
            throw error;
        }
    };
    const getProductById = async (id) => {
        try {
            const response = await client.get(`/api-product/${id}`,{
               });

            return response.data;
        } catch (error) {
            console.error('Error getting balance by id:', error);
            throw error;
        }
    };
    const deleteProduct = async (id) => {
        try {
            await client.delete(`/api-product/${id}`,{
            });
        } catch (error) {
            console.error('Error deleting balance:', error);
            throw error;
        }
    };


    return {
        getAllProducts,
        incrementQuantity,
        decrementQuantity,
        getAllLessQuantity,
        getDiscountHistory,
        getIncrementHistory,
        addProduct,
        getProductById,
        updateProduct,
        deleteProduct
    };
});







