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
import com.haust.design.utils.AliOSSUtils;
import com.haust.design.utils.CopyUtil;
import com.haust.design.utils.UniqueIdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    public Result<Object> addConcertAndDetail(ConcertArgs concertArgs, @NotNull MultipartFile file) {
        String name = concertArgs.getName();
        if(concertMapper.exist(name)){
            return Result.error(409,"该场次已存在");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String coverImageUrl = AliOSSUtils.uploadFile(UniqueIdGenerator.generateUniqueId() + ".jpg", inputStream);
        Concert concert = CopyUtil.copyProperties(concertArgs, Concert.class);
        //设置URL
        concert.setCoverImageUrl(coverImageUrl);
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
    public Result<Object> updateConcert(ConcertArgs concertArgs, MultipartFile file) {
        Integer concertId = concertArgs.getConcertId();
        Concert newConcert = CopyUtil.copyProperties(concertArgs, Concert.class);
        if (file != null) {
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            ConcertDto concertById = concertMapper.getConcertById(concertId);
            //删除原封面
            AliOSSUtils.deleteFile(concertById.getCoverImageUrl().substring(concertById.getCoverImageUrl().lastIndexOf("/")+1));
            //上传新封面
            String coverImageUrl = AliOSSUtils.uploadFile(UniqueIdGenerator.generateUniqueId() + ".jpg", inputStream);
            //修改封面URL
            newConcert.setCoverImageUrl(coverImageUrl);
        }
        //修改信息
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

    @Override
    public Result<Object> searchConcertByConditions(ConcertArgs concertArgs) {
        int pageNumber =1;//设置默认值
        int pageSize =15;
        // 查询逻辑
        if (concertArgs.getPageNumber() != null) {
            pageNumber = concertArgs.getPageNumber();
        }
        if (concertArgs.getPageSize() != null) {
            pageSize = concertArgs.getPageSize();
        }
        PageHelper.startPage(pageNumber, pageSize);
        List<ConcertDto> concerts = concertMapper.searchConcertByConditions(concertArgs);
        //若有时间筛选参数，执行筛选
        ArrayList<ConcertDto> newConcerts = new ArrayList<>();
        if (concertArgs.getStartTime() != null && concertArgs.getEndTime() != null) {
            for (ConcertDto concertDto : concerts) {
                if (concertDto.getStartTime().isBefore(concertArgs.getEndTime())
                && concertDto.getStartTime().isAfter(concertArgs.getStartTime())) {
                        newConcerts.add(concertDto);
                }
            }
        }
        //若没有时间筛选，则返回原数据
        if(newConcerts.isEmpty()){
            PageInfo<ConcertDto> pageInfo = new PageInfo<>(concerts);
            return Result.success(pageInfo);
        }
        PageInfo<ConcertDto> pageInfo = new PageInfo<>(newConcerts);
        //新得到的列表startPage失效，重新设置
        pageInfo.setPageSize(15);
        return Result.success(pageInfo);
    }
}
