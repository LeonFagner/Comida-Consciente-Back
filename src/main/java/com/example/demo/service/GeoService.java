package com.example.demo.service;

import com.example.demo.dto.DistanceResponseDTO;
import com.example.demo.dto.NearbyDonationDTO;
import com.example.demo.dto.RegionDTO;
import com.example.demo.model.Donation;
import com.example.demo.repository.GeoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final GeoRepository repository;

    public List<NearbyDonationDTO> getNearby(
            Double lat,
            Double lon,
            Double radiusKm
    ) {

        return repository.findAll()
                .stream()
                .filter(d -> d.getLatitude() != null && d.getLongitude() != null)
                .map(d -> {

                    double donationLat = d.getLatitude().doubleValue();
                    double donationLon = d.getLongitude().doubleValue();

                    double distance = calculateDistance(
                            lat,
                            lon,
                            donationLat,
                            donationLon
                    );

                    return new NearbyDonationDTO(
                            d.getId(),
                            d.getItemName(),
                            donationLat,
                            donationLon,
                            distance
                    );
                })
                .filter(d -> d.getDistanceKm() <= radiusKm)
                .toList();
    }

    public DistanceResponseDTO calculate(
            Double lat1,
            Double lon1,
            Double lat2,
            Double lon2
    ) {

        double distance = calculateDistance(lat1, lon1, lat2, lon2);

        return new DistanceResponseDTO(distance);
    }

    public List<RegionDTO> getRegions() {

        return repository.findAll()
                .stream()
                .map(Donation::getPickupAddress)
                .distinct()
                .map(RegionDTO::new)
                .toList();
    }

    private double calculateDistance(
            Double lat1,
            Double lon1,
            Double lat2,
            Double lon2
    ) {

        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}