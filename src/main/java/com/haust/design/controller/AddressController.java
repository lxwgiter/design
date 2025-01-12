package com.haust.design.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haust.design.dto.Result;
import com.haust.design.entity.Address;
import com.haust.design.entity.ConcertCategory;
import com.haust.design.mapper.AddressMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressMapper addressMapper;

    @GetMapping("/all")
    public Result<Object> all(Integer pageNumber, Integer pageSize) {
        // 查询逻辑
        if (pageNumber == null) {
            pageNumber = 1; // 设置默认值
        }
        if (pageSize == null) {
            pageSize = 15; // 设置默认值
        }
        PageHelper.startPage(pageNumber, pageSize); // 设置分页参数
        List<Address> addresses = addressMapper.selectAllAddress();
        PageInfo<Address> pageInfo = new PageInfo<>(addresses);
        return Result.success(pageInfo);
    }
}
