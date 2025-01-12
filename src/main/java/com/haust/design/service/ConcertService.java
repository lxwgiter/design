package com.haust.design.service;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;

public interface ConcertService {
    Result<Object> addConcertOnly(Concert concert);

    Result<Object> addConcertAndDetail(ConcertArgs concertArgs);

    Result<Object> addConcertDetail(ConcertArgs concertArgs);

    Result<Object> getAllConcert(Integer pageNumber, Integer pageSize);

    Result<Object> getprojectDetails(Integer concertId);

    Result<Object> getTicketInfoByConcertId(Integer concertId);

    Result<Object> getViewInfoByConcertId(Integer concertId);

    Result<Object> searchConcertByName(String concertName, Integer pageNumber, Integer pageSize);

    Result<Object> searchConcertByPerformer(String performer, Integer pageNumber, Integer pageSize);

    Result<Object> searchConcertByAddressAndCategory(Integer addressId, Integer categoryId, Integer pageNumber, Integer pageSize);

    Result<Object> updateConcert(ConcertArgs concertArgs);

    Result<Object> updateDetail(ConcertDetail concertDetail);

    Result<Object> deleteConcert(Integer concertId);
}
