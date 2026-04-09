package com.example.demo.dto;

import com.example.demo.enums.DonationCategory;
import com.example.demo.enums.DonationStatus;
import com.example.demo.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class DonationResponseDTO {

    private String id;

    private String donorId;

    private String itemName;
    private DonationCategory category;
    private BigDecimal quantity;
    private UnitOfMeasure unitOfMeasure;

    private LocalDate expirationDate;

    private String pickupAddress;
    private String availableTimes;
    private String pickupContact;
    private String notes;

    private boolean urgent;

    private DonationStatus status;

    private BigDecimal latitude;
    private BigDecimal longitude;
}