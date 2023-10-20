package com.example.bottledwater.controller;

import com.example.bottledwater.entity.userwater;
import com.example.bottledwater.service.adminInterface;
import com.example.bottledwater.service.userInterface;
import com.example.bottledwater.utils.APIResponse;
import com.example.bottledwater.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/userManage")
public class userManage {
    @Autowired
    private userInterface userInterface;
@Autowired
com.example.bottledwater.service.adminInterface adminInterface;

@GetMapping ("/selectAll")
@CrossOrigin
public APIResponse<List<userwater>> select(@RequestHeader("Authorization" )String token){
    try {

        if (adminInterface.token(utils.getUsernameFromToken(token))){
            return APIResponse.successResponse(userInterface.selectAll());
        }else{
            return APIResponse.errorResponse(2,"查询失败");
        }
    }catch (Exception e){
        return APIResponse.errorResponse(1,e.getMessage());
    }
}

    @PostMapping("/insert")
    @CrossOrigin
    public APIResponse<String> Insert(@RequestHeader("Authorization") String token,@RequestBody userwater user){

        try {
            if(adminInterface.token(utils.getUsernameFromToken(token))){
                if (userInterface.selectusername(user)){
                    return APIResponse.errorResponse(1,"用户已存在");
                }
                if(userInterface.selectPhone(user)){
                    return APIResponse.errorResponse(5,"电话已绑定");
                }
                if (userInterface.selectEmail(user)) return APIResponse.errorResponse(6,"邮箱已绑定");
                if (userInterface.insert(user)){
                    return APIResponse.successResponse1();
                }else {
                    return APIResponse.errorResponse(3,"请联系管理员");
                }
            }else {
                return APIResponse.errorResponse(2,"管理员权限错误");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return APIResponse.errorResponse(4, e.getMessage());
        }
    }

    @PostMapping("/update")
    @CrossOrigin
    public APIResponse<String> Update(@RequestBody userwater userwater){
    try {
        if(userInterface.selectEmail(userwater))return APIResponse.errorResponse(3,"修改邮箱已存在");
        if(userInterface.selectusername(userwater))return APIResponse.errorResponse(3,"修改用户名已存在");
        if(userInterface.selectPhone(userwater))return APIResponse.errorResponse(3,"修改电话已被绑定");
        if (userInterface.updateuser(userwater)){
          return   APIResponse.successResponse1();
        }else {
            return APIResponse.errorResponse(2,"修改错误");
        }
    }catch (Exception e){

        return APIResponse.errorResponse(1, e.getMessage());

    }
    }

    @DeleteMapping ("/delUser")
    @CrossOrigin
    public APIResponse<String> Del(int id){
    try {

        if (userInterface.deluser(id)){
            return APIResponse.successResponse1();
        }else {
            return APIResponse.errorResponse(2,"删除错误");
        }
    }catch (Exception e){
        return APIResponse.errorResponse(1, e.getMessage());

    }
    }

    @GetMapping("selUser")
    @CrossOrigin
    public APIResponse<List<userwater>> selUser(String sel){
    try {
        List<userwater> userwaters=userInterface.selectUser(sel);
       if(!ObjectUtils.isEmpty(userwaters)){
           return APIResponse.successResponse(userwaters);
       }else {
           return APIResponse.errorResponse(2,"查询不存在");
       }
    }catch (Exception e){
        return APIResponse.errorResponse(1,e.getMessage());
    }
    }


}
