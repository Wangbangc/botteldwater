import axios from "./axios";
const adminRegister = async (body) => {
    const { data } = await axios({
        method: 'POST',
        url: '/admin/register',
        data: body
    })
    return data
}
let fetchLogin;
fetchLogin = async (body) => {
    let data;
    ({data} = await axios({
        method: 'POST',
        url: '/admin/login',
        data: body
    }));

    // http.defaults.headers.common['Authorization'] = data.data?.accessToken
    return data
};

export  {
    fetchLogin,
    adminRegister
}