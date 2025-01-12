package com.haust.design.mapper;

import com.haust.design.entity.Concert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConcertMapper {
    boolean exist(@Param("name") String name);

    void insert(Concert concert);
}
