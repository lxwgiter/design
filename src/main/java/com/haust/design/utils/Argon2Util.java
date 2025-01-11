package com.haust.design.utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;  

public class Argon2Util {  

    private static final Argon2 argon2 = Argon2Factory.create();  

    // 加密密码  
    public static String encode(String password) {  
        return argon2.hash(2, 65536, 1, password);  
    }  

    // 验证密码  
    public static boolean matches(String rawPassword, String encodedPassword) {  
        return argon2.verify(encodedPassword, rawPassword);  
    }  

}