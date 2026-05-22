package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NearbyDonationDTO {

    private String donationId;
    private String itemName;
    private Double latitude;
    private Double longitude;
    private Double distanceKm;
}