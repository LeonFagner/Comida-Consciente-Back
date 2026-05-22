package com.example.demo.dto;

import lombok.Data;

@Data
public class DistanceRequestDTO {

    private Double lat1;
    private Double lon1;

    private Double lat2;
    private Double lon2;
}