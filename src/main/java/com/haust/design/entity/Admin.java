package com.haust.design.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

//管理员表
@Data
public class Admin {
    private int id;
    //头像地址
    @URL
    private String avatarUrl;

    @NotEmpty(message = "用户名不合法")
    @Pattern(regexp = "^\\S{5,16}$")
    private String account;

    @NotEmpty(message = "密码不合法")
    @Pattern(regexp = "^\\S{5,16}$")
    private String password;
    private String nickName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
