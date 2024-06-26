import {createRouter, createWebHistory} from  "vue-router"
import Login from "@/components/crud-stock/Login.vue";
import HomeView from "@/views/HomeView.vue";
import AddProduct from "@/components/crud-stock/AddProduct.vue";
import GetProductsCategories from "@/components/crud-stock/GetProductsCategories.vue";
import App from "@/App.vue";
import GetProductsByCategory from "@/components/crud-stock/GetProductsByCategory.vue";
import LessQuantity from "@/components/crud-stock/LessQuantity.vue";
import DecrementHistory from "@/components/crud-stock/Decrement-History.vue";
import IncrementHistory from "@/components/crud-stock/Increment-History.vue";
import ModifyProduct from "@/components/crud-stock/ModifyProduct.vue";
import FileReader from "@/components/crud-stock/FileReader.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name:'login',
            component: Login
        },
        {
            path: '/home',
            name:'home',
            component: GetProductsCategories
        },
        {
            path: '/products',
            name:'Products',
            component: GetProductsByCategory
        },
        {
            path: '/less-quantity',
            name: 'less-quantity',
            component: LessQuantity
        },
        {
            path: '/add',
            name:'add-product',
            component: AddProduct
        },
        {
            path: '/discount-history',
            name:'discount',
            component: DecrementHistory
        },
        {
            path: '/increment-history',
            name:'increment-history',
            component: IncrementHistory
        },
        {
            path: '/update/:id',
            name:'update',
            component: ModifyProduct
        },
        {
            path: '/file-reader',
            name:'file-reader',
            component: FileReader
        },

    ]
})

export default router;
