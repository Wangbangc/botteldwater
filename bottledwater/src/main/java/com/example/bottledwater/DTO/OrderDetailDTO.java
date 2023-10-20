package com.example.bottledwater.DTO;

import java.math.BigDecimal;

public class OrderDetailDTO {
    private String bottledWaterCategory;
    private int quantity;
    private BigDecimal unitPrice;

    public String getBottledWaterCategory() {
        return bottledWaterCategory;
    }

    public void setBottledWaterCategory(String bottledWaterCategory) {
        this.bottledWaterCategory = bottledWaterCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
