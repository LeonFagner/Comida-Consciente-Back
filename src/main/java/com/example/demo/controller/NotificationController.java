package com.example.demo.controller;



import com.example.demo.dto.NotificationRequestDTO;
import com.example.demo.dto.NotificationResponseDTO;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(
            @PathVariable String userId
    ) {

        return ResponseEntity.ok(service.getUserNotifications(userId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable String id) {

        service.markAsRead(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/read-all/{userId}")
    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {

        service.markAllAsRead(userId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponseDTO> send(
            @RequestBody NotificationRequestDTO dto
    ) {

        return ResponseEntity.ok(service.send(dto));
    }
}