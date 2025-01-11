package com.haust.design.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

//管理员表
@Data
public class Admin {
    private int id;
    //头像地址
    private String avatarUrl;
    private String account;
    private String password;
    private String nickName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
