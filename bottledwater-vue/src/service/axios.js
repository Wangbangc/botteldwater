
import Axios from 'axios'

const initAxios = Axios.create({
    timeout: 1800000 ,//数据响应过期时间
    baseURL: 'http://localhost:8085',
//    headers:{
//     Authorization: "Bearer localStorage.getItem('token')"
//    }
})

//请求拦截器
initAxios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
          config.headers.Authorization = token;
        }
        return config;
        
    },
    (error) => {
        //对请求错误做点什么
        return error
    }
)

// //响应拦截器
// initAxios.interceptors.response.use(
//     (response) => {
//         if (response.code === 401) {

//             // location.href = '/login'
//             window.location.href = 'login'
//         }
//         return response
//     },
//     (error) => {
//         console.log(error, 'error')
//         if (error.response.status === 401) {

//             window.location.href = 'login'
//         }
//         return Promise.reject(error)
//     }
// )

export default initAxios
