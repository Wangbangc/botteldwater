package com.example.bottledwater.service.Impl;

import com.example.bottledwater.DTO.OrderDTO;
import com.example.bottledwater.DTO.OrderDetailDTO;
import com.example.bottledwater.entity.order;
import com.example.bottledwater.entity.orderDetail;
import com.example.bottledwater.mapper.orderDetailMapper;
import com.example.bottledwater.mapper.orderMapper;
import com.example.bottledwater.service.orderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class orderImpl implements orderInterface {

    @Autowired
    private orderMapper orderMapper; // 自动注入订单的mapper
    @Autowired
    private orderDetailMapper orderDetailMapper;

    @Override
    public boolean insert(OrderDTO orderDTO) {
        long currentTimeMillis = System.currentTimeMillis();
        int randomInt = new Random().nextInt(9999);
        String orderNumber = String.format("%013d%03d", currentTimeMillis, randomInt);
        // 创建并保存主订单
        order newOrder = new order();
        newOrder.setUserId(orderDTO.getUserId());
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setSerialNumber(orderNumber);
        if (orderMapper.insert(newOrder) <= 0) {
            return false;
        }

        // 获取新创建的订单ID
        String newOrderId= orderNumber;

        // 创建并保存订单详情
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            orderDetail newDetail = new orderDetail();
            newDetail.setOrderId(newOrderId);
            newDetail.setBottledWaterCategory(detailDTO.getBottledWaterCategory());
            newDetail.setQuantity(detailDTO.getQuantity());
            newDetail.setUnitPrice(detailDTO.getUnitPrice());

            if (orderDetailMapper.insert(newDetail) <= 0) {
                // 这里应该进行适当的错误处理，比如回滚事务
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean deleteByPrimaryKey(Integer orderId) {
        // 根据主键删除订单并返回是否成功
        if (orderMapper.deleteByPrimaryKey(orderId) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<order> selectAll() {
        // 查询所有的订单
        return orderMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKey(order record) {
        // 根据主键更新订单信息并返回是否成功
        if (orderMapper.updateByPrimaryKey(record) > 0) {
            return true;
        }
        return false;
    }



    @Override
    public List<order> selectByUserId(Integer userId) {
        // 根据用户ID查询订单
        return orderMapper.selectuserid(userId);
    }
}
