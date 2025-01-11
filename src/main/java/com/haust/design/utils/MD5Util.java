package com.haust.design.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  

//MD5加密工具类，MD5 在某些应用中仍被使用，但由于其已被证明容易受到碰撞攻击，建议使用更安全的加密算法，例如 bcrypt 或 Argon2，尤其在处理密码时。
public class MD5Util {  

    // 用于MD5加密的静态方法  
    public static String md5(String password) {  
        try {  
            // 创建MD5 MessageDigest实例  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // 进行加密  
            byte[] messageDigest = md.digest(password.getBytes());  
            // 将字节数组转换为16进制字符串  
            StringBuilder hexString = new StringBuilder();  
            for (byte b : messageDigest) {  
                String hex = Integer.toHexString(0xff & b);  
                if (hex.length() == 1) {  
                    hexString.append('0');  
                }  
                hexString.append(hex);  
            }  
            return hexString.toString();  
        } catch (NoSuchAlgorithmException e) {  
            throw new RuntimeException("MD5 加密算法不存在", e);  
        }  
    }  

}