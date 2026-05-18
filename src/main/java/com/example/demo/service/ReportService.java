package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository repository;

    public DashboardDTO getDashboard() {

        return new DashboardDTO(
                repository.totalDonations(),
                repository.totalUsers(),
                repository.totalAppointments(),
                repository.totalFoodSaved()
        );
    }

    public List<ActiveEstablishmentDTO> getActiveEstablishments() {

        return repository.activeEstablishments();
    }

    public List<RegionStatsDTO> getServedRegions() {

        return repository.servedRegions();
    }

    public EnvironmentalImpactDTO getEnvironmentalImpact() {

        double co2 = repository.totalFoodSaved().doubleValue() * 2.5;
        double water = repository.totalFoodSaved().doubleValue() * 15;

        return new EnvironmentalImpactDTO(co2, water);
    }
}