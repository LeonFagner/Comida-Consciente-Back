package com.example.demo.dto;

import java.time.LocalDateTime;

public record ScoreHistoryDTO(
        String description,
        Integer points,
        LocalDateTime createdAt
) {}