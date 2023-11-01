import axios from "./axios";


const userselectAll =async ()=> {
    const a = await axios({
        method:'get',
        url:'/userManage/selectAll',
        data:''
    });
        return a;
    
} ;
//id查询用户
const usersel =async (id:number)=> {
    const a = await axios({
        method:'get',
        url:'/userManage/selectIdUserwater?id='+id,
    });
        return a;
};
//修改用户信息
const userupdate =async (body)=> {
    const a = await axios({
        method:'post',
        url:'/userManage/update',
        data:body
    });
        return a;
};

//删除用户
const userdelete =async (id:number)=> {
    const a = await axios({
        method:'delete',
        url:'/userManage/delUser?id='+id,
    });
        return a;
};
//新增用户方法
const useradd =async (body)=> {
    const useradd = await axios({
        method:'post',
        url:'/userManage/insert',
        data:body
    });
        return useradd;
}
//用户模糊查询
const userselect =async (username)=> {
    const userselect = await axios({
        method:'get',
        url:'/userManage/selUser?sel='+username,
     
    });
        return userselect;
}
export {
userselectAll,
usersel,
userupdate,
userdelete,
useradd,
userselect 
};