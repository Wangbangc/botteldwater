package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface orderMapper {
    Integer insert(order record);//添加订单
    int deleteByPrimaryKey(Integer orderId);//删除订单

  List<order> selectAll();//查询订单
    int updateByPrimaryKey(order record);//修改订单信息

    List<order> selectuserid(int id);//用户id查询
}