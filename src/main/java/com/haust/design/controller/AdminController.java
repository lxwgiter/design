package com.haust.design.controller;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import com.haust.design.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RestController
@RequestMapping("/admin")
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














}
