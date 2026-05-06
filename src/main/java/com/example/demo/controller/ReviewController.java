package com.example.demo.controller;

import com.example.demo.dto.ReviewRequestDTO;
import com.example.demo.dto.ReviewResponseDTO;
import com.example.demo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> create(@RequestBody ReviewRequestDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<List<ReviewResponseDTO>> getByAppointment(@PathVariable String id) {
        return ResponseEntity.ok(service.getByAppointment(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReviewResponseDTO>> getByUser(@PathVariable String id) {
        return ResponseEntity.ok(service.getByUser(id));
    }

    @GetMapping("/reviewer/{id}")
    public ResponseEntity<List<ReviewResponseDTO>> getByReviewer(@PathVariable String id) {
        return ResponseEntity.ok(service.getByReviewer(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponseDTO> update(@PathVariable String id,
                                                    @RequestBody ReviewRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}