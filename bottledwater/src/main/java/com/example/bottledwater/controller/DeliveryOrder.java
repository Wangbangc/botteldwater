package com.example.bottledwater.controller;

import com.example.bottledwater.dto.OrderDeliveryDTO;
import com.example.bottledwater.entity.DeliveryAssignment;
import com.example.bottledwater.service.DeliveryInterface;
import com.example.bottledwater.service.OrderInterface;
import com.example.bottledwater.service.UserInterface;
import com.example.bottledwater.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/DeliveryOrderManage")
public class DeliveryOrder {
    @Autowired
    private OrderInterface orderService;
    @Autowired
    private UserInterface userInterface;
    @Autowired
   private DeliveryInterface deliveryInterface;
    // 14.添加配送员订单关系表：addOrderDelivery（未完成）
    @PostMapping("/addDeliveryOrder")
    @CrossOrigin
    public APIResponse<String> addOrderDeliveryman(@RequestBody Map<String,Object> map) {
        String serialNumber = (String) map.get("serialNumber");
        String deliveryPersonName = (String) map.get("deliveryPersonName");
        try {
            if (orderService.insertDeliveryAssignment(serialNumber,deliveryPersonName)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "分配订单状态异常");
            }
        } catch (Exception e) {
            if (e.getMessage()==null) return APIResponse.errorResponse(3, "分配订单状态异常");
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @GetMapping("/getDeliveryOrder")
    @CrossOrigin
    public APIResponse<List<OrderDeliveryDTO>> getDeliveryOrder() {
        try {
            return APIResponse.successResponse(orderService.selectOrderDeliveryAll());
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    //通过订单编号查询关系表
    @GetMapping("/getDeliveryOrderBySerialNumber/{username}")

    public APIResponse<DeliveryAssignment> getDeliveryOrderBySerialNumber(@PathVariable String serialNumber) {
        System.out.println(serialNumber);
        try {
            return APIResponse.successResponse(orderService.selectByDeliveryPersonNameAndSerialNumber(serialNumber));
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
}
