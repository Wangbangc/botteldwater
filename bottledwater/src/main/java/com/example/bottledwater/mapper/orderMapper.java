package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.order;

public interface orderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    int insert(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    int insertSelective(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    order selectByPrimaryKey(Integer orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_master
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(order record);
}