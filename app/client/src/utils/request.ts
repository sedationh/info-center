import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
const request = axios.create({
  baseURL: '/api',
  timeout: 5000,
});

request.interceptors.request.use(
  (config) => {
    // 在请求被发送之前做些什么
    // 可以在这里添加请求头、处理请求参数等操作
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = token;
    }


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

    //如果返回的状态码是403，说明token已经过期了，需要重新登录
    if (response.code === 403) {
      //清除token
      localStorage.removeItem('token');
      //跳转到登录页面
      router.replace({
        path: '/login',
      });
    }
    return response.data;
  },
  (error) => {
    // 对响应错误做些什么
    return Promise.reject(error);
  }
);

export default request;
