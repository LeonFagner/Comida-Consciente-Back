package com.example.demo.service;

import com.example.demo.dto.AppointmentRequestDTO;
import com.example.demo.dto.AppointmentResponseDTO;
import com.example.demo.enums.AppointmentStatus;
import com.example.demo.enums.DonationStatus;
import com.example.demo.model.Appointment;
import com.example.demo.model.Donation;
import com.example.demo.model.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DonationRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final DonationRepository donationRepository;
    private final UserRepository userRepository;

    // CREATE
    @Transactional
    public AppointmentResponseDTO create(AppointmentRequestDTO dto) {

        Donation donation = donationRepository.findById(dto.getDonationId())
                .orElseThrow(() -> new RuntimeException("Doação não encontrada"));

        // 🚨 valida status
        if (donation.getStatus() != DonationStatus.AVAILABLE) {
            throw new IllegalStateException("Doação não está disponível para agendamento");
        }

        User requester = userRepository.findById(dto.getRequesterId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 🚨 evita duplicidade (mesmo usuário)
        boolean alreadyExists = repository.existsByDonation_IdAndRequester_Id(
                dto.getDonationId(), dto.getRequesterId()
        );

        if (alreadyExists) {
            throw new IllegalStateException("Você já possui um agendamento para essa doação");
        }

        Appointment a = new Appointment();
        a.setDonation(donation);
        a.setRequester(requester);
        a.setScheduledDate(dto.getScheduledDate());
        a.setNotes(dto.getNotes());
        a.setStatus(AppointmentStatus.PENDING);

        // 🔥 trava a doação
        donation.setStatus(DonationStatus.RESERVED);
        donationRepository.save(donation);

        return toDTO(repository.save(a));
    }

    // GET ALL (do usuário)
    public List<AppointmentResponseDTO> getByRequester(String requesterId) {
        return repository.findByRequester_Id(requesterId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // GET BY ID
    public AppointmentResponseDTO getById(String id) {
        return toDTO(findById(id));
    }

    // UPDATE
    @Transactional
    public AppointmentResponseDTO update(String id, AppointmentRequestDTO dto) {

        Appointment a = findById(id);

        if (a.getStatus() == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("Agendamento cancelado não pode ser atualizado");
        }

        a.setScheduledDate(dto.getScheduledDate());
        a.setNotes(dto.getNotes());

        return toDTO(repository.save(a));
    }

    // CONFIRMAR
    @Transactional
    public AppointmentResponseDTO confirm(String id) {

        Appointment a = findById(id);

        if (a.getStatus() != AppointmentStatus.PENDING) {
            throw new IllegalStateException("Só pode confirmar agendamentos pendentes");
        }

        a.setStatus(AppointmentStatus.CONFIRMED);

        return toDTO(repository.save(a));
    }

    // CONCLUIR
    @Transactional
    public AppointmentResponseDTO complete(String id) {

        Appointment a = findById(id);

        if (a.getStatus() != AppointmentStatus.CONFIRMED) {
            throw new IllegalStateException("Só pode concluir agendamentos confirmados");
        }

        a.setStatus(AppointmentStatus.COMPLETED);

        // 🔥 quando conclui, a doação vira coletada
        Donation donation = a.getDonation();
        donation.setStatus(DonationStatus.COLLECTED);
        donationRepository.save(donation);

        return toDTO(repository.save(a));
    }

    // CANCELAR
    @Transactional
    public AppointmentResponseDTO cancel(String id) {

        Appointment a = findById(id);

        if (a.getStatus() == AppointmentStatus.CANCELLED) {
            throw new IllegalStateException("Agendamento já cancelado");
        }

        a.setStatus(AppointmentStatus.CANCELLED);

        // 🔥 libera a doação novamente
        Donation donation = a.getDonation();
        donation.setStatus(DonationStatus.AVAILABLE);
        donationRepository.save(donation);

        return toDTO(repository.save(a));
    }

    // PENDENTES
    public List<AppointmentResponseDTO> getPending() {
        return repository.findByStatus(AppointmentStatus.PENDING)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // AUX
    private Appointment findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    private AppointmentResponseDTO toDTO(Appointment a) {
        return new AppointmentResponseDTO(
                a.getId(),
                a.getDonation().getId(),
                a.getRequester().getId(),
                a.getScheduledDate(),
                a.getStatus(),
                a.getNotes(),
                a.getCreatedAt(),
                a.getUpdatedAt()
        );
    }
}