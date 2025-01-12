package com.haust.design.controller;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.service.ConcertService;
import com.haust.design.utils.CopyUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("concert")
@Validated
public class ConcertController {

    @Resource
    ConcertService concertService;

    @PostMapping("/add")
    public Result<Object> addConcert(@Validated @RequestBody ConcertArgs concertArgs) {

        Concert concert = CopyUtil.copyProperties(concertArgs, Concert.class);

        if(concertArgs.getProjectDetails() == null &&
        concertArgs.getViewInfo()==null && concertArgs.getTicketInfo() == null) {
            return concertService.addConcertOnly(concert);
        }
        return concertService.addConcertAndDetail(concertArgs);
    }
}
