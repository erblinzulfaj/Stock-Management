import client from '@/helpers/client.js';

class ProductService {
    async checkEntity(data) {
        try {
            const response = await client.post('/check-admin', data);
            return response.data;
        } catch (error) {
            throw new Error('Error checking entity: ' + error.message);
        }
    }
}

export default new ProductService();