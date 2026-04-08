package com.example.demo.service;



import com.example.demo.dto.OngRequestDTO;
import com.example.demo.dto.OngResponseDTO;
import com.example.demo.enums.UserType;
import com.example.demo.model.Ong;
import com.example.demo.model.User;
import com.example.demo.repository.OngRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OngService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public OngResponseDTO createOng(OngRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Ong ong = new Ong();
        ong.setName(dto.getName());
        ong.setEmail(dto.getEmail());
        ong.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        ong.setPhone(dto.getPhone());
        ong.setAddress(dto.getAddress());

        ong.setCnpj(dto.getCnpj());
        ong.setInstitutionType(dto.getInstitutionType());
        ong.setStorageCapacity(dto.getStorageCapacity());
        ong.setPeopleServed(dto.getPeopleServed());
        ong.setVerificationDocument(dto.getVerificationDocument());

        Ong saved = userRepository.save(ong);

        return OngResponseDTO.builder()
                .id((saved.getId()))
                .name(saved.getName())
                .email(saved.getEmail())
                .phone(saved.getPhone())
                .address(saved.getAddress())
                .cnpj(saved.getCnpj())
                .institutionType(saved.getInstitutionType())
                .storageCapacity(saved.getStorageCapacity())
                .peopleServed(saved.getPeopleServed())
                .verificationDocument(saved.getVerificationDocument())
                .build();
    }


    private final OngRepository ongRepository;

    public List<OngResponseDTO> getAllOng(){
        List<Ong> ongs = ongRepository.findAll();

        return ongs.stream()
                .map(e -> new OngResponseDTO(
                        e.getId(),
                        e.getName(),
                        e.getEmail(),
                        e.getPhone(),
                        e.getAddress(),
                        e.getCnpj(),
                        e.getInstitutionType(),
                        e.getStorageCapacity(),
                        e.getPeopleServed(),
                        e.getVerificationDocument()
                ))
                .toList();
    }

    public OngResponseDTO getById(String id) {

        Optional<Ong> ongOptional = ongRepository.findById(id);


        if (ongOptional.isPresent()) {
            Ong e = ongOptional.get();
            return new OngResponseDTO(
                    e.getId(),
                    e.getName(),
                    e.getEmail(),
                    e.getPhone(),
                    e.getAddress(),
                    e.getCnpj(),
                    e.getInstitutionType(),
                    e.getStorageCapacity(),
                    e.getPeopleServed(),
                    e.getVerificationDocument()
            );
        } else {

            throw new RuntimeException("ONG não encontrada com id: " + id);
        }
    }
    public OngResponseDTO updateOng(String id, OngRequestDTO dto) {

        Optional<Ong> ongOptional = ongRepository.findById(id);

        if (ongOptional.isPresent()) {

            Ong ong = ongOptional.get();

            // valida email duplicado (boa prática)
            if (!ong.getEmail().equals(dto.getEmail())
                    && userRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Email já está em uso.");
            }

            // atualiza campos
            ong.setName(dto.getName());
            ong.setEmail(dto.getEmail());
            ong.setPhone(dto.getPhone());
            ong.setAddress(dto.getAddress());

            ong.setInstitutionType(dto.getInstitutionType());
            ong.setStorageCapacity(dto.getStorageCapacity());
            ong.setPeopleServed(dto.getPeopleServed());
            ong.setVerificationDocument(dto.getVerificationDocument());

            Ong updated = ongRepository.save(ong);

            return new OngResponseDTO(
                    updated.getId(),
                    updated.getName(),
                    updated.getEmail(),
                    updated.getPhone(),
                    updated.getAddress(),
                    updated.getCnpj(), // mantém o original
                    updated.getInstitutionType(),
                    updated.getStorageCapacity(),
                    updated.getPeopleServed(),
                    updated.getVerificationDocument()
            );

        } else {
            throw new RuntimeException("ONG não encontrada com id: " + id);
        }
    }

// Será implementado após a criação de outras features dependente.
//POST /api/ongs/{id}/verificar - Verificar documentação
//GET /api/ongs/{id}/agendamentos - Agendamentos da ONG


}


