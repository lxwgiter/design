package com.haust.design.mapper;

import com.haust.design.entity.ConcertCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConcertCategoryMapper {
    List<ConcertCategory> selectAll();
}
