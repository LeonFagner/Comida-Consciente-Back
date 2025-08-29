package com.example.demo.service;



import com.example.demo.dto.EstablishmentRequestDTO;
import com.example.demo.dto.EstablishmentResponseDTO;
import com.example.demo.enums.EstablishmentType;

import com.example.demo.model.Establishment;

import com.example.demo.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public EstablishmentResponseDTO create(EstablishmentRequestDTO dto) {
        if (repository.existsByEmail(dto.getEmail()) || repository.existsByCnpj(dto.getCnpj())) {
            throw new RuntimeException("Email or CNPJ already in use");
        }

        Establishment e = new Establishment();
        e.setEmail(dto.getEmail());
        e.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        e.setName(dto.getName());
        e.setCnpj(dto.getCnpj());
        e.setTradeName(dto.getTradeName());
        e.setEstablishmentType(EstablishmentType.valueOf(dto.getEstablishmentType()));
        e.setOpeningHours(dto.getOpeningHours());


        Establishment saved = repository.save(e);

        return new EstablishmentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getCnpj(),
                saved.getTradeName(),
                saved.getEstablishmentType().name(),
                saved.getOpeningHours(),
                saved.getTotalScore(),
                saved.getRecognitionLevel() != null ? saved.getRecognitionLevel().name() : null

        );


    }

    public List<EstablishmentResponseDTO> getAllEstablishments() {
        List<Establishment> establishments = repository.findAllEstablishments();

        return establishments.stream()
                .map(e -> new EstablishmentResponseDTO(
                        e.getId(),
                        e.getName(),
                        e.getEmail(),
                        e.getCnpj(),
                        e.getTradeName(),
                        e.getEstablishmentType() != null ? e.getEstablishmentType().name() : "UNKNOW",
                        e.getOpeningHours(),
                        e.getTotalScore(),
                        e.getRecognitionLevel() != null ? e.getRecognitionLevel().name() : null
                ))
                .toList();
    }


    public EstablishmentResponseDTO getEstablishmentById(String id) {
        Establishment e = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado com ID: " + id));
        return new EstablishmentResponseDTO(
                e.getId(),
                e.getName(),
                e.getEmail(),
                e.getCnpj(),
                e.getTradeName(),
                e.getEstablishmentType() != null ? e.getEstablishmentType().name() : "UNKNOW",
                e.getOpeningHours(),
                e.getTotalScore(),
                e.getRecognitionLevel() != null ? e.getRecognitionLevel().name() : null
        );
    }






}

