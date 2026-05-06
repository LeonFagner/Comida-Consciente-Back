package com.example.demo.dto;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        String id,
        String reviewerName,
        String reviewedName,
        int rating,
        String comment,
        LocalDateTime createdAt
) {}