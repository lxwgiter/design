package com.haust.design.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

//管理员表
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin {
    private Integer id;
    //头像地址
    @URL
    private String avatarUrl;

    @NotEmpty(message = "用户名不合法",groups = {Register.class,Login.class})
    @Pattern(regexp = "^\\S{5,16}$",groups = {Register.class,Login.class})
    private String account;

    @NotEmpty(message = "密码不合法",groups = {Register.class,Login.class})
    @Pattern(regexp = "^\\S{5,16}$",groups = {Register.class,Login.class})
    private String password;

    @NotEmpty(groups = {Register.class} )
    @Email(groups = {Register.class} )
    private String email;
    private String nickName;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    public interface Register{

    }
    public interface Login{}
}
