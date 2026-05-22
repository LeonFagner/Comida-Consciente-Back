package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppConfigDTO {

    private String appName;

    private String version;

    private String supportEmail;
}