package com.example.demo.dto;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestDTO {

    private String donationId;
    private String requesterId;
    private LocalDateTime scheduledDate;
    private String notes;
}