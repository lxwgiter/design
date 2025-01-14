package com.haust.design.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConcertDto {

    private Integer concertId;

    private String name;

    private String address;

    private String detailedLocation;

    private LocalDateTime startTime;

    private String category;

    private String performers;

    private Double price;

    private Integer stock;

    private String coverImageUrl;

}
