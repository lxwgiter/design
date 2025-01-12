package com.haust.design.service;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;

public interface ConcertService {
    Result<Object> addConcertOnly(Concert concert);

    Result<Object> addConcertAndDetail(ConcertArgs concertArgs);
}
