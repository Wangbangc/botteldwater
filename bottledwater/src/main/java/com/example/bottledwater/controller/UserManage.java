package com.example.bottledwater.controller;

import com.example.bottledwater.entity.UserWater;
import com.example.bottledwater.service.AdminInterface;
import com.example.bottledwater.service.UserInterface;
import com.example.bottledwater.utils.APIResponse;
import com.example.bottledwater.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间：2023/10/23
 * 编写人：wangbangc
 * 功能描述：管理用户信息
 * 版本：1.0
 * 方法列表：
 * 1.获取所有用户信息：select
 * 2.添加用户信息：Insert
 * 3.更新用户信息：Update
 * 4.删除用户信息：Del
 * 5.根据用户名模糊查询用户信息：selUser
 **/
@RestController
@RequestMapping("/userManage")
public class UserManage {
    @Autowired
    private UserInterface userInterface;
    @Autowired
    AdminInterface adminInterface;

    @GetMapping("/selectAll")
    @CrossOrigin
    public APIResponse<List<UserWater>> select(@RequestHeader("Authorization") String token) {
        try {

            if (adminInterface.token(Utils.getUsernameFromToken(token))) {
                return APIResponse.successResponse(userInterface.selectAll());
            } else {
                return APIResponse.errorResponse(2, "查询失败");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @PostMapping("/insert")
    @CrossOrigin
    public APIResponse<String> insert(@RequestHeader("Authorization") String token, @RequestBody UserWater user) {

        try {
            if (adminInterface.token(Utils.getUsernameFromToken(token))) {
                if (userInterface.selectusername(user)) {
                    return APIResponse.errorResponse(1, "用户已存在");
                }
                if (userInterface.selectPhone(user)) {
                    return APIResponse.errorResponse(5, "电话已绑定");
                }
                if (userInterface.selectEmail(user)) {
                    return APIResponse.errorResponse(6, "邮箱已绑定");
                }
                if (userInterface.insert(user)) {
                    return APIResponse.successResponse1();
                } else {
                    return APIResponse.errorResponse(3, "请联系管理员");
                }
            } else {
                return APIResponse.errorResponse(2, "管理员权限错误");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return APIResponse.errorResponse(4, e.getMessage());
        }
    }

    @PostMapping("/update")
    @CrossOrigin
    public APIResponse<String> update(@RequestBody UserWater userwater) {

        try {
            if (userInterface.selectEmail(userwater)) {
                return APIResponse.errorResponse(3, "修改邮箱已存在");
            }
            if (userInterface.selectusername(userwater)) {
                return APIResponse.errorResponse(3, "修改用户名已存在");
            }
            if (userInterface.selectPhone(userwater)) {
                return APIResponse.errorResponse(3, "修改电话已被绑定");
            }
            if (userInterface.updateuser(userwater)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "修改错误");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @DeleteMapping("/delUser")
    @CrossOrigin
    public APIResponse<String> del(int id) {
        try {

            if (userInterface.deluser(id)) {
                return APIResponse.successResponse1();
            } else {
                return APIResponse.errorResponse(2, "删除错误");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());

        }
    }

    @GetMapping("/selUser")
    @CrossOrigin
    public APIResponse<List<UserWater>> selUser(String sel) {
        System.out.println(sel);
        try {
            List<UserWater> userwaters = userInterface.selectUser(sel);
            if (!ObjectUtils.isEmpty(userwaters)) {
                return APIResponse.successResponse(userwaters);
            } else {
                return APIResponse.errorResponse(2, "查询不存在");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    //根据id查询
    @GetMapping("/selectIdUserwater")
    @CrossOrigin
    public APIResponse<UserWater> selectIdUserwater(Integer id) {

        try {
            UserWater userwater = userInterface.selectIdUserwater(id);
            if (!ObjectUtils.isEmpty(userwater)) {
                return APIResponse.successResponse(userwater);
            } else {
                return APIResponse.errorResponse(2, "查询不存在");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }
}
