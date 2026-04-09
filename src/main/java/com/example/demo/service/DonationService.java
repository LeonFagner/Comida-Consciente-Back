package com.example.demo.service;

import com.example.demo.dto.DonationRequestDTO;
import com.example.demo.dto.DonationResponseDTO;
import com.example.demo.enums.DonationStatus;
import com.example.demo.model.Donation;
import com.example.demo.model.User;
import com.example.demo.repository.DonationRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    // CREATE
    public DonationResponseDTO create(DonationRequestDTO dto) {

        User donor = userRepository.findById(dto.getDonorId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        Donation d = new Donation();
        d.setDonor(donor);
        d.setItemName(dto.getItemName());
        d.setCategory(dto.getCategory());
        d.setQuantity(dto.getQuantity());
        d.setUnitOfMeasure(dto.getUnitOfMeasure());
        d.setExpirationDate(dto.getExpirationDate());
        d.setPickupAddress(dto.getPickupAddress());
        d.setAvailableTimes(dto.getAvailableTimes());
        d.setPickupContact(dto.getPickupContact());
        d.setNotes(dto.getNotes());
        d.setUrgent(dto.isUrgent());
        d.setLatitude(dto.getLatitude());
        d.setLongitude(dto.getLongitude());

        d.setStatus(DonationStatus.AVAILABLE);


        Donation saved = donationRepository.save(d);


        return toDTO(saved);
    }

    // GET ALL
    public List<DonationResponseDTO> getAllDonations() {
        List<Donation> donations = donationRepository.findAll();

        return donations.stream()
                .map(d -> new DonationResponseDTO(
                        d.getId() != null ? d.getId().toString() : null,

                        d.getDonor() != null ? d.getDonor().getId().toString() : null,

                        d.getItemName(),
                        d.getCategory(),
                        d.getQuantity(),
                        d.getUnitOfMeasure(),

                        d.getExpirationDate(),

                        d.getPickupAddress(),
                        d.getAvailableTimes(),
                        d.getPickupContact(),
                        d.getNotes(),

                        d.isUrgent(),

                        d.getStatus(),

                        d.getLatitude(),
                        d.getLongitude()
                ))
                .toList();
    }

    // GET BY ID
    public DonationResponseDTO getById(String id) {
        Donation d = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        return toDTO(d);
    }

    // UPDATE
    public DonationResponseDTO update(String id, DonationRequestDTO dto) {

        Donation d = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        d.setItemName(dto.getItemName());
        d.setCategory(dto.getCategory());
        d.setQuantity(dto.getQuantity());
        d.setUnitOfMeasure(dto.getUnitOfMeasure());
        d.setExpirationDate(dto.getExpirationDate());
        d.setPickupAddress(dto.getPickupAddress());
        d.setAvailableTimes(dto.getAvailableTimes());
        d.setPickupContact(dto.getPickupContact());
        d.setNotes(dto.getNotes());
        d.setUrgent(dto.isUrgent());
        d.setLatitude(dto.getLatitude());
        d.setLongitude(dto.getLongitude());

        Donation updated = donationRepository.save(d);

        return toDTO(updated);
    }

    // DELETE (cancelar)
    public DonationResponseDTO delete(String id) {

        Donation d = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));


        if (d.getStatus() == DonationStatus.CANCELLED) {
            throw new RuntimeException("Doação já está cancelada");
        }

        d.setStatus(DonationStatus.CANCELLED);

        Donation updated = donationRepository.save(d);

        return toDTO(updated);
    }

    // MAPPER
    private DonationResponseDTO toDTO(Donation d) {
        return DonationResponseDTO.builder()
                .id(d.getId())
                .donorId(d.getDonor().getId())
                .itemName(d.getItemName())
                .category(d.getCategory())
                .quantity(d.getQuantity())
                .unitOfMeasure(d.getUnitOfMeasure())
                .expirationDate(d.getExpirationDate())
                .pickupAddress(d.getPickupAddress())
                .availableTimes(d.getAvailableTimes())
                .pickupContact(d.getPickupContact())
                .notes(d.getNotes())
                .urgent(d.isUrgent())
                .status(d.getStatus())
                .latitude(d.getLatitude())
                .longitude(d.getLongitude())
                .build();
    }
}