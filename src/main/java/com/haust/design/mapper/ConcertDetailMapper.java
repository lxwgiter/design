package com.haust.design.mapper;

import com.haust.design.dto.ConcertArgs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConcertDetailMapper {
    void insertById(@Param("concertId") Integer concertId, @Param("projectDetails") String projectDetails, @Param("ticketInfo") String ticketInfo, @Param("viewInfo") String viewInfo);
}
