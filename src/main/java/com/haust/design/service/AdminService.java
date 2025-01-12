package com.haust.design.service;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;

import javax.validation.constraints.NotEmpty;

public interface AdminService {
    Result<String> register(Admin user);

    Result<String> login(Admin user);

    Result<String> updateNickname(String nickname);

    Result<String> updateAvatarUrl(String avatarUrl);
}
