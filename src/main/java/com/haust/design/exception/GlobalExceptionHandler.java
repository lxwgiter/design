package com.haust.design.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;  
import org.springframework.validation.BindingResult;  
import org.springframework.validation.FieldError;  
import org.springframework.web.bind.MethodArgumentNotValidException;  
import org.springframework.web.bind.annotation.ControllerAdvice;  
import org.springframework.web.bind.annotation.ExceptionHandler;  

import java.util.HashMap;  
import java.util.Map;  

@ControllerAdvice  
public class GlobalExceptionHandler {  

    //处理参数校验非法的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)  
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {  
        Map<String, String> errors = new HashMap<>();  
        
        // 从 BindingResult 中获取所有字段错误  
        BindingResult bindingResult = ex.getBindingResult();  
        for (FieldError fieldError : bindingResult.getFieldErrors()) {  
            String fieldName = fieldError.getField(); // 字段名  
            String errorMessage = fieldError.getDefaultMessage(); // 错误消息  
            
            // 将字段名与错误消息关联，构建友好的错误结构  
            errors.put(fieldName, errorMessage);  
        }  
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors); // 返回400状态码和错误信息  
    }  
}