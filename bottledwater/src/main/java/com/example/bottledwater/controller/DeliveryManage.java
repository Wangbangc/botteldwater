package com.example.bottledwater.controller;

import com.example.bottledwater.entity.Delivery;
import com.example.bottledwater.service.DeliveryInterface;
import com.example.bottledwater.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间：2023/10/23
 * 编写人：wangbangc
 * 功能描述：管理配送员信息
 * 版本：1.0
 * 方法列表：
 * 1.创建配送员信息：insert
 * 2.删除配送员信息：del
 * 3.更新配送员信息：update
 * 4.获取所有配送员信息：selectAll
 * 5.根据用户名模糊查询配送员信息：selectLike
 **/
@RestController
@RequestMapping("/delivery")
public class DeliveryManage {
    @Autowired
    private DeliveryInterface deliveryInterface;

    @PostMapping("/insert")
    @CrossOrigin
    public APIResponse<String> insert(@RequestBody Delivery delivery) {
        try {
            System.out.println(delivery.getIdNumber());
            if (!deliveryInterface.selectUsername(delivery)) {
                return APIResponse.errorResponse(1, "用户名已存在");
            }
            if (!deliveryInterface.selectPhone(delivery)) {
                return APIResponse.errorResponse(2, "电话已存在");
            }
            if (!deliveryInterface.selectIdnumber(delivery)) {
                return APIResponse.errorResponse(4, "身份证号已被绑定");
            }
            if (deliveryInterface.insert(delivery)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "插入数据错误");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(3, e.getMessage());
        }
    }

    @DeleteMapping("/del")
    @CrossOrigin
    public APIResponse<String> del(Integer id) {
        try {
            if (!ObjectUtils.isEmpty(deliveryInterface.selectByPrimaryKey(id))) {
                if (deliveryInterface.deleteByPrimaryKey(id)) {
                    return APIResponse.successResponse1();
                } else {
                    return APIResponse.errorResponse(2, "删除数据错误");
                }
            } else {
                return APIResponse.errorResponse(2, "删除数据错误");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @PutMapping("/update")
    @CrossOrigin
    public APIResponse<String> update(@RequestBody Delivery delivery) {
        try {
            if (deliveryInterface.selectUsername(delivery) && deliveryInterface.selectIdnumber(delivery) && deliveryInterface.selectPhone(delivery)) {

                if (deliveryInterface.updateByPrimaryKey(delivery)) {
                    return APIResponse.successResponse1();
                } else {
                    return APIResponse.errorResponse(3, "信息重复或已被使用");
                }
            }
        } catch (Exception e) {
            APIResponse.errorResponse(4, e.getMessage());
        }
        return APIResponse.errorResponse(3, "修改信息错误");
    }

    @GetMapping("/selectAll")
    @CrossOrigin
    public APIResponse<List<Delivery>> selectAll() {
        try {
            List<Delivery> deliveries = deliveryInterface.selectAll();
            if (!ObjectUtils.isEmpty(deliveries)) {
                return APIResponse.successResponse(deliveries);
            }
            return APIResponse.errorResponse(1, "暂无数据");
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }

    }

    @GetMapping("/selectLike")
    @CrossOrigin
    public APIResponse<List<Delivery>> selectLike(String like) {
        System.out.println(like);
        try {
            List<Delivery> deliveries = deliveryInterface.selectLike(like);
            if (!ObjectUtils.isEmpty(deliveries)) {
                return APIResponse.successResponse(deliveries);
            }
            return APIResponse.errorResponse(2, "暂无数据");
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }

    }
}
