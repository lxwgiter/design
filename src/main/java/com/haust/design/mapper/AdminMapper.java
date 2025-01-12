package com.haust.design.mapper;

import com.haust.design.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Mapper
public interface AdminMapper {

    boolean exist(@Param("account") String account);

    void insert(Admin user);

    Admin findByAccount(@Param("account") String account);
}
