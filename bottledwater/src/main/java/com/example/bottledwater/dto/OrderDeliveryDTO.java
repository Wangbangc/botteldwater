package com.example.bottledwater.dto;

import lombok.Getter;

@Getter
public class OrderDeliveryDTO{
    private Integer deliveryAssignmentId;
    private String userName;
    private String deliveryPersonName;
    private String orderStatus;
    private String serialNumber;


    public void setDeliveryAssignmentId(Integer deliveryAssignmentId) {
        this.deliveryAssignmentId = deliveryAssignmentId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        this.deliveryPersonName = deliveryPersonName;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
