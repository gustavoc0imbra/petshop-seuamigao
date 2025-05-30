import api from "./api";

const API_URL = "http://localhost:8080/api/v0/products";

export async function getProducts() {
    try {
        const response = await api.get(API_URL);

        return { success: true, body: { data: response.data } };
    }catch(e) {
        console.log(e)
        return { success: false, body: { message: e.response } };
    }
}

export async function getProductById(id) {
    try {
        const response = await api.get(`${API_URL}/${id}`);

        return { success: true, body: { data: response.data } };
    }catch(e) {
        console.log(e)
        return { success: false, body: { message: e.response } };
    }
}