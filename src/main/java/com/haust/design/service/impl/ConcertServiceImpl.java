package com.haust.design.service.impl;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import com.haust.design.mapper.ConcertDetailMapper;
import com.haust.design.mapper.ConcertMapper;
import com.haust.design.service.ConcertService;
import com.haust.design.utils.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ConcertServiceImpl implements ConcertService {

    @Resource
    private ConcertMapper concertMapper;

    @Resource
    ConcertDetailMapper concertDetailMapper;

    @Override
    public Result<Object> addConcertOnly(Concert concert) {
        String name = concert.getName();
        if(concertMapper.exist(name)){
            return Result.error(409,"该场次已存在");
        }
        concertMapper.insert(concert);
        return Result.success();
    }

    @Override
    public Result<Object> addConcertAndDetail(ConcertArgs concertArgs) {
        String name = concertArgs.getName();
        if(concertMapper.exist(name)){
            return Result.error(409,"该场次已存在");
        }
        Concert concert = CopyUtil.copyProperties(concertArgs, Concert.class);
        //插入并获取生成的id
        concertMapper.insert(concert);
        concertDetailMapper.insertById(concert.getId(),concertArgs.getProjectDetails(),
                concertArgs.getTicketInfo(),concertArgs.getViewInfo());
        return Result.success();
    }
}
