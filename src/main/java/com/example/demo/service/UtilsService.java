package com.example.demo.service;

import com.example.demo.dto.CepResponseDTO;
import com.example.demo.dto.UploadResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UtilsService {

    public CepResponseDTO getAddressByCep(String cep) {

        CepResponseDTO dto = new CepResponseDTO();

        dto.setCep(cep);
        dto.setStreet("Rua Exemplo");
        dto.setNeighborhood("Centro");
        dto.setCity("Joinville");
        dto.setState("SC");

        return dto;
    }

    public UploadResponseDTO uploadImage(MultipartFile file) {

        String fileName = file.getOriginalFilename();

        String fakeUrl = "http://localhost:8080/uploads/" + fileName;

        return new UploadResponseDTO(
                fileName,
                fakeUrl
        );
    }

    public List<String> getFoodCategories() {

        return List.of(
                "Frutas",
                "Verduras",
                "Legumes",
                "Grãos",
                "Laticínios",
                "Carnes",
                "Bebidas",
                "Padaria"
        );
    }

    public String healthCheck() {

        return "API ONLINE";
    }
}