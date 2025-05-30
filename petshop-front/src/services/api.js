import axios from "axios";

const api = axios.create();

api.interceptors.response.use((response) => {return response},
    (error) => {
        console.log(error)
    }
)

export default api;