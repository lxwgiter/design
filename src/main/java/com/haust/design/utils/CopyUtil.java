package com.haust.design.utils;

import org.springframework.beans.BeanUtils;

public class CopyUtil {  

    /**  
     * 复制属性到目标对象  
     *  
     * @param source     源对象  
     * @param targetClass 目标对象的类类型  
     * @param <T>       目标对象的类型  
     * @return 目标对象  
     */  
    public static <T> T copyProperties(Object source, Class<T> targetClass) {  
        try {  
            // 创建目标对象的实例  
            T target = targetClass.getDeclaredConstructor().newInstance();  
            // 复制属性  
            BeanUtils.copyProperties(source, target);  
            return target;  
        } catch (Exception e) {  
            throw new RuntimeException("复制属性失败", e);  
        }  
    }  
}