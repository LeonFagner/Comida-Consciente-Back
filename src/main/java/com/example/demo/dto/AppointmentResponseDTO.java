package com.example.demo.dto;



import com.example.demo.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {

    private String id;
    private String donationId;
    private String requesterId;
    private LocalDateTime scheduledDate;
    private AppointmentStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}