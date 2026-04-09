package com.example.demo.dto;

import com.example.demo.enums.DonationCategory;
import com.example.demo.enums.UnitOfMeasure;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DonationRequestDTO {

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

    private BigDecimal latitude;
    private BigDecimal longitude;
}