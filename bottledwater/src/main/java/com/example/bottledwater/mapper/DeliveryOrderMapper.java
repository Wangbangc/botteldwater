package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.DeliveryOrder;

public interface DeliveryOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int insert(DeliveryOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int insertSelective(DeliveryOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    DeliveryOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DeliveryOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DeliveryOrder record);
}
