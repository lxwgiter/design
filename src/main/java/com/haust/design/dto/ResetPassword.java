package com.haust.design.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ResetPassword {
    @NotEmpty(groups = {Reset.class})
    @Pattern(regexp = "^\\S{5,16}$",groups = {Reset.class})
    private String oldPassword;
    @NotEmpty(groups = {Reset.class,Forget.class})
    @Pattern(regexp = "^\\S{5,16}$",groups = {Reset.class,Forget.class})
    private String newPassword;

    @NotEmpty(groups = {Forget.class})
    @Email(groups = {Forget.class})
    private String email;

    @NotEmpty(groups = {Forget.class})
    @Pattern(regexp = "^\\S{5,16}$",groups = {Forget.class})
    private String account;

    public interface Reset {

    }

    public interface Forget {

    }
}
