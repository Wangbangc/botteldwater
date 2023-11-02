import axios from "./axios";
//do=deliveryOrder
//新增配送员订单关系表
const doAdd = async (body) => {
    const a = await axios({
        method: 'post',
        url: '/DeliveryOrderManage/addDeliveryOrder',
        data: body
    });
    return a;
};
//查询所有配送员订单关系表
const doSelectAll = async () => {
    
    const a = await axios({
        method: 'get',
        url: '/DeliveryOrderManage/getDeliveryOrder',
        data: ''
    });
    return a;
};
export{doAdd,doSelectAll  }