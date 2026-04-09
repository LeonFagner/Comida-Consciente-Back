package com.example.demo.controller;

import com.example.demo.dto.DonationRequestDTO;
import com.example.demo.dto.DonationResponseDTO;
import com.example.demo.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    @Autowired
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationResponseDTO> create(@RequestBody DonationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DonationResponseDTO>> getAll() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(donationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationResponseDTO> update(
            @PathVariable String id,
            @RequestBody DonationRequestDTO dto
    ) {
        return ResponseEntity.ok(donationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        donationService.delete(id);
        return ResponseEntity.ok("Doação cancelada com sucesso!");
    }
}