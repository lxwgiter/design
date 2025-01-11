package com.haust.design;

import com.haust.design.utils.Argon2Util;
import com.haust.design.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DesignApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testMD5(){
        String s = MD5Util.md5("123456");
        System.out.println(s);
    }

    @Test
    void testArgon2(){
        String password = "mySecretPassword";
        // 加密密码
        String hashedPassword = Argon2Util.encode(password);
        System.out.println("原始密码: " + password);
        System.out.println("Argon2加密后: " + hashedPassword);
        // 验证密码
        boolean isPasswordMatch = Argon2Util.matches(password, hashedPassword);
        System.out.println("密码匹配: " + isPasswordMatch);
    }

}
