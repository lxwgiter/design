package com.haust.design.service;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import org.springframework.web.multipart.MultipartFile;

public interface ConcertService {
    Result<Object> addConcertOnly(Concert concert);

    Result<Object> addConcertAndDetail(ConcertArgs concertArgs, MultipartFile file);

    Result<Object> addConcertDetail(ConcertArgs concertArgs);

    Result<Object> getAllConcert(Integer pageNumber, Integer pageSize);

    Result<Object> getprojectDetails(Integer concertId);

    Result<Object> getTicketInfoByConcertId(Integer concertId);

    Result<Object> getViewInfoByConcertId(Integer concertId);

    Result<Object> searchConcertByName(String concertName, Integer pageNumber, Integer pageSize);

    Result<Object> searchConcertByPerformer(String performer, Integer pageNumber, Integer pageSize);

    Result<Object> searchConcertByAddressAndCategory(Integer addressId, Integer categoryId, Integer pageNumber, Integer pageSize);

    Result<Object> updateConcert(ConcertArgs concertArgs, MultipartFile file);

    Result<Object> updateDetail(ConcertDetail concertDetail);

    Result<Object> deleteConcert(Integer concertId);

    Result<Object> getConcertById(Integer concertId);

    Result<Object> searchConcertByConditions(ConcertArgs concertArgs);
}
