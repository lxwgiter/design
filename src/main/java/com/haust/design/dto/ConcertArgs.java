package com.haust.design.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class ConcertArgs {
    private Integer concertId;
    @NotEmpty(message = "演唱会名称为空")
    private String name;
    @NotNull(message = "地址为空")
    private Integer addressId;
    @NotEmpty(message = "详细地址为空")
    private String detailedLocation;
    @NotNull(message = "开始时间为空")
    private OffsetDateTime startTime;
    @NotNull(message = "演唱会类别为空")
    private Integer categoryId;
    @NotEmpty(message = "演唱会参演明星为空")
    private String performers;
    @NotNull(message = "演唱会价格为空")
    private Double price;
    @NotNull(message = "演唱会库存为空")
    private Integer stock;
    private String coverImageUrl;

    //项目详情
    private String projectDetails;
    //购票须知
    private String ticketInfo;
    //观影须知
    private String viewingInfo;

    //可能的参数
    private Integer pageNumber;
    private Integer pageSize;
    private OffsetDateTime endTime;
}
