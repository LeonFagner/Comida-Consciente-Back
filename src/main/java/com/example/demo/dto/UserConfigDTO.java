package com.example.demo.dto;

import lombok.Data;

@Data
public class UserConfigDTO {

    private boolean darkMode;

    private String language;

    private boolean emailNotifications;
}