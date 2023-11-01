package com.example.bottledwater.controller;

import com.example.bottledwater.entity.admin;
import com.example.bottledwater.service.adminInterface;
import com.example.bottledwater.utils.APIResponse;
import com.example.bottledwater.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建日期：2023/10/23
 * 编写人：wangbangc
 * 功能描述：管理管理员信息
 * 版本：1.0
 * 方法列表：
 * 1.管理员登入：Login
 * 2.管理员注册：Register
 * 3.管理员更新：Update
 **/

@RestController

@RequestMapping("/admin")
public class adminController {
    @Autowired
    private adminInterface adminInterface;

    @PostMapping("/login")
    @CrossOrigin
    public APIResponse<Object> Login(@RequestBody admin admin) {
        Map<String, String> a = new HashMap<>();
        try {
            if (!ObjectUtils.isEmpty(adminInterface.login(admin))) {
                admin admin1 = (adminInterface.login(admin));
                a.put("username", admin1.getUsername());
                a.put("token", utils.generateToken(admin1.getUsername()));
                return APIResponse.successResponse(a);
            } else {
                if (adminInterface.loginname(admin)) return APIResponse.errorResponse(2, "密码错误");
                if (!adminInterface.loginname(admin)) return APIResponse.errorResponse(3, "用户不存在");
                return APIResponse.errorResponse(0, "登入错误");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }

    }


    @PostMapping("/register")
    @CrossOrigin
    public APIResponse<Object> Register(@RequestBody admin admin) {
        Map<String, String> res = new HashMap<>();

        try {
            if (adminInterface.loginname(admin)) {
                return APIResponse.errorResponse(2, "用户名已存在");
            }
            if (adminInterface.register(admin)) {
                res.put("username", admin.getUsername());
                res.put("token", utils.generateToken(admin.getUsername()));
                return APIResponse.successResponse(res);
            } else {
                return APIResponse.errorResponse(0, "注册失败");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }

    }

    @GetMapping("/selectUsername")
    @CrossOrigin(origins = "http://localhost:8080")
    public APIResponse<Object> selectUsername(@RequestHeader("Authorization") String token) {
        Map<String, String> res = new HashMap<>();
        try {
            admin admin = adminInterface.selectUsernameAdmin(utils.getUsernameFromToken(token));
            res.put("username", admin.getUsername());
            res.put("password", admin.getPassword());
            res.put("phone", admin.getPhone());
            res.put("email", admin.getEmail());
            return APIResponse.successResponse(res);
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }

    @PostMapping("/update")
    @CrossOrigin
    public APIResponse<Object> Update(@RequestHeader("Authorization") String token, @RequestBody admin admin) {

        Map<String, String> a = new HashMap<>();
        try {
            if (adminInterface.loginname(admin)) {
                return APIResponse.errorResponse(2, "用户名已存在");
            }
            if (adminInterface.updateAdmin(admin, utils.getUsernameFromToken(token))) {
                a.put("token", utils.generateToken(admin.getUsername()));
                return APIResponse.successResponse(a);
            } else {
                return APIResponse.errorResponse(2, "更新失败");
            }
        } catch (Exception e) {
            return APIResponse.errorResponse(1, e.getMessage());
        }
    }


}
