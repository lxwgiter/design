package com.haust.design.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;  
import lombok.Data;  
import lombok.NoArgsConstructor;  

@Data  
@Builder  
@NoArgsConstructor  
@AllArgsConstructor  
public class Result<T> {  
    private int code;       // 状态码  
    private String message; // 响应消息  
    private T data;        // 存放响应数据  
    private Long total;     // 数据总量，用于分页支持  

    // 成功结果（无数据）  
    public static <T> Result<T> success() {  
        return Result.<T>builder()  
                .code(200)  
                .message("请求成功")  
                .data(null)  
                .total(null)  
                .build();  
    }  

    // 成功结果（带数据）  
    public static <T> Result<T> success(T data) {  
        return Result.<T>builder()  
                .code(200)  
                .message("请求成功")  
                .data(data)  
                .total(null)  
                .build();  
    }  

    // 成功结果（带数据和总数，用于分页）  
    public static <T> Result<T> success(T data, Long total) {  
        return Result.<T>builder()  
                .code(200)  
                .message("请求成功")  
                .data(data)  
                .total(total)  
                .build();  
    }  

    // 失败结果  
    public static <T> Result<T> error(int code, String message) {  
        return Result.<T>builder()  
                .code(code)  
                .message(message)  
                .data(null)  
                .total(null)  
                .build();  
    }
}