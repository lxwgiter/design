package com.haust.design.service;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;

public interface AdminService {
    Result<String> register(Admin user);

    Result<String> login(Admin user);

    Result<String> updateNickname(String nickname);

    Result<String> updateAvatarUrl(String avatarUrl);

    Result<String> updatePassword(String newPassword, String oldPassword);

    Result<Object> me();
}
