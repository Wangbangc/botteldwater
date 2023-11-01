import axios from "./axios";

//获取所有配送员信息
const deliveryselAll = async () => {
    const a = await axios({
        method: 'get',
        url: '/delivery/selectAll',
        data: ''
    });
    return a;
};
//添加配送员
const deliveryadd = async (body) => {
    const a = await axios({
        method: 'post',
        url: '/delivery/insert',
        data: body
    });
    return a;
};
//删除配送员
const deliverydelete = async (id) => {
    const a = await axios({
        method: 'delete',
        url: '/delivery/delete?id=' + id,
    });
    return a;
};
//更新配送员信息
const deliveryupdate = async (body) => {
    const a = await axios({
        method: 'put',
        url: '/delivery/update',
        data: body
    });
    return a;
};
//模糊查询配送员
const deliveryselect = async (name) => {
    const a = await axios({
        method: 'get',
        url: '/delivery/selectLike?like=' + name,
    });
    return a;
};
export{
    deliveryselAll,deliveryadd,deliverydelete,deliveryupdate,deliveryselect
}