package com.haust.design.controller;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import com.haust.design.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    public Result<String> register(@RequestBody @Validated Admin user) {
        return adminService.register(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated Admin user) {
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
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatarUrl")
    public Result<String> updateAvatarUrl(@NotEmpty String avatarUrl) {
        return adminService.updateAvatarUrl(avatarUrl);
    }

    /**
     * 修改密码
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @PostMapping("/updatePassword")
    public Result<String> updatePassword(@Pattern(regexp = "^\\S{5,16}$")String newPassword,
                                         @Pattern(regexp = "^\\S{5,16}$")String oldPassword) {
        return adminService.updatePassword(newPassword,oldPassword);
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
