package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegionStatsDTO {

    private String region;

    private Long totalDonations;
}