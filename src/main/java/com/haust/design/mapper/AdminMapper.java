package com.haust.design.mapper;

import com.haust.design.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    boolean exist(@Param("account") String account);

    void insert(Admin user);

    Admin findByAccount(@Param("account") String account);

    void updateNickname(@Param("nickname") String nickname, @Param("id") Integer id);

    void updateAvatarUrl(@Param("avatarUrl") String avatarUrl, @Param("id") Integer id);
}
