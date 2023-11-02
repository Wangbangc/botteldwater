package com.example.bottledwater.service;

import com.example.bottledwater.dto.OrderDTO;
import com.example.bottledwater.dto.OrderDeliveryDTO;
import com.example.bottledwater.entity.DeliveryAssignment;
import com.example.bottledwater.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface OrderInterface {
    DeliveryAssignment selectByDeliveryPersonNameAndSerialNumber(@Param("serialNumber") String serialNumber);
    List<OrderDeliveryDTO> selectOrderDeliveryAll(); //查询所有配送员订单关系表
    boolean orderStatus(Order order);//修改订单状态
    // 插入新的订单记录
    boolean insert(OrderDTO orderDTO);
    boolean insertDeliveryAssignment(String serialNumber,String deliveryPersonName);    //插入订单合配送员关系表

    // 根据主键删除订单
    boolean deleteByPrimaryKey(Integer orderId);

    // 查询所有的订单
    List<Order> selectAll();

    // 根据主键更新订单信息
    boolean updateByPrimaryKey(OrderDTO orderDTO);



    // 根据用户ID查询订单
    List<Order> selectByUserId(Integer userId);
}
