package com.haust.design.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;  
import com.auth0.jwt.interfaces.DecodedJWT;  
import com.auth0.jwt.interfaces.JWTVerifier;  

import java.util.Date;  
import java.util.Map;  

public class JwtUtil {  

    private static final String KEY = "huast";  // 密钥

    /**  
     * 根据业务数据生成 JWT Token
     * @param claims 业务数据  
     * @return 生成的 JWT Token  
     */  
    public static String genToken(Map<String, Object> claims) {  
        return JWT.create()  
                .withClaim("claims", claims)  // 将业务数据添加到 Token  
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // 设置过期时间为24小时
                .sign(Algorithm.HMAC256(KEY));  // 采用 HMAC256 对 Token 进行签名  
    }  

    /**  
     * 解析 JWT Token  
     *  
     * @param token JWT Token  
     * @return 解析出的业务数据  
     */  
    public static Map<String, Object> parseToken(String token) {  
        // 创建 JWT 验证器  
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();  
        // 验证 Token 并获取解析后的 JWT 对象  
        DecodedJWT decodedJWT = verifier.verify(token);  
        // 返回 Token 中的业务数据  
        return decodedJWT.getClaim("claims").asMap();  
    }  
}