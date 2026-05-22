package com.example.demo.dto;

import lombok.Data;

@Data
public class NotificationConfigDTO {

    private boolean donationNotifications;

    private boolean appointmentNotifications;

    private boolean marketingNotifications;
}