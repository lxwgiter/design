package com.haust.design.mapper;

import com.haust.design.entity.Concert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConcertMapper {
    boolean exist(@Param("name") String name);

    void insert(Concert concert);

    List<Concert> selectAll();

    String getProjectDetailsByConcertId(@Param("concertId") Integer concertId);

    String getTicketInfoByConcertId(@Param("concertId") Integer concertId);

    String getViewInfoByConcertId(@Param("concertId") Integer concertId);
}
