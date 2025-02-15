package com.haust.design.entity;

import lombok.Data;

@Data
public class ConcertDetail {
    private Integer id;
    private Integer concertId;
    //项目详情
    private String projectDetails;
    //购票须知
    private String ticketInfo;
    //观影须知
    private String viewingInfo;
}
