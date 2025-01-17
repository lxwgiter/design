package com.haust.design.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class ResetPassword {
    @NotEmpty
    @Pattern(regexp = "^\\S{5,16}$")
    private String oldPassword;
    @NotEmpty
    @Pattern(regexp = "^\\S{5,16}$")
    private String newPassword;
}
