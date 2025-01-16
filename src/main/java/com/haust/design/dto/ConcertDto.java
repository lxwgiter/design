package com.haust.design.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class ConcertDto {

    private Integer concertId;

    private String name;

    private String address;

    private String detailedLocation;

    private OffsetDateTime startTime;

    private String category;

    private String performers;

    private Double price;

    private Integer stock;

    private String coverImageUrl;

    private Integer addressId;

    private Integer categoryId;

    //项目详情
    private String projectDetails;
    //购票须知
    private String ticketInfo;
    //观影须知
    private String viewingInfo;

}
