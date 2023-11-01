package com.example.bottledwater.service.impl;

import com.example.bottledwater.dto.OrderDTO;
import com.example.bottledwater.dto.OrderDetailDTO;
import com.example.bottledwater.entity.Order;
import com.example.bottledwater.entity.OrderDetail;
import com.example.bottledwater.mapper.OrderDetailMapper;
import com.example.bottledwater.mapper.OrderMapper;
import com.example.bottledwater.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Random;

@Service
public class OrderImpl implements OrderInterface {

    @Autowired
    private OrderMapper orderMapper; // 自动注入订单的mapper
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public boolean insert(OrderDTO orderDTO) {
        long currentTimeMillis = System.currentTimeMillis();
        int randomInt = new Random().nextInt(9999);
        String orderNumber = String.format("%013d%03d", currentTimeMillis, randomInt);
        // 创建并保存主订单
        Order newOrder = new Order();
        newOrder.setUserId(orderDTO.getUserId());
        newOrder.setTotalPrice(orderDTO.getTotalPrice());
        newOrder.setSerialNumber(orderNumber);
        if (orderMapper.insert(newOrder) <= 0) {
            return false;
        }

        // 获取新创建的订单ID
        String newOrderId = orderNumber;

        // 创建并保存订单详情
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            OrderDetail newDetail = new OrderDetail();
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
    public List<Order> selectAll() {
        // 查询所有的订单
        return orderMapper.selectAll();
    }

    @Transactional
    public boolean updateByPrimaryKey(OrderDTO orderDTO) {

        // 更新主订单表
        Order mainOrder = new Order();
        mainOrder.setUserId(orderDTO.getUserId());
        mainOrder.setTotalPrice(orderDTO.getTotalPrice());
        mainOrder.setSerialNumber(orderDTO.getSerialNumber());
        if (orderMapper.updateByPrimaryKey(mainOrder) < 0) {
            return false;
        }
        // 更新子订单表
        for (OrderDetailDTO detailDTO : orderDTO.getOrderDetails()) {
            OrderDetail newDetail = new OrderDetail();
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
    public List<Order> selectByUserId(Integer userId) {
        // 根据用户ID查询订单
        return orderMapper.selectUserId(userId);
    }
}
