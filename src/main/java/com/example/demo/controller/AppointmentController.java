package com.example.demo.controller;



import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;
import com.example.demo.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getByUser(@RequestParam String requesterId) {
        return ResponseEntity.ok(service.getByRequester(requesterId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@PathVariable String id,
                                                         @RequestBody AppointmentRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<AppointmentResponseDTO> confirm(@PathVariable String id) {
        return ResponseEntity.ok(service.confirm(id));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<AppointmentResponseDTO> complete(@PathVariable String id) {
        return ResponseEntity.ok(service.complete(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponseDTO> cancel(@PathVariable String id) {
        return ResponseEntity.ok(service.cancel(id));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<AppointmentResponseDTO>> getPending() {
        return ResponseEntity.ok(service.getPending());
    }
}