package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer insert(Order record); //添加订单

    int deleteByPrimaryKey(Integer orderId); //删除订单

    List<Order> selectAll(); //查询订单

    int updateByPrimaryKey(Order record); //修改订单信息

    //修改订单状态
    int updateByPrimaryKey1(Order record);

    List<Order> selectUserId(int id); //用户id查询
}
