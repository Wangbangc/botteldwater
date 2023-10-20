package com.example.bottledwater.DTO;
import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private int userId;
    private BigDecimal totalPrice;
    private List<OrderDetailDTO> orderDetails;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
