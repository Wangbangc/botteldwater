package com.example.bottledwater.mapper;

import com.example.bottledwater.entity.orderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface orderDetailMapper {
    Integer insert(orderDetail record);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer detailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */
    int insertSelective(orderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */
    orderDetail selectByPrimaryKey(Integer detailId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(orderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(orderDetail record);
}