package com.example.demo.dto;

public record RankingDTO(
        String userId,
        String userName,
        Long totalPoints
) {}