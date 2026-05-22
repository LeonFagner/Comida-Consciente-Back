package com.example.demo.controller;

import com.example.demo.dto.AppConfigDTO;
import com.example.demo.dto.NotificationConfigDTO;
import com.example.demo.dto.UserConfigDTO;
import com.example.demo.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ConfigService service;

    @GetMapping("/app")
    public ResponseEntity<AppConfigDTO> getAppConfig() {

        return ResponseEntity.ok(
                service.getAppConfig()
        );
    }

    @PutMapping("/user")
    public ResponseEntity<UserConfigDTO> updateUserConfig(
            @RequestBody UserConfigDTO dto
    ) {

        return ResponseEntity.ok(
                service.updateUserConfig(dto)
        );
    }

    @GetMapping("/notifications")
    public ResponseEntity<NotificationConfigDTO> getNotificationConfig() {

        return ResponseEntity.ok(
                service.getNotificationConfig()
        );
    }

    @PutMapping("/notifications")
    public ResponseEntity<NotificationConfigDTO> updateNotificationConfig(
            @RequestBody NotificationConfigDTO dto
    ) {

        return ResponseEntity.ok(
                service.updateNotificationConfig(dto)
        );
    }
}