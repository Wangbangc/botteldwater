package com.example.bottledwater.controller;

import com.example.bottledwater.entity.admin;
import com.example.bottledwater.service.adminInterface;
import com.example.bottledwater.utils.APIResponse;
import com.example.bottledwater.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private adminInterface adminInterface;
    @PostMapping("/login")
    @CrossOrigin
    public APIResponse<Object> Login(@RequestBody admin admin){
        Map<String,String> a=new HashMap<>();
        try {
            if (!ObjectUtils.isEmpty(adminInterface.login(admin))){
                admin admin1=(adminInterface.login(admin));
a.put("username",admin1.getUsername());
a.put("token",utils.generateToken(admin1.getUsername()));
                return APIResponse.successResponse(a);
            }else {
                if (adminInterface.loginname(admin))return APIResponse.errorResponse(2,"密码错误");
                if (!adminInterface.loginname(admin))return APIResponse.errorResponse(3,"用户不存在");
                return APIResponse.errorResponse(0,"登入错误");
            }
        }catch (Exception e){
return APIResponse.errorResponse(1,e.getMessage());
        }

    }


@PostMapping("/register")
@CrossOrigin
    public APIResponse<Object> Register(@RequestBody admin admin){
        Map<String,String> res=new HashMap<>();

        try {if (adminInterface.loginname(admin)){
            return APIResponse.errorResponse(2,"用户名已存在");
        }
            if(adminInterface.register(admin)){
                res.put("username",admin.getUsername());
                res.put("token",utils.generateToken(admin.getUsername()));
               return APIResponse.successResponse(res);
            }
           else {
                return APIResponse.errorResponse(0,"注册失败");
            }
        }catch (Exception e){
            return APIResponse.errorResponse(1,e.getMessage());
        }

}

@PostMapping("/update")
@CrossOrigin
    public APIResponse<Object> Update(@RequestHeader("Authorization") String token, @RequestBody admin admin){

   Map<String,String> a=new HashMap<>();
        try {
            if ( adminInterface.updateAdmin(admin,utils.getUsernameFromToken(token))){
                a.put("token", utils.generateToken(admin.getUsername()));
                return APIResponse.successResponse(a);
            }else {
                return APIResponse.errorResponse(2,"更新失败");
            }
        }catch (Exception e){
            return APIResponse.errorResponse(1,e.getMessage());
        }
}


}
