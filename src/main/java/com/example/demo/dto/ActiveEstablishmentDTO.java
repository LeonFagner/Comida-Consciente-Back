package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActiveEstablishmentDTO {

    private String userId;

    private String establishmentName;

    private Long totalDonations;
}