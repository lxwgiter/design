package com.haust.design.mapper;

import com.haust.design.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    boolean exist(@Param("account") String account);

    void insert(Admin user);
}
