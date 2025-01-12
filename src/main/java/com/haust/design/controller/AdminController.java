package com.haust.design.controller;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import com.haust.design.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

@Controller
@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody @Validated Admin user) {
        return adminService.register(user);

    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Validated Admin user) {
        return adminService.login(user);
    }

    @PatchMapping("/updateNickname")
    public Result<String> updateNickname(@NotEmpty String nickname) {
        return adminService.updateNickname(nickname);
    }

    @PatchMapping("/updateAvatarUrl")
    public Result<String> updateAvatarUrl(@NotEmpty String avatarUrl) {
        return adminService.updateAvatarUrl(avatarUrl);
    }














}
