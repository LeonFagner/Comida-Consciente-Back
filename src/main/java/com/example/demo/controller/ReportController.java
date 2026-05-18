package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> dashboard() {

        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping("/environmental-impact")
    public ResponseEntity<EnvironmentalImpactDTO> environmentalImpact() {

        return ResponseEntity.ok(service.getEnvironmentalImpact());
    }

    @GetMapping("/active-establishments")
    public ResponseEntity<List<ActiveEstablishmentDTO>> activeEstablishments() {

        return ResponseEntity.ok(service.getActiveEstablishments());
    }

    @GetMapping("/served-regions")
    public ResponseEntity<List<RegionStatsDTO>> servedRegions() {

        return ResponseEntity.ok(service.getServedRegions());
    }
}