package com.example.demo.dto;


import com.example.demo.enums.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDTO {

    private String userId;
    private String title;
    private String message;
    private NotificationType type;
}