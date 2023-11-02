package com.example.bottledwater.controller;
import com.example.bottledwater.component.ShoppingCartManager;
import com.example.bottledwater.dto.OrderDTO;
import com.example.bottledwater.dto.OrderDetailDTO;
import com.example.bottledwater.entity.Order;
import com.example.bottledwater.service.OrderInterface;
import com.example.bottledwater.service.UserInterface;
import com.example.bottledwater.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
 * 9.订单状态修改：updateOrderStatus（已完成）
 * 10.清空用户的购物车：clearCart（未完成）
 * 12.获取购物车中的商品总价：getCartTotalPrice（未完成）
 * 13.获取配送员订单关系表：getOrderDeliveryman（未完成）
 * 14.添加配送员订单关系表：addOrderDeliveryman（未完成）
 *
 * **/
@RestController
@RequestMapping("/orderManage")
public class OrderController {


    @Autowired
    private OrderInterface orderService;
    @Autowired
    private UserInterface userInterface;
    /**
     * 获取所有订单
     * @return List of orders
     */
    @GetMapping("/getAllOrders")
    @CrossOrigin
    public APIResponse<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderService.selectAll();
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
    public APIResponse<List<Order>> getOrdersByUserId(@PathVariable String username) {
        try {

            Integer userId=userInterface.selectUser(username).get(0).getId();
            List<Order> orders = orderService.selectByUserId(userId);
            return APIResponse.successResponse(orders);
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @PutMapping("/updateOrderStatus")
    @CrossOrigin
    public APIResponse<String> updateOrderStatus(@RequestBody Order order) {
        try {
            if (orderService.orderStatus(order)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "更新订单状态异常");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

//    用户方法编写
@PostMapping("/addToCart")
@CrossOrigin
public APIResponse<String> addToCart(@RequestBody OrderDetailDTO orderDetailDTO, @RequestHeader("Authorization") String authorizationHeader) {
    try {
      int userId=4;

        // 创建 ShoppingCartManager 的实例
        ShoppingCartManager cartManager = new ShoppingCartManager();

        // 调用购物车管理类的添加到购物车方法
        cartManager.addToCart(userId, orderDetailDTO);

        return APIResponse.successResponse1();
    } catch (Exception e) {
        return APIResponse.errorResponse(1, e.getMessage());
    }
}
}
