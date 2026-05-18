package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DonationsByPeriodDTO {

    private String period;

    private Long totalDonations;
}