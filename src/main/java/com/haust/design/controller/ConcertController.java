package com.haust.design.controller;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
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

        Concert concert = CopyUtil.copyProperties(concertArgs, Concert.class);

        if(concertArgs.getProjectDetails() == null &&
        concertArgs.getViewInfo()==null && concertArgs.getTicketInfo() == null) {
            return concertService.addConcertOnly(concert);
        }
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

    @GetMapping("/viewInfo")
    public Result<Object> viewInfo(@RequestParam Integer concertId) {
        return concertService.getViewInfoByConcertId(concertId);
    }

}
