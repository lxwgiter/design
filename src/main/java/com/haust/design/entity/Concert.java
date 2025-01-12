package com.haust.design.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Concert {
    private int id;
    private String name;
    private int addressId;
    private String detailedLocation;
    private LocalDateTime startTime;
    private int categoryId;
    private String performers;
    private Double price;
    private int stock;
    private String coverImageUrl;
}
