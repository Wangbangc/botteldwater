package com.example.bottledwater.utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;
/**
 * 响应工具类
 * 列表类型响应：successResponse
 * 空类型响应：successResponse1
 * data=null,message=null
 * 错误类型响应：errorResponse
 * **/
@JsonRootName("APIResponse")
public class APIResponse<T> {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("code")
    private int code;
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private String message;


    public APIResponse(boolean success, int code, T data, String message) {
        this.success = success;
        this.code = code;
        this.data = data;
        this.message = message;

    }
    public static <T> APIResponse<T> successLogin(T data){
        return new APIResponse<>(true,0,data,"");
    }
    public static <T> APIResponse<T> successResponse1() {
        return new APIResponse<>(true, 0, null, null);
    }
    public static <T> APIResponse<T> successResponse(T data) {
        return new APIResponse<>(true, 0, data, "");
    }
    public static <T> APIResponse<T> errorResponse(int statusCode, String message) {
        return new APIResponse<>(false, statusCode, null, message);
    }
}
