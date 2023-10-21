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
