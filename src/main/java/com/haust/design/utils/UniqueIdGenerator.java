package com.haust.design.utils;

import java.util.UUID;

public class UniqueIdGenerator {  

    /**  
     * 生成唯一 ID  
     *  
     * @return 唯一 ID  
     */  
    public static String generateUniqueId() {  
        return UUID.randomUUID().toString();  
    }  

}