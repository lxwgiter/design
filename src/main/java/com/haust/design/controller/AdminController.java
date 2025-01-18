package com.haust.design.controller;

import com.haust.design.dto.ResetPassword;
import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import com.haust.design.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated(Admin.Register.class) Admin user) {
        return adminService.register(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated(Admin.Login.class) Admin user) {
        return adminService.login(user);
    }

    /**
     * 修改昵称
     * @param nickname
     * @return
     */
    @PatchMapping("/updateNicknameAndEmail")
    public Result<String> updateNicknameAndEmail(@NotEmpty String nickname,@NotEmpty String email) {
        return adminService.updateNicknameAndEmail(nickname,email);
    }

    /**
     * 修改头像URL
     * @param file
     * @return
     */
    @PatchMapping("/updateAvatarUrl")
    public Result<String> updateAvatarUrl(@NotNull MultipartFile file) {
        return adminService.updateAvatarUrl(file);
    }

    /**
     * 修改密码

     * @return
     */
    @PostMapping("/updatePassword")
    public Result<String> updatePassword(@RequestBody @Validated(ResetPassword.Reset.class) ResetPassword resetPassword) {
        return adminService.updatePassword(resetPassword.getNewPassword(),resetPassword.getOldPassword());
    }

    @PostMapping("/forgetPassword")
    public Result<String> forgetPassword(@RequestBody @Validated(ResetPassword.Forget.class) ResetPassword resetPassword) {
        return adminService.forgetPassword(resetPassword);
    }


    /**
     * 获取用户详情
     * @return
     */
    @GetMapping("/me")
    public Result<Object> me() {
        return adminService.me();
    }

}
