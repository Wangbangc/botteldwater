import axios from "./axios";


//更新管理员信息
const adminUpdate = async (body) => {
    const { data } = await axios({
        method: 'POST',
        url: '/admin/update',
        data: body
    })
    return data
}

//获取管理员信息
const adminselect = async (body)=>{
    const {data} = await axios({
        method: 'GET',
        url: '/admin/selectUsername',
        data:body
    })
    return data
}

//注册管理员
const adminRegister = async (body) => {
    const { data } = await axios({
        method: 'POST',
        url: '/admin/register',
        data: body
    })

    localStorage.setItem('token',data.data.token)
    return data
}

//登入管理员
let fetchLogin;
fetchLogin = async (body) => {
    let data;
    ({data} = await axios({
        method: 'POST',
        url: '/admin/login',
        data: body
    }))
   
    localStorage.setItem('token',data.data.token)

    return data
};

export  {
    fetchLogin,
    adminRegister,
    adminselect,
    adminUpdate
}