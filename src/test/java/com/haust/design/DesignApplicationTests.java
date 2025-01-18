package com.haust.design;


import com.haust.design.entity.Address;
import com.haust.design.entity.ConcertCategory;
import com.haust.design.mapper.AddressMapper;
import com.haust.design.mapper.ConcertCategoryMapper;
import com.haust.design.utils.AliOSSUtils;
import com.haust.design.utils.Argon2Util;
import com.haust.design.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@SpringBootTest
class DesignApplicationTests {

    @Resource
    private ConcertCategoryMapper concertCategoryMapper;

    @Resource
    private AddressMapper addressMapper;

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

    @Test
    void testConcertCategory(){
        for (ConcertCategory concertCategory : concertCategoryMapper.selectAll()) {
            System.out.println(concertCategory);
        }
    }

    @Test
    void testAddress(){
        for (Address selectAllAddress : addressMapper.selectAllAddress()) {
            System.out.println(selectAllAddress);
        }

    }
    @Test
    void testOSS() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("E:\\default.jpg");
        String s = AliOSSUtils.uploadFile("default.jpg", in);
        System.out.println(s);
    }
    @Test
    void testOSSDelete() throws FileNotFoundException {
        AliOSSUtils.deleteFile("005.png");
    }

}
