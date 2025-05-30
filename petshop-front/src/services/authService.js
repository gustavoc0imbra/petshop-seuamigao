import api from "./api";

const API_URL = "http://localhost:8081/api/v0/auth";

export async function login(credentials) {
    try {
        const response = await api.post(API_URL, credentials);

        return { success: true, body: { data: response.data } };
    }catch(e) {
        console.log(e)
        return { success: false, body: { message: e.response } };
    }
}

export async function auth(token) {
    try {
        const response = await api.post(`${API_URL}/validate`, null, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });

        return { success: true, body: { data: response.data } };
    }catch(e) {
        console.log(e)
        return { success: false, body: { message: e.response } };
    }
}

export function getAuthToken() {
    const token = localStorage.getItem('token');
    return token ? token : null;
}

export function getAuthUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
}