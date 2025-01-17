package com.haust.design.service.impl;

import com.haust.design.dto.ResetPassword;
import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import com.haust.design.mapper.AdminMapper;
import com.haust.design.service.AdminService;
import com.haust.design.utils.Argon2Util;
import com.haust.design.utils.JwtUtil;
import com.haust.design.utils.ThreadLocalUtil;
import com.haust.design.utils.UniqueIdGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Result<String> register(Admin user) {
        String account = user.getAccount();
        //判断用户名是否存在
        if(adminMapper.exist(account)){
            return Result.error(409,"用户名已存在");
        }
        //对密码加密
        String password = user.getPassword();
        user.setPassword(Argon2Util.encode(password));
        //设置属性默认值
        user.setAvatarUrl("default.jpg");
        user.setNickName("管理员"+ UniqueIdGenerator.generateUniqueId());
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedTime(now);
        user.setUpdatedTime(now);
        adminMapper.insert(user);
        return Result.success("注册成功");
    }

    @Override
    public Result<String> login(Admin user) {
        Admin findUser=adminMapper.findByAccount(user.getAccount());
        if(findUser==null){
            return Result.error(401,"用户名或密码错误");
        }
        if(!Argon2Util.matches(user.getPassword(), findUser.getPassword())){
            return Result.error(401,"用户名或密码错误");
        }
        //使用JWT验证
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id",findUser.getId());
        claims.put("account", findUser.getAccount());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }

    @Override
    public Result<String> updateNickname(String nickname) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        adminMapper.updateNickname(nickname,id);
        return Result.success("修改昵称成功");
    }

    @Override
    public Result<String> updateAvatarUrl(String avatarUrl) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        adminMapper.updateAvatarUrl(avatarUrl,id);
        return Result.success("修改头像成功");
    }

    @Override
    public Result<String> updatePassword(String newPassword, String oldPassword) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        Admin user=adminMapper.findById(id);
        if(Argon2Util.matches(oldPassword,user.getPassword())){
            newPassword = Argon2Util.encode(newPassword);
            adminMapper.updatePassword(newPassword,id);
            return Result.success("修改密码成功");
        }else return Result.error(400,"原密码错误");

    }

    @Override
    public Result<Object> me() {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        Admin user=adminMapper.findById(id);
        user.setPassword(null);
        return Result.success(user);
    }

    @Override
    public Result<String> updateNicknameAndEmail(String nickname, String email) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        adminMapper.updateNicknameAndEmail(id,nickname,email);
        return Result.success("修改成功");
    }

    @Override
    public Result<String> forgetPassword(ResetPassword resetPassword) {
        Admin user=adminMapper.getUserByAccountAndEmail(resetPassword.getAccount(),resetPassword.getEmail());
        if(user==null){
            return Result.error(409,"账号或邮箱错误");
        }
        adminMapper.updatePassword(Argon2Util.encode(resetPassword.getNewPassword()),user.getId());
        return Result.success("修改密码成功");
    }

}
