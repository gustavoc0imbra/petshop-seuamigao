import api from "./api";

const API_URL = "http://localhost:8082/api/v0/orders";

export async function getOrders(token, userId) {
    try {
        const response = await api.get(`${API_URL}/${userId}/orders`);

        return { success: true, body: { data: response.data } };
    }catch(e) {
        console.log(e)
        return { success: false, body: { message: e.response } };
    }
}