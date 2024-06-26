import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {'Content-Type': 'application/json'}
});


// Add a request interceptor
instance.interceptors.request.use(function (config) {
    // Do something before request is sent

    const token = localStorage.getItem("token");
    config.headers.Authorization = token ? `Bearer ${token}` : null;
    // console.log(config.headers.Authorization)


    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});
export default instance;
