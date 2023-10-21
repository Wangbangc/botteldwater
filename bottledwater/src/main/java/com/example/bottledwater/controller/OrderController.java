package com.example.bottledwater.controller;
import com.example.bottledwater.Component.ShoppingCartManager;
import com.example.bottledwater.DTO.OrderDTO;
import com.example.bottledwater.DTO.OrderDetailDTO;
import com.example.bottledwater.entity.order;
import com.example.bottledwater.service.orderInterface;
import com.example.bottledwater.service.userInterface;
import com.example.bottledwater.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
/**
 * 创建日期：2023/10/23
 * 编写人：wangbangc
 * 功能描述：管理订单信息
 * 版本：1.0
 * 方法列表：
 * 1.创建订单：createOrder
 * 2.获取所有订单：getAllOrders
 * 3.根据id查询订单：getOrderById
 * 4.更新订单：updateOrder
 * 5.删除订单：deleteOrder
 * 6.根据用户名获取订单：getOrdersByUserId
 * 7.添加到购物车：addToCart
 * 8.从购物车中移除商品：removeFromCart（未完成）
 * 9.订单状态修改：updateOrderStatus
 * 10.清空用户的购物车：clearCart（未完成）
 * 12.获取购物车中的商品总价：getCartTotalPrice（未完成）
 *
 * **/
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
     * @param updatedOrderDTO The order details to be updated
     * @return API Response
     */
    @PutMapping("/updateOrder")
    @CrossOrigin
    public APIResponse<String> updateOrder(@RequestBody OrderDTO updatedOrderDTO) {
        try {
            if (orderService.updateByPrimaryKey(updatedOrderDTO)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "更新信息错误");
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
                return APIResponse.errorResponse(2, "删除订单异常");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
    /**
     * 根据用户名来获取获取订单
     * @param username The id of the user whose orders are to be fetched
     * @return List of orders
     */
    @GetMapping("/getOrdersByUserId/{username}")
    @CrossOrigin
    public APIResponse<List<order>> getOrdersByUserId(@PathVariable String username) {
        try {
          System.out.println(userInterface.selectUser(username).get(0));
            Integer userId=userInterface.selectUser(username).get(0).getId();
            List<order> orders = orderService.selectByUserId(userId);
            return APIResponse.successResponse(orders);
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    //修改订单状态
    @PutMapping("/updateOrderStatus")
    public APIResponse<String> updateOrderStatus(@RequestBody order updatedOrder) {
        try {
            if (orderService.updateByPrimaryKey1(updatedOrder)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "更新订单状态异常");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
}
