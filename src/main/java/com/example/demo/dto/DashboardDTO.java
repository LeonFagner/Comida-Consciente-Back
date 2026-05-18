package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private Long totalDonations;

    private Long totalUsers;

    private Long totalAppointments;

    private BigDecimal totalFoodSaved;
}