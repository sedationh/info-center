import axios from 'axios';

const request = axios.create({
  baseURL: '/api',
  timeout: 5000,
});

request.interceptors.request.use(
  (config) => {
    // 在请求被发送之前做些什么
    // 可以在这里添加请求头、处理请求参数等操作
    return config;
  },
  (error) => {
    // 对请求错误做些什么
    return Promise.reject(error);
  }
);

request.interceptors.response.use(
  (response) => {
    // 对响应数据做些什么
    return response.data;
  },
  (error) => {
    // 对响应错误做些什么
    return Promise.reject(error);
  }
);

export default request;
