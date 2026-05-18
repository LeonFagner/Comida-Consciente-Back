package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvironmentalImpactDTO {

    private Double co2Saved;

    private Double waterSaved;
}