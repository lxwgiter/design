package com.haust.design.controller;

import com.haust.design.dto.ConcertArgs;
import com.haust.design.dto.Result;
import com.haust.design.entity.Concert;
import com.haust.design.entity.ConcertDetail;
import com.haust.design.service.ConcertService;
import com.haust.design.utils.CopyUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("concert")
@Validated
public class ConcertController {

    @Resource
    ConcertService concertService;

    /**
     * 添加演唱会门票

     * @return
     */
    @PostMapping("/add")
    public Result<Object> addConcert(            @RequestParam("file") @NotNull MultipartFile file,
                                                 @RequestParam("name") @NotEmpty String name,
                                                 @RequestParam("performers") @NotEmpty  String performers,
                                                 @RequestParam("addressId") @NotNull Integer addressId,
                                                 @RequestParam("categoryId") @NotNull  Integer categoryId,
                                                 @RequestParam("detailedLocation") @NotEmpty  String detailedLocation,
                                                 @RequestParam("startTime") @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startTime,
                                                 @RequestParam("price") @NotNull Double price,
                                                 @RequestParam("stock") @NotNull Integer stock,
                                                 @RequestParam("projectDetails")   String projectDetails,
                                                 @RequestParam("ticketInfo")   String ticketInfo,
                                                 @RequestParam("viewingInfo")   String viewingInfo ) {
        // 处理上传的文件和普通字段
        ConcertArgs args = new ConcertArgs();
        args.setName(name);
        args.setPerformers(performers);
        args.setAddressId(addressId);
        args.setCategoryId(categoryId);
        args.setDetailedLocation(detailedLocation);
        args.setStartTime(startTime); // 这里可以调整为合适的类型
        args.setPrice(price);
        args.setStock(stock);
        args.setProjectDetails(projectDetails);
        args.setTicketInfo(ticketInfo);
        args.setViewingInfo(viewingInfo);


        return concertService.addConcertAndDetail(args,file);
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
    @GetMapping("/searchConcertByAddressAndCategory")
    public Result<Object> searchConcertByAddressAndCategory(@RequestParam(required = false) Integer addressId,@RequestParam(required = false) Integer categoryId,Integer pageNumber, Integer pageSize) {
        return concertService.searchConcertByAddressAndCategory(addressId,categoryId,pageNumber,pageSize);
    }

    //修改门票时数据回显
    @GetMapping("/getConcertById")
    public Result<Object> getConcertById(@RequestParam Integer concertId) {
        return concertService.getConcertById(concertId);
    }

    @PostMapping("/searchConcertByConditions")
    public Result<Object> searchConcertByConditions(@RequestBody ConcertArgs concertArgs) {
        return concertService.searchConcertByConditions(concertArgs);
    }



    /**
     * 更新演唱会表的主体部分
     * @param concertArgs
     * @return
     */
    @PutMapping("/updateConcert")
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
