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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
//修改订单状态
    @Override
    public boolean  updateByPrimaryKey1(order record) {
        if (orderMapper.updateByPrimaryKey1(record) > 0) {
            return true;
        }return false;
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

    @Transactional
    public boolean updateByPrimaryKey(OrderDTO orderDTO) {

        // 更新主订单表
        order mainOrder = new order();
        mainOrder.setUserId(orderDTO.getUserId());
        mainOrder.setTotalPrice(orderDTO.getTotalPrice());
        mainOrder.setSerialNumber(orderDTO.getSerialNumber());
        if (orderMapper.updateByPrimaryKey(mainOrder) < 0) {
            return false;
        }
        // 更新子订单表
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            orderDetail newDetail = new orderDetail();
            newDetail.setOrderId(orderDTO.getSerialNumber());  // 使用主订单的ID
            newDetail.setBottledWaterCategory(detailDTO.getBottledWaterCategory());
            newDetail.setQuantity(detailDTO.getQuantity());
            newDetail.setUnitPrice(detailDTO.getUnitPrice());

            if (orderDetailMapper.updateByPrimaryKey(newDetail) <= 0) {
                // 这里应该进行适当的错误处理，比如回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return false;
            }
        }
        return true;
    }



    @Override
    public List<order> selectByUserId(Integer userId) {
        // 根据用户ID查询订单
        return orderMapper.selectuserid(userId);
    }
}
