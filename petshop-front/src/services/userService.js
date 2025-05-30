import api from "./api";
import { login } from "./authService";

const API_URL = "http://localhost:8081/api/v0/users";

export async function getUser(token, userId) {
    try {
        const result = await api.get(`${API_URL}/${userId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        return { success: true, body: { data: result.data } }
    }catch(e) {
        return { success: false, body: { message: e.response } }
    }
}

export async function saveUser(credentials) {
    try {
        let result = await api.post(API_URL, credentials);

        if (result.data.id) {
            result = await login({ email: credentials.email, password: credentials.password });
        }
        
        console.log(result);

        return { success: true, body: { data: result.body.data } }
    }catch(e) {
        return { success: false, body: { message: e.response } }
    }
}