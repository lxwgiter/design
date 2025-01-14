package com.haust.design.mapper;

import com.haust.design.dto.ConcertDto;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConcertMapper {
    boolean exist(@Param("name") String name);

    void insert(Concert concert);

    List<ConcertDto> selectAll();

    String getProjectDetailsByConcertId(@Param("concertId") Integer concertId);

    String getTicketInfoByConcertId(@Param("concertId") Integer concertId);

    String getViewInfoByConcertId(@Param("concertId") Integer concertId);

    List<Concert> searchConcertByName(@Param("concertName") String concertName);

    List<Concert> searchConcertByPerformer(@Param("performer") String performer);

    List<Concert> searchConcertByAddressAndCategory(@Param("addressId") Integer addressId, @Param("categoryId") Integer categoryId);

    void updateConcertByConcertId(@Param("concertId") Integer concertId, @Param("newConcert") Concert newConcert);

    void deleteConcert(@Param("concertId") Integer concertId);
}
