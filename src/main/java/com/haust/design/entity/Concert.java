package com.haust.design.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class Concert {
    private Integer id;
    private String name;
    private Integer addressId;
    private String detailedLocation;
    private OffsetDateTime startTime;
    private Integer categoryId;
    private String performers;
    private Double price;
    private Integer stock;
    private String coverImageUrl;
}
