import Axios from './axios';
//获取所有订单信息
const orderAll = async () => {
    const a = await Axios({
        method: 'get',
        url: '/orderManage/getAllOrders',
        data: ''
    });
    return a;
};
//修改订单状态
const orderUpdate = async (body) => {

    const a = await Axios({
        method: 'put',
        url: '/orderManage/updateOrderStatus',
        data: body
    });
    return a;
};
//删除订单
const orderDelete = async (id) => {
    const a = await Axios({
        method: 'delete',
        url: '/orderManage/deleteOrder/ ' + id,
    });
    return a;
};
//模糊查询订单
const orderSelect = async (name) => {
    const a = await Axios({
        method: 'get',
        url: '/orderManage/getOrdersByUserId/' + name,
    });
    return a;
};
export{
    orderAll,orderUpdate,orderDelete,orderSelect
}
