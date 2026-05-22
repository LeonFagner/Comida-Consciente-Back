package com.example.demo.controller;

import com.example.demo.dto.CepResponseDTO;
import com.example.demo.dto.UploadResponseDTO;
import com.example.demo.service.UtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/utils")
@RequiredArgsConstructor
public class UtilsController {

    private final UtilsService service;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<CepResponseDTO> getCep(
            @PathVariable String cep
    ) {

        return ResponseEntity.ok(
                service.getAddressByCep(cep)
        );
    }

    @PostMapping("/upload-image")
    public ResponseEntity<UploadResponseDTO> uploadImage(
            @RequestParam("file") MultipartFile file
    ) {

        return ResponseEntity.ok(
                service.uploadImage(file)
        );
    }

    @GetMapping("/food-categories")
    public ResponseEntity<List<String>> getFoodCategories() {

        return ResponseEntity.ok(
                service.getFoodCategories()
        );
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {

        return ResponseEntity.ok(
                service.healthCheck()
        );
    }
}