package com.example.bottledwater.mapper;

import com.example.bottledwater.dto.OrderDeliveryDTO;
import com.example.bottledwater.entity.DeliveryAssignment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliveryAssignmentMapper {
 Integer insertDeliveryAssignmentWithNames(@Param("serialNumber") String serialNumber, @Param("deliveryPersonName") String deliveryPersonName);

    //查询所有

   //查询所有
    List<OrderDeliveryDTO> selectAll();
    //通过订单编号查询关系表
      DeliveryAssignment selectByDeliveryPersonNameAndSerialNumber(@Param("serialNumber") String serialNumber);

    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int insertSelective(DeliveryAssignment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    DeliveryAssignment selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DeliveryAssignment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_assignment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DeliveryAssignment record);
}