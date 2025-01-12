package com.haust.design.mapper;

import com.haust.design.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> selectAllAddress();
}
