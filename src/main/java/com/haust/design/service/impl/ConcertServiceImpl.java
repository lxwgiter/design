package com.haust.design.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.ConcertDto;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import com.haust.design.mapper.ConcertDetailMapper;
import com.haust.design.mapper.ConcertMapper;
import com.haust.design.service.ConcertService;
import com.haust.design.utils.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
                concertArgs.getTicketInfo(),concertArgs.getViewingInfo());
        return Result.success();
    }

    @Override
    public Result<Object> addConcertDetail(ConcertArgs concertArgs) {
        Integer concertId = concertArgs.getConcertId();
        if(concertDetailMapper.exist(concertId)){
            return Result.error(409,"该演唱会详细信息已存在");
        }
        concertDetailMapper.insertById(concertId,concertArgs.getProjectDetails(),concertArgs.getTicketInfo(),concertArgs.getViewingInfo());
        return Result.success();
    }

    @Override
    public Result<Object> getAllConcert(Integer pageNumber, Integer pageSize) {
        // 查询逻辑
        if (pageNumber == null) {
            pageNumber = 1; // 设置默认值
        }
        if (pageSize == null) {
            pageSize = 15; // 设置默认值
        }
        PageHelper.startPage(pageNumber, pageSize); // 设置分页参数
        List<ConcertDto> concerts = concertMapper.selectAll();
        PageInfo<ConcertDto> pageInfo = new PageInfo<>(concerts);
        return Result.success(pageInfo);

    }

    @Override
    public Result<Object> getprojectDetails(Integer concertId) {
        String projectDetails = concertMapper.getProjectDetailsByConcertId(concertId);
        return Result.success(projectDetails);
    }

    @Override
    public Result<Object> getTicketInfoByConcertId(Integer concertId) {
        String ticketInfo = concertMapper.getTicketInfoByConcertId(concertId);
        return Result.success(ticketInfo);
    }

    @Override
    public Result<Object> getViewInfoByConcertId(Integer concertId) {
        String viewingInfo = concertMapper.getViewInfoByConcertId(concertId);
        return Result.success(viewingInfo);
    }

    @Override
    public Result<Object> searchConcertByName(String concertName, Integer pageNumber, Integer pageSize) {
        // 查询逻辑
        if (pageNumber == null) {
            pageNumber = 1; // 设置默认值
        }
        if (pageSize == null) {
            pageSize = 15; // 设置默认值
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Concert> concerts = concertMapper.searchConcertByName(concertName);
        PageInfo<Concert> pageInfo = new PageInfo<>(concerts);
        return Result.success(pageInfo);
    }

    @Override
    public Result<Object> searchConcertByPerformer(String performer, Integer pageNumber, Integer pageSize) {
        // 查询逻辑
        if (pageNumber == null) {
            pageNumber = 1; // 设置默认值
        }
        if (pageSize == null) {
            pageSize = 15; // 设置默认值
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Concert> concerts = concertMapper.searchConcertByPerformer(performer);
        PageInfo<Concert> pageInfo = new PageInfo<>(concerts);
        return Result.success(pageInfo);
    }

    @Override
    public Result<Object> searchConcertByAddressAndCategory(Integer addressId, Integer categoryId, Integer pageNumber, Integer pageSize) {
        // 查询逻辑
        if (pageNumber == null) {
            pageNumber = 1; // 设置默认值
        }
        if (pageSize == null) {
            pageSize = 15; // 设置默认值
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<Concert> concerts = concertMapper.searchConcertByAddressAndCategory(addressId,categoryId);
        PageInfo<Concert> pageInfo = new PageInfo<>(concerts);
        return Result.success(pageInfo);
    }

    @Override
    public Result<Object> updateConcert(ConcertArgs concertArgs) {
        Integer concertId = concertArgs.getConcertId();
        Concert newConcert = CopyUtil.copyProperties(concertArgs, Concert.class);
        concertMapper.updateConcertByConcertId(concertId,newConcert);
        ConcertDetail concertDetail = CopyUtil.copyProperties(concertArgs, ConcertDetail.class);
        concertDetailMapper.updateDetailsById(concertDetail);
        return Result.success();
    }

    @Override
    public Result<Object> updateDetail(ConcertDetail concertDetail) {

        concertDetailMapper.concertDetail(concertDetail);
        return Result.success();
    }

    @Override
    public Result<Object> deleteConcert(Integer concertId) {

        //先删除演唱会详情表
        concertDetailMapper.deleteConcertDetail(concertId);
        //再删除演唱会表
        concertMapper.deleteConcert(concertId);
        return Result.success();
    }

    @Override
    public Result<Object> getConcertById(Integer concertId) {

        ConcertDto concertDto = concertMapper.getConcertById(concertId);
        ConcertDetail concertDetail = concertDetailMapper.getAllDetailsByConcertId(concertId);
        concertDto.setProjectDetails(concertDetail.getProjectDetails());
        concertDto.setTicketInfo(concertDetail.getTicketInfo());
        concertDto.setViewingInfo(concertDetail.getViewingInfo());
        return Result.success(concertDto);
    }
}
