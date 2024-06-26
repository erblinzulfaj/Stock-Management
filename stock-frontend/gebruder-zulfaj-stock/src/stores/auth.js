import { defineStore } from "pinia";
import client from "@/helpers/client.js";
import { computed, ref } from "vue";

export const useAuthStore = defineStore('auth', () => {
    const token = ref(localStorage.getItem('token') || null);

    async function logIn(user) {
        const response = await client.post("/api/auth/authenticate", user);
        try {
            if (response.data) {
                localStorage.setItem('token', response.data.token);
                token.value = response.data.token;
            }
            return response.data;
        } catch (e) {
            throw e;
        }
    }

    function logOut() {
        if (isLoggedIn.value) {
            localStorage.removeItem('token');
            token.value = null;
        }
    }

    const isLoggedIn = computed(() => {
        return !!token.value;
    });

    return { logIn, logOut, isLoggedIn, token };
});
