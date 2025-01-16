package com.haust.design.mapper;

import com.haust.design.entity.ConcertDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConcertDetailMapper {
    void insertById(@Param("concertId") Integer concertId, @Param("projectDetails") String projectDetails, @Param("ticketInfo") String ticketInfo, @Param("viewingInfo") String viewingInfo);

    boolean exist(@Param("concertId") Integer concertId);

    void concertDetail(ConcertDetail concertDetail);

    void deleteConcertDetail(@Param("concertId") Integer concertId);

    ConcertDetail getAllDetailsByConcertId(@Param("concertId") Integer concertId);

    void updateDetailsById(ConcertDetail concertDetail);
}
