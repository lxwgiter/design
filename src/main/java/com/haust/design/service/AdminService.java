package com.haust.design.service;

import com.haust.design.dto.Result;
import com.haust.design.entity.Admin;

public interface AdminService {
    Result<String> register(Admin user);

    Result<String> login(Admin user);
}
