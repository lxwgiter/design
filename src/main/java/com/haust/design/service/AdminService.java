package com.haust.design.service;

import com.haust.design.dto.ResetPassword;
import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public interface AdminService {
    Result<String> register(Admin user);

    Result<String> login(Admin user);

    Result<String> updateNickname(String nickname);

    Result<String> updateAvatarUrl(@NotNull MultipartFile file);

    Result<String> updatePassword(String newPassword, String oldPassword);

    Result<Object> me();

    Result<String> updateNicknameAndEmail(@Param("nickname") String nickname, @Param("email") String email);

    Result<String> forgetPassword(ResetPassword resetPassword);
}
