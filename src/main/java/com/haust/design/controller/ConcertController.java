package com.haust.design.controller;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import com.haust.design.service.ConcertService;
import com.haust.design.utils.CopyUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("concert")
@Validated
public class ConcertController {

    @Resource
    ConcertService concertService;

    /**
     * 添加演唱会门票
     * @param concertArgs
     * @return
     */
    @PostMapping("/add")
    public Result<Object> addConcert(@Validated @RequestBody ConcertArgs concertArgs) {

//        Concert concert = CopyUtil.copyProperties(concertArgs, Concert.class);
//
//        if(concertArgs.getProjectDetails() == null &&
//        concertArgs.getViewingInfo()==null && concertArgs.getTicketInfo() == null) {
//            return concertService.addConcertOnly(concert);
//        }
        return concertService.addConcertAndDetail(concertArgs);
    }

    /**
     * 添加门票详情，这个接口貌似没什么用
     * @param concertArgs
     * @return
     */
    @PostMapping("/addDetail")
    public Result<Object> addDetail(@RequestBody ConcertArgs concertArgs) {
        return concertService.addConcertDetail(concertArgs);
    }

    /**
     * 得到所有的演唱会门票信息并分页展示
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("allConcert")
    public Result<Object> allConcert(Integer pageNumber, Integer pageSize) {
        return concertService.getAllConcert(pageNumber,pageSize);
    }

    /**
     * 请求projectDetails
     * @param concertId 演唱会id
     * @return
     */
    @GetMapping("/projectDetails")
    public Result<Object> projectDetails(@RequestParam Integer concertId) {
        return concertService.getprojectDetails(concertId);
    }

    @GetMapping("/ticketInfo")
    public Result<Object> ticketInfo(@RequestParam Integer concertId) {
        return concertService.getTicketInfoByConcertId(concertId);
    }

    @GetMapping("/viewingInfo")
    public Result<Object> viewingInfo(@RequestParam Integer concertId) {
        return concertService.getViewInfoByConcertId(concertId);
    }

    /**
     * 根据演唱会名称模糊查询并分页
     * @param concertName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/searchConcertByName")
    public Result<Object> searchConcertByName(@RequestParam String concertName,Integer pageNumber, Integer pageSize) {
        return concertService.searchConcertByName(concertName,pageNumber,pageSize);
    }

    // TODO 根据时间区间过滤门票的请求

    @GetMapping("/searchConcertByPerformer")
    public Result<Object> searchConcertByPerformer(@RequestParam String performer,Integer pageNumber, Integer pageSize) {
        return concertService.searchConcertByPerformer(performer,pageNumber,pageSize);
    }

    /**
     * 根据地址或分类查询演唱会
     * @param addressId 可选
     * @param categoryId 可选
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("searchConcertByAddressAndCategory")
    public Result<Object> searchConcertByAddressAndCategory(@RequestParam(required = false) Integer addressId,@RequestParam(required = false) Integer categoryId,Integer pageNumber, Integer pageSize) {
        return concertService.searchConcertByAddressAndCategory(addressId,categoryId,pageNumber,pageSize);
    }

    /**
     * 更新演唱会表的主体部分
     * @param concertArgs
     * @return
     */
    @PutMapping("updateConcert")
    public Result<Object> updateConcert(@RequestBody ConcertArgs concertArgs) {
        return concertService.updateConcert(concertArgs);
    }

    /**
     * 修改演唱会详情，concertId必传，projectDetails、ticketInfo、viewInfo有且仅有一个有值
     * @param concertDetail
     * @return
     */
    @PatchMapping("/updateDetails")
    public Result<Object> updateDetail(@RequestBody ConcertDetail concertDetail) {
        return concertService.updateDetail(concertDetail);
    }

    @DeleteMapping("/deleteConcert")
    public Result<Object> deleteConcert(@RequestParam Integer concertId) {
        return concertService.deleteConcert(concertId);
    }


}
