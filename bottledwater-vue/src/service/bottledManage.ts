import axios from "./axios";
//查询所有商品
const bottledselectAll = async () => {
    const a = await axios({
        method: 'get',
        url: '/bottledWaterManage/getAllProducts',
        data: ''
    });
    return a;
};




//添加商品
const bottledadd = async (body) => {
    const formData = new FormData();
formData.append('imageData', body.imageData);
formData.append('brand', body.brand);
formData.append('categoryName', body.categoryName);
formData.append('price', body.price);
formData.append('description', body.description);
    const response = await axios.post('/bottledWaterManage/createProduct', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
    return  response ;
};
//删除商品
const bottleddelete = async (id) => {
    const a = await axios({
        method: 'delete',
        url: '/bottledManage/delBottled?id=' + id,
    });
    return a;
};
//修改商品
const bottledupdate = async (body) => {
    const formData = new FormData();
    formData.append('id', body.id);
    formData.append('imageData', body.imageData);
    formData.append('brand', body.brand);
    formData.append('categoryName', body.categoryName);
    formData.append('price', body.price);
    formData.append('description', body.description);
    const response = await axios.put('/bottledWaterManage/updateProduct', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
    return  response ;
};

//模糊查询商品
const bottledselect = async (name) => {
    const a = await axios({
        method: 'get',
        url: '/bottledWaterManage/selProduct?sel=' +name,
    });
    return a;
};

//查看商品详情
const bottledselectId = async (id) => {
    // const a = await axios({
    //     method: 'get',
    //     url: '/bottledManage/selectIdBottled?id=' + id,
    // });
    // return a;
};
export { bottledselectAll, bottledadd, bottleddelete, bottledupdate, bottledselect, bottledselectId
};