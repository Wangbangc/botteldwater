import axios from "./axios";


const userselectAll =async ()=> {
    const a = await axios({
        method:'get',
        url:'/userManage/selectAll',
        data:''
    });
        return a;
    
} ;
export {
userselectAll
};