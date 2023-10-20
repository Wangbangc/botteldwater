package com.example.bottledwater.service;

import com.example.bottledwater.DTO.OrderDTO;
import com.example.bottledwater.entity.order;
import org.springframework.stereotype.Service;

import java.util.List;


public interface orderInterface {

    // 插入新的订单记录
    boolean insert(OrderDTO orderDTO);

    // 根据主键删除订单
    boolean deleteByPrimaryKey(Integer orderId);

    // 查询所有的订单
    List<order> selectAll();

    // 根据主键更新订单信息
    boolean updateByPrimaryKey(order record);



    // 根据用户ID查询订单
    List<order> selectByUserId(Integer userId);
}
