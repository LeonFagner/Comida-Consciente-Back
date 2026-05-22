package com.example.demo.controller;

import com.example.demo.dto.DistanceRequestDTO;
import com.example.demo.dto.DistanceResponseDTO;
import com.example.demo.dto.NearbyDonationDTO;
import com.example.demo.dto.RegionDTO;
import com.example.demo.service.GeoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
@RequiredArgsConstructor
public class GeoController {

    private final GeoService service;

    @GetMapping("/nearby")
    public ResponseEntity<List<NearbyDonationDTO>> getNearby(
            @RequestParam Double lat,
            @RequestParam Double lon,
            @RequestParam Double radiusKm
    ) {

        return ResponseEntity.ok(
                service.getNearby(lat, lon, radiusKm)
        );
    }

    @PostMapping("/calculate-distance")
    public ResponseEntity<DistanceResponseDTO> calculateDistance(
            @RequestBody DistanceRequestDTO dto
    ) {

        return ResponseEntity.ok(
                service.calculate(
                        dto.getLat1(),
                        dto.getLon1(),
                        dto.getLat2(),
                        dto.getLon2()
                )
        );
    }

    @GetMapping("/regions")
    public ResponseEntity<List<RegionDTO>> getRegions() {

        return ResponseEntity.ok(service.getRegions());
    }
}