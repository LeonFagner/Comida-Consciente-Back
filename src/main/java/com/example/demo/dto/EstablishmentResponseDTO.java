package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentResponseDTO {
    private String id;
    private String name;
    private String email;
    private String cnpj;
    private String tradeName;
    private String establishmentType;
    private String openingHours;
    private int totalScore;
    private String recognitionLevel;



}