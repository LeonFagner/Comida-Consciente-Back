package com.example.demo.dto;

public record ReviewRequestDTO(
        String appointmentId,
        String reviewerId,
        String reviewedId,
        int rating,
        String comment
) {}