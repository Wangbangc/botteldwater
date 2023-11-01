package com.example.bottledwater.service;

import com.example.bottledwater.dto.OrderDTO;
import com.example.bottledwater.entity.Order;

import java.util.List;


public interface OrderInterface {

    // 插入新的订单记录
    boolean insert(OrderDTO orderDTO);

    // 根据主键删除订单
    boolean deleteByPrimaryKey(Integer orderId);

    // 查询所有的订单
    List<Order> selectAll();

    // 根据主键更新订单信息
    boolean updateByPrimaryKey(OrderDTO orderDTO);


    // 根据用户ID查询订单
    List<Order> selectByUserId(Integer userId);
}
