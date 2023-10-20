package com.example.bottledwater.controller;
import com.example.bottledwater.DTO.OrderDTO;
import com.example.bottledwater.entity.order;
import com.example.bottledwater.service.orderInterface;
import com.example.bottledwater.service.userInterface;
import com.example.bottledwater.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/orderManage")
public class OrderController {


    @Autowired
    private orderInterface orderService;
    @Autowired
    private userInterface userInterface;
    /**
     * 获取所有订单
     * @return List of orders
     */
    @GetMapping("/getAllOrders")
    @CrossOrigin
    public APIResponse<List<order>> getAllOrders() {
        try {
            List<order> orders = orderService.selectAll();
            return APIResponse.successResponse(orders);
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
    /**
     * 创建新订单
     * @param
     * @return API Response
     */
    @PostMapping("/createOrder")
    @CrossOrigin
    public APIResponse<String> createOrder(@RequestBody OrderDTO newOrderDTO) {
        try {

//            if (userInterface.)
            if (orderService.insert(newOrderDTO)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "Failed to create order");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    /**
     * 更新现有订单
     * @param updatedOrder The order details to be updated
     * @return API Response
     */
    @PutMapping("/updateOrder")
    @CrossOrigin
    public APIResponse<String> updateOrder(@RequestBody order updatedOrder) {
        try {
            if (orderService.updateByPrimaryKey(updatedOrder)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "Failed to update order");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
    /**
     * 根据ID删除订单
     * @param id The id of the order to be deleted
     * @return API Response
     */
    @DeleteMapping("/deleteOrder/{id}")
    @CrossOrigin
    public APIResponse<String> deleteOrder(@PathVariable Integer id) {
        try {
            if (orderService.deleteByPrimaryKey(id)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "Failed to delete order");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
    /**
     * 根据用户ID获取订单
     * @param userId The id of the user whose orders are to be fetched
     * @return List of orders
     */
    @GetMapping("/getOrdersByUserId/{userId}")
    @CrossOrigin
    public APIResponse<List<order>> getOrdersByUserId(@PathVariable Integer userId) {
        try {
            List<order> orders = orderService.selectByUserId(userId);
            return APIResponse.successResponse(orders);
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
}
