package com.haust.design.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Concert {
    private Integer id;
    private String name;
    private Integer addressId;
    private String detailedLocation;
    private LocalDateTime startTime;
    private Integer categoryId;
    private String performers;
    private Double price;
    private Integer stock;
    private String coverImageUrl;
}
